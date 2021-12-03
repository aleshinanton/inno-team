FROM python:3.8-slim

COPY . /appmodels
WORKDIR /appmodels

EXPOSE 1937

RUN apt-get update -y \
 && apt-get install -y python3-dev build-essential
RUN python -m pip install --upgrade pip
RUN pip install --upgrade pip setuptools wheel
RUN pip install numpy
RUN pip install Cython --install-option="--no-cython-compile"
RUN pip install -r requirements.txt

ENTRYPOINT ["python"]
CMD ["appmodels.py"]