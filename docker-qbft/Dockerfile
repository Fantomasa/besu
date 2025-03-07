FROM ubuntu:24.04

RUN apt-get update && apt-get upgrade -y

RUN apt-get install -y --no-install-recommends \
  openjdk-21-jdk \
  git \
  curl \
  unzip \
  wget \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /app

RUN git clone https://github.com/hyperledger/besu

WORKDIR /app/besu

RUN chmod +x gradlew && ./gradlew installDist

WORKDIR /app/besu/qbft-network/node

RUN mkdir data

COPY ./config/config.toml /app/besu/qbft-network/node

COPY ./config/genesis.json /app/besu/qbft-network/node

WORKDIR /app

CMD ["./build/install/besu/bin/besu", "--config-file=./config.toml"]