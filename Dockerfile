FROM postgres:latest

ENV POSTGRES_USER=developer
ENV POSTGRES_PASSWORD=1234567
ENV POSTGRES_DB=projetocarrinho

COPY src/main/resources/script.txt /docker-entrypoint-initdb.d/

EXPOSE 5432 