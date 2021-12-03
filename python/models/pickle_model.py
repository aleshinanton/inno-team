import pickle


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
