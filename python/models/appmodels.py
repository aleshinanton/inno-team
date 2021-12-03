import os
from flask import Flask, request

import model_test_grantstatus
import model_test_grantstatusproba
import model_techcat


##############################################################################


app = Flask(__name__)

@app.route('/predict', methods=['POST', 'GET'])
def route_predict():
    prediction = 'closed'
    # if request.method == 'POST':
    #     request_data = request.get_json()
    #     request_dict = dict(request_data)
    #     # ТРАНСФОРМАЦИЯ булевых полей в 0 и 1
    #     request_dict['has_VC'] = int(request_dict['has_VC'] * 1)
    #     request_dict['is_top500'] = int(request_dict['is_top500'] * 1)
    #     request_ndarray = pd.Series(request_dict).values[:-1]
    #     # делаем предсказание
    #     prediction = model_prediction(request_ndarray)
    # elif request.method == 'GET':
    #     prediction = "closed"
    return prediction


@app.route('/predict/test_grantstatus', methods=['POST', 'GET'])
def route_predict_test_grantstatus():
    prediction = ''
    if request.method == 'POST':
        # преобразуем запрос в словарь
        request_data = request.get_json()
        request_dict = dict(request_data)
        # делаем предсказание
        prediction = model_test_grantstatus.model_prediction(MODEL_TEST_GRANTSTATUS, request_dict)
    elif request.method == 'GET':
        prediction = "ERROR: use POST method"
    return prediction


@app.route('/predict/test_grantstatusproba', methods=['POST', 'GET'])
def route_predict_test_grantstatusproba():
    prediction = ''
    if request.method == 'POST':
        # преобразуем запрос в словарь
        request_data = request.get_json()
        request_dict = dict(request_data)
        # делаем предсказание
        prediction = model_test_grantstatusproba.model_prediction(MODEL_TEST_GRANTSTATUSPROBA, request_dict)
    elif request.method == 'GET':
        prediction = "ERROR: use POST method"
    return prediction


@app.route('/predict/techcat', methods=['POST', 'GET'])
def route_predict_techcat():
    prediction = ''
    if request.method == 'POST':
        # преобразуем запрос в словарь
        request_data = request.get_json()
        request_dict = dict(request_data)
        # делаем предсказание
        prediction = model_techcat.model_prediction(MODEL_TECHCAT, request_dict)
    elif request.method == 'GET':
        prediction = "ERROR: use POST method"
    return prediction


@app.route('/predict/gettags', methods=['POST', 'GET'])
def route_predict_gettags():
    prediction = ''
    if request.method == 'POST':
        # преобразуем запрос в словарь
        request_data = request.get_json()
        request_dict = dict(request_data)
        # делаем предсказание
        prediction = model_techcat.model_gettags(MODEL_TECHCAT, request_dict)
    elif request.method == 'GET':
        prediction = "ERROR: use POST method"
    return prediction


##############################################################################


if __name__ == '__main__':

    # загрузка моделей
    MODEL_TEST_GRANTSTATUS = model_test_grantstatus.load_model()
    MODEL_TEST_GRANTSTATUSPROBA = model_test_grantstatusproba.load_model()
    MODEL_TECHCAT = model_techcat.load_model()

    port = int(os.environ.get('PORT', 1937))
    app.run(debug=True, host='0.0.0.0', port=port)

