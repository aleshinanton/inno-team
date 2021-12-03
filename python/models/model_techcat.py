######## ДЛЯ УСТАНОВКЯИ spacy НУЖНО ########
#
# python -m pip install -U --user spacy
# python -m spacy download ru_core_news_lg
#
# Для проверки установки spacy
#
# python -m spacy validate
#
######################################


from pickle_model import save_obj, load_obj

import os
import pandas as pd
import numpy as np
import configparser
import spacy

import warnings
warnings.filterwarnings('ignore')

# import re
from spacy.tokenizer import Tokenizer
from spacy.util import compile_prefix_regex, compile_infix_regex, compile_suffix_regex
from spacy.lang.char_classes import CONCAT_QUOTES, LIST_ELLIPSES, LIST_ICONS, ALPHA_LOWER, ALPHA_UPPER, ALPHA, HYPHENS

from sklearn.metrics.pairwise import cosine_similarity


#############################################################


# имя раздела модели в конфигурационном файле CONFIG.ini
MODEL = 'TECHCAT'


# ======== настройки из CONFIG.ini ========

CONFIG = configparser.ConfigParser()  # создаeм объект парсера
CONFIG.read('CONFIG.INI')  # читаем конфиг
TECHCAT = dict(CONFIG[MODEL])

# путь к данным модели
model_path = TECHCAT['model_path']
# имя модели для сохранения объекта
model_name = TECHCAT['model_name']
# имя файла с данными для ОБУЧЕНИЯ модели
data_train_filename = model_path + TECHCAT['data_train']
# путь и имя файла пикл
model_pickle = model_path + model_name


#############################################################

# препроцессинг текста
def text_preprocessing(text, nlp, lowercase=False):
    # TOKEN's kind to DELETE
    #
    # token.is_oov    # Is the token out-of-vocabulary (i.e. does it not have a word vector)
    # not token.has_vector
    # token.is_bracket
    # token.is_punct
    # token.is_stop
    # token.is_space
    # token.like_num
    # token.is_currency
    # token.like_url
    # token.like_url

    text = nlp(text)
    lemmatized = []
    for token in text:

        if (not token.is_oov) and (not token.is_bracket) and (not token.is_punct) and (not token.is_stop) and \
                (not token.is_space) and (not token.like_num) and (not token.is_currency) and (not token.like_url) and \
                (not token.like_url):
            if lowercase:
                lemmatized.append(token.lemma_.strip().lower())
            else:
                lemmatized.append(token.lemma_.strip())

    return " ".join(lemmatized)


#############################################################

def train_model():
    """Тренирует, сохраняет на диск и возвращает кортеж объектов модели."""


    # ======== ЗАГРУЗКА данных ========


    # загружаем каталог с подкаталогами
    df_tech_cats_subcats = pd.read_csv(data_train_filename, index_col='subcat_id')
    df_tech_cats_subcats['cat_subcat'] = df_tech_cats_subcats['category'] + ' => ' + df_tech_cats_subcats['sub_category'] + ' => ' + df_tech_cats_subcats['anchors']


    # ======== ПРЕПРОЦЕССИНГ данных ========

    nlp = spacy.load('ru_core_news_lg')

    # кастомный токенайзер
    def custom_tokenizer(nlp):
        # infix_re = re.compile(r'''[.\,\?\:\;\...\‘\’\`\“\”\"\'~]''')

        _infixes = (
                LIST_ELLIPSES
                + LIST_ICONS
                + [
                    # r"(?<=[0-9])[+\-\*^](?=[0-9-])", # exclude '-' not need to split ATL-NELLC-N9K-2 for example
                    r"(?<=[0-9])[+\*^](?=[0-9-])",
                    r"(?<=[{al}{q}])\.(?=[{au}{q}])".format(
                        al=ALPHA_LOWER, au=ALPHA_UPPER, q=CONCAT_QUOTES
                    ),
                    r"(?<=[{a}]),(?=[{a}])".format(a=ALPHA),
                    # r"(?<=[{a}0-9])(?:{h})(?=[{a}])".format(a=ALPHA, h=HYPHENS), # exclude HYPHENS = "- – — -- --- —— ~"
                    r"(?<=[{a}0-9])[:<>=/](?=[{a}])".format(a=ALPHA),
                ]
        )

        prefix_re = compile_prefix_regex(nlp.Defaults.prefixes)
        suffix_re = compile_suffix_regex(nlp.Defaults.suffixes)
        infix_re = compile_infix_regex(_infixes)

        return Tokenizer(nlp.vocab, prefix_search=prefix_re.search,
                         suffix_search=suffix_re.search,
                         infix_finditer=infix_re.finditer,
                         token_match=None)

    # установка токенайзера для nlp
    nlp.tokenizer = custom_tokenizer(nlp)


    # change some stop words to NOT stop words, cause it could say about issue of ticket
    customize_stop_words_to_not_stop = [
    #     'Above', 'above', 'below', 'Below', 'Down', 'DOWN', 'down',
    #     'cannot', 'no', 'No', 'NOT', 'not', 'Not',
    #     'off', 'on', 'up', 'Call', 'top', 'DID',
    #     'Last', 'least', 'empty', 'out', 'Used', 'used'
    ]

    # change some NOT stop words to stop words, cause it not help for clustering
    customize_not_stop_words_to_stop = [
        'решение'
    ]


    for w in customize_stop_words_to_not_stop:
        nlp.vocab[w].is_stop = False

    for w in customize_not_stop_words_to_stop:
        nlp.vocab[w].is_stop = True


    # def text_preprocessing(text, nlp=nlp, lowercase=False):
    #
    #     # TOKEN's kind to DELETE
    #     #
    #     # token.is_oov    # Is the token out-of-vocabulary (i.e. does it not have a word vector)
    #     # not token.has_vector
    #     # token.is_bracket
    #     # token.is_punct
    #     # token.is_stop
    #     # token.is_space
    #     # token.like_num
    #     # token.is_currency
    #     # token.like_url
    #     # token.like_url
    #
    #     text = nlp(text)
    #     lemmatized = []
    #     for token in text:
    #
    #         if (not token.is_oov) and (not token.is_bracket) and (not token.is_punct) and (not token.is_stop) and \
    #                 (not token.is_space) and (not token.like_num) and (not token.is_currency) and (not token.like_url) and \
    #                 (not token.like_url):
    #             if lowercase:
    #                 lemmatized.append(token.lemma_.strip().lower())
    #             else:
    #                 lemmatized.append(token.lemma_.strip())
    #
    #     return " ".join(lemmatized)


    # приводим текст к нижнему регистру и делаем препроцессинг
    df_tech_cats_subcats['cat_subcat_lm'] = [text_preprocessing(text.lower(), nlp=nlp, lowercase=False) for text in df_tech_cats_subcats.loc[:, 'cat_subcat']]


    # ======== Инжениринг признаков ========

    with nlp.disable_pipes():
        tech_cats_subcats_vectors = np.array([nlp(text).vector for text in df_tech_cats_subcats['cat_subcat_lm']])

    # вектор среднее по всем категориям
    tech_cats_subcats_vectors_mean = tech_cats_subcats_vectors.mean(axis=0)
    # центрированный вектор по категориям
    tech_cats_subcats_vectors_centr = tech_cats_subcats_vectors - tech_cats_subcats_vectors_mean


    # ======== Модель тегов ========

    # собираем словарь для поиска тегов по тренспортной тематике
    allcats_set = set()
    for cat_text in df_tech_cats_subcats['cat_subcat_lm']:
        cat_set = set(cat_text.split())
        allcats_set = allcats_set | cat_set

    # проверяем словарь тегов на наличие вектора
    allcats_set_withvect = set()
    for cat_text in allcats_set:
        if nlp(cat_text).has_vector:
            allcats_set_withvect.add(cat_text)

    # сохрянем словарь тегов в датафрейм
    df_topics_words = pd.DataFrame(allcats_set_withvect, columns=['word']).sort_values(by='word').reset_index(drop=True)

    # получем вектор признаков для словаря тегов (по каждому слову отдельно)
    with nlp.disable_pipes():
        topics_words_vectors = np.array([nlp(text).vector for text in df_topics_words['word']])




    # ======== СОХРАНЯЕМ состояние модели на диск ========
    # сохраняем как кортеж (
    #   nlp - модель,
    #   tech_cats_subcats_vectors_centr - центрированные векторы категорий и подкатегорий
    #   df_tech_cats_subcats - датафрейм с категориями и подкатегориями

    model_cort = (nlp, tech_cats_subcats_vectors_centr, tech_cats_subcats_vectors_mean, df_tech_cats_subcats, topics_words_vectors, df_topics_words)

    save_obj(model_cort, model_pickle)


    # ======== ВЫВОД ИНФОРМАЦИИ ========

    print(f'Модель {MODEL} обучена и сохранена на диск')

    return model_cort


#############################################################


def load_model():
    """Загружает модель если она есть на диске или обучает модель если ее нет"""

    if os.path.exists(model_pickle + '.pkl'):
        model_cort = load_obj(model_pickle)
    else:
        model_cort = train_model()

    return model_cort


#############################################################


def model_prediction(model_cort, request_dict):
    """Модель по входящему тексту находит самую подходящую подкатегорию"""

    # распаковка модели
    nlp, tech_cats_subcats_vectors_centr, tech_cats_subcats_vectors_mean, df_tech_cats_subcats, _, _ = model_cort

    # загрузка данных запроса в DataFrame
    text = request_dict['text']
    text_vector = np.array([nlp(text).vector]) - tech_cats_subcats_vectors_mean

    cs = cosine_similarity(tech_cats_subcats_vectors_centr, text_vector)

    maxidx_in_cs = cs.ravel().argmax()

    # return str(df_tech_cats_subcats.loc[maxidx_in_cs, :].values())
    return str(maxidx_in_cs)


#############################################################


def model_gettags(model_cort, request_dict):
    """Модель по входящему тексту возвращает список тегов транспортной тематики"""

    # порог отсечения похожих слов
    THRESHOLD = 0.7
    # максимальное кол-во тегов
    TAG_COUNT_MAX = 20

    # распаковка модели
    nlp, tech_cats_subcats_vectors_centr, tech_cats_subcats_vectors_mean, df_tech_cats_subcats, topics_words_vectors, df_topics_words = model_cort

    # загрузка данных запроса в DataFrame
    text = request_dict['text']

    # препроцессинг текста запроса
    text = text_preprocessing(text.lower(), nlp=nlp, lowercase=False)

    # находим множество слов запроса у которых есть вектор
    text_set = set(text.split())
    text_set_withvect = set()
    for word in text_set:
        if nlp(word).has_vector:
            text_set_withvect.add(word)

    # датафрейм слов запроса
    df_text_words = pd.DataFrame(text_set_withvect, columns=['word']).sort_values(by='word').reset_index(drop=True)

    # вектор признаков для слов запроса
    with nlp.disable_pipes():
        text_words_vectors = np.array([nlp(text).vector for text in df_text_words['word']])

    # вычисляем матрицу похожести слов из транспортного словаря и слов из запроса
    similarity_matrix = cosine_similarity(topics_words_vectors, text_words_vectors)

    # датафрейм похожести
    df_similarity_matrix = pd.DataFrame(similarity_matrix, index=df_topics_words['word'], columns=df_text_words['word'])

    # функция возвращает n максимально похожих слов
    def largest_indices(ary, n):
        """Returns the n largest indices from a numpy array."""
        flat = ary.flatten()
        indices = np.argpartition(flat, -n)[-n:]
        indices = indices[np.argsort(-flat[indices])]
        return np.unravel_index(indices, ary.shape)

    # находим индексы максимально похожих слов
    larg_indx = largest_indices(df_similarity_matrix.values, TAG_COUNT_MAX)

    # отсекаем кол-во найденных совпадений слов по THRESHOLD
    threshold_count = df_similarity_matrix.values[larg_indx][df_similarity_matrix.values[larg_indx] > THRESHOLD].shape[0]

    # уникальные индексы слов запросы после отсечения по THRESHOLD
    larg_indx_text = np.unique(larg_indx[1][:threshold_count])

    # возвращаем список тегов (слов из запроса)
    tags_list = list(df_similarity_matrix.columns[larg_indx_text])
    # return str(tags_list)

    # возвращаем строку тегов
    return ' '.join(['#' + word for word in tags_list])

