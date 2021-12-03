from pickle_model import save_obj, load_obj

import os
import pandas as pd
import configparser

from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import OneHotEncoder
from sklearn.ensemble import RandomForestClassifier

import warnings
warnings.filterwarnings('ignore')


#####################################################################


# имя раздела модели в конфигурационном файле CONFIG.ini
MODEL = 'TEST_GRANTSTATUS'


# ======== настройки из CONFIG.ini ========

CONFIG = configparser.ConfigParser()  # создаeм объект парсера
CONFIG.read('CONFIG.INI')  # читаем конфиг
TEST_GRANTSTATUS = dict(CONFIG[MODEL])

# путь к данным модели
model_path = TEST_GRANTSTATUS['model_path']
# имя модели для сохранения объекта
model_name = TEST_GRANTSTATUS['model_name']
# имя файла с данными для ОБУЧЕНИЯ модели
data_train_filename = model_path + TEST_GRANTSTATUS['data_train']
# путь и имя файла пикл
model_pickle = model_path + model_name


#####################################################################


def train_model():
    """Тренирует, сохраняет на диск и возвращает пайплайн модели."""

    # ======== ЗАГРУЗКА данных ========

    # считываем данные
    df = pd.read_csv(data_train_filename)

    # ======== ПРЕПРОЦЕССИНГ данных ========

    # отделяем целевую переменную от остальных
    y = df.status.map({'acquired': 1, 'closed': 0})
    X = df.drop(['status'], axis=1)

    # категориальные признаки
    categorical_cols = ['state_code']

    # числовые признаки
    numerical_cols = ['age_first_funding_year', 'age_last_funding_year', 'relationships', 'funding_rounds',
                      'funding_total_usd', 'milestones', 'has_VC', 'avg_participants', 'is_top500']

    # препроцессинг числовых данных - заменяем пустые значения нулями
    numerical_transformer = SimpleImputer(strategy='constant')

    # препроцессинг для категориальных данных - заменяем пустые значения на 'missing_value' и перекодируем
    categorical_transformer = Pipeline(steps=[
        ('imputer', SimpleImputer(strategy='constant')),
        ('onehot', OneHotEncoder(handle_unknown='ignore'))
    ])

    # препроцессор для числовых и категориальных признаков
    preprocessor = ColumnTransformer(
        transformers=[
            ('numerical', numerical_transformer, numerical_cols),
            ('categorical', categorical_transformer, categorical_cols)
        ])

    # модель случайного леса
    model = RandomForestClassifier(n_estimators=100, random_state=0, min_samples_leaf=5, n_jobs=-1)

    # препроцессор данных и модель
    model_pipeline = Pipeline(steps=[
        ('preprocessor', preprocessor),
        ('model', model)
    ])

    # ======== ОБУЧАЕМ модель ========

    # препроцессинг и обучение модели
    model_pipeline.fit(X, y)

    # ======== СОХРАНЯЕМ состояние модели на диск ========

    save_obj(model_pipeline, model_pickle)

    # ======== ВЫВОД ИНФОРМАЦИИ ========

    print(f'Модель {MODEL} обучена и сохранена на диск')

    return model_pipeline


#####################################################################


def load_model():
    """Загружает модель если она есть на диске или обучает модель если ее нет"""

    if os.path.exists(model_pickle + '.pkl'):
        model_pipeline = load_obj(model_pickle)
    else:
        model_pipeline = train_model()

    return model_pipeline


#####################################################################


def model_prediction(model_pipeline, request_dict):
    """Модель по входящему словарю делает одно предсказание"""

    # ТРАНФОРМАЦИЯ входящих данных
    request_dict['has_VC'] = int(request_dict['has_VC'] * 1)
    request_dict['is_top500'] = int(request_dict['is_top500'] * 1)

    # загрузка данных запроса в DataFrame
    X_pred = pd.DataFrame([request_dict]).iloc[:, :-1]

    return pd.Series(model_pipeline.predict(X_pred), name='status').map({1: 'acquired', 0: 'closed'})[0]

