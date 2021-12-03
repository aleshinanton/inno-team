FROM python:3.6-alpine

COPY . /appchatbot
WORKDIR /appchatbot

EXPOSE 5000

RUN pip install -r requirements.txt

ENTRYPOINT ["python"]
CMD ["appchatbot.py"]