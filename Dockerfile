FROM ubuntu:24.04

WORKDIR /app

COPY . .

RUN apt-get update && apt-get upgrade -y

RUN apt-get install -y --no-install-recommends \
  openjdk-21-jdk \
  git \
  curl \
  unzip \
  wget

RUN chmod +x gradlew && ./gradlew installDist