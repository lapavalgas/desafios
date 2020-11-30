# Imagem de Origem
FROM node:14-alpine

# diretório de trabalho no interior do container
WORKDIR /appfront

# copy packages
COPY ./ /appfront

# installing packages
RUN npm install 

# adicionando `/app/node_modules/.bin` para o $PATH
ENV PATH="./frontend/node_modules/.bin:$PATH"

# react
RUN npm install react-scripts@3.3.1 -g --silent

# configs
RUN npm run build

# start app
CMD ["npm", "start"]