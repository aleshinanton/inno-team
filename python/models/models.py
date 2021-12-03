import os.path
import numpy as np
import pandas as pd
import pickle

from sklearn.preprocessing import OneHotEncoder
from sklearn.ensemble import RandomForestClassifier

# import warnings
# warnings.filterwarnings('ignore')



# сохранение объекта в файл пикл
def save_obj(obj, name):
    # имя и путь для сохранения файла
    f_name = name + '.pkl'
    with open(f_name, 'wb') as f:
        pickle.dump(obj, f, pickle.HIGHEST_PROTOCOL)

# загрузка объекта из файла пикл
def load_obj(name):
    with open(name + '.pkl', 'rb') as f:
        return pickle.load(f)


# делаем предсказание
def model_prediction(data_pred: np.ndarray):
    # проверка на размерность входных данных
    if data_pred.shape == (10,):
        if os.path.exists('OH_encoder.pkl') and os.path.exists('modelRFC.pkl'):
            # загружаем инкодер и модель
            OH_encoder = load_obj('OH_encoder')
            model = load_obj('modelRFC')
            # ПРЕПРОЦЕССИНГ
            OH_cols_data = OH_encoder.transform(np.array(data_pred[0]).reshape(1, -1))
            X = np.hstack((OH_cols_data.ravel(), data_pred[1:])).reshape(1, -1)
            # делаем предсказание
            prediction = pd.Series(model.predict(X), name='status').map({1: 'acquired', 0: 'closed'})[0]
        else:
            prediction = 'ЕRROR: no model to load'
    else:
        prediction = 'ЕRROR: bad shape of input data'
    return prediction
