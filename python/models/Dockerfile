FROM python:3.9.9

COPY . /appmodels
WORKDIR /appmodels

EXPOSE 1937

RUN pip3 install --upgrade cython
RUN pip3 install -r requirements.txt

ENTRYPOINT ["python3"]
CMD ["appmodels.py"]