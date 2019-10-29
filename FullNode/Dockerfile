FROM node:8.16.2

RUN mkdir /app
WORKDIR /app

COPY package.json .
RUN npm install --silent

COPY src ./src
CMD npm start