FROM ubuntu:24.04

RUN apt-get update && apt-get upgrade -y

RUN apt-get install -y --no-install-recommends \
  openjdk-21-jdk \
  git \
  curl \
  unzip \
  wget

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew installDist