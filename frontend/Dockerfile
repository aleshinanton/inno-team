FROM node:14.14.0

EXPOSE 8080

COPY . /app
WORKDIR /app

RUN npm install

CMD ["npm", "run", "serve"]