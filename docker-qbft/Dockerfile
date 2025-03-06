FROM ubuntu:24.04

RUN apt-get update && apt-get upgrade -y

RUN apt-get install -y --no-install-recommends \
  openjdk-21-jdk \
  git \
  curl \
  unzip \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /app

RUN git clone https://github.com/hyperledger/besu

WORKDIR /app/besu

RUN chmod +x gradlew && ./gradlew installDist

CMD ["./build/install/besu/bin/besu", "--config-file=./config.toml"]