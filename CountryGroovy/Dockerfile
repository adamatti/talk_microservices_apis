FROM gradle:5.6.3-jdk11 as builder

RUN mkdir /app
WORKDIR /app

COPY build.gradle .
COPY src/ ./src

RUN gradle install --no-daemon

##################################################################3
FROM openjdk:jdk

COPY --from=builder /app /app
WORKDIR /app

EXPOSE 8081

CMD /app/build/install/CountryGroovy/bin/CountryGroovy