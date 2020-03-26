FROM postgres

ENV POSTGRES_DB hotelcovid19
ENV POSTGRES_USER hotelcovid19
ENV POSTGRES_PASSWORD 12345

COPY init.sql /docker-entrypoint-initdb.d/10-init.sql
