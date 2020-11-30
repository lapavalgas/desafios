# Imagem de Origem
FROM maven:3.6.3-openjdk-11-slim

# diretório de trabalho no interior do container
WORKDIR /appback

# copy packages
COPY ./ /appback

# running spring
CMD ["mvn", "spring-boot:run"]