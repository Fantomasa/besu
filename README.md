# Besu QBFT Network

This repository provides a configurable Besu QBFT blockchain network setup. You can run the network in two ways:

1. **Manually** by installing Java and running nodes one by one.
2. **Using Docker** with Docker Compose.

## Prerequisites

Depending on the setup method you choose, ensure you have the necessary dependencies installed. Ensure the necessary ports are not in use before starting the network:

- `8545`, `8546`, `8547`, `8548`
- `30303`, `30304`, `30305`, `30306`

### **Option 1: Running Manually (Java Installation Required)**

#### **1. Install Java (v21)**

Download java JDK: https://www.oracle.com/java/technologies/downloads/#java21

```sh
sudo apt update && sudo apt install -y openjdk-21-jdk
```

#### **2. Clone the Repository**

```sh
git clone https://github.com/Fantomasa/besu.git
cd besu
```

#### **3. Build Besu**

```sh
./gradlew installDist
```

#### **4. Start Nodes One by One**

Run each node in a separate terminal:

`Node1:`

```sh
cd /besu/qbft-network/node-1
```

```sh
./build/install/besu/bin/besu \
  --config-file=./config.toml \
  -- bootnodes=enode://46d8c978dafa9094b3d8dc7eee1acf49dae9cec66a0fe2e729d3ef5447d56cc50c2a8515ea9432c825d19180c5eb1dea0b8175f29c7b1c448b5fc44f34760571@127.0.0.1:30303
```

Repeat this for each node (`node-2`, `node-3`, `node-4`), changing the paths accordingly.

### **Option 2: Running with Docker and Docker Compose**

Make sure the network: `172.20.0.0/24` is not in use.

#### **1. Install Docker Engine and Docker Compose**

- Install Docker Engine: [Official Installation Guide](https://docs.docker.com/engine)
- Verify Docker installation:

```sh
docker --version
```

#### **2. Clone the Repository**

```sh
git clone https://github.com/Fantomasa/besu.git
cd besu
```

#### **3. Build and Start the Network**

```sh
docker compose up --build
```

This will build and start all nodes automatically.

#### **4. Stop the Network**

To stop and remove all running containers:

```sh
docker compose down
```

## **Verifying the Network**

You can check if nodes are running correctly by making an RPC call:

```sh
curl -X POST --data '{"jsonrpc":"2.0","method":"eth_chainId","params":[],"id":1}' \
     -H "Content-Type: application/json" http://localhost:8545
```

If everything is set up correctly, it should return a valid chain ID.

`Logs:`

```sh
node4-1  | 2025-03-10 07:22:17.010+00:00 | BftProcessorExecutor-QBFT-0 | INFO  | QbftBesuControllerBuilder | Produced empty block #83 / 0 tx / 0 pending / 0 (0.0%) gas / (0xc7a2a009be467e807b077c3351f9c6e5f1a43fb6159debf067d4b522e2cd8655)
node3-1  | 2025-03-10 07:22:19.009+00:00 | BftProcessorExecutor-QBFT-0 | INFO  | QbftBesuControllerBuilder | Imported empty block #84 / 0 tx / 0 pending / 0 (0.0%) gas / (0x503c3532509da0b326b2f15e2e9222e4e570f6d2736a705eea7caf59fe9f962c)
node1-1  | 2025-03-10 07:22:19.009+00:00 | BftProcessorExecutor-QBFT-0 | INFO  | QbftBesuControllerBuilder | Imported empty block #84 / 0 tx / 0 pending / 0 (0.0%) gas / (0x503c3532509da0b326b2f15e2e9222e4e570f6d2736a705eea7caf59fe9f962c)
node2-1  | 2025-03-10 07:22:19.009+00:00 | BftProcessorExecutor-QBFT-0 | INFO  | QbftBesuControllerBuilder | Produced empty block #84 / 0 tx / 0 pending / 0 (0.0%) gas / (0x503c3532509da0b326b2f15e2e9222e4e570f6d2736a705eea7caf59fe9f962c)
node4-1  | 2025-03-10 07:22:19.009+00:00 | BftProcessorExecutor-QBFT-0 | INFO  | QbftBesuControllerBuilder | Imported empty block #84 / 0 tx / 0 pending / 0 (0.0%) gas / (0x503c3532509da0b326b2f15e2e9222e4e570f6d2736a705eea7caf59fe9f962c)
node3-1  | 2025-03-10 07:22:21.009+00:00 | BftProcessorExecutor-QBFT-0 | INFO  | QbftBesuControllerBuilder | Imported empty block #85 / 0 tx / 0 pending / 0 (0.0%) gas / (0x292204550b8b165174317c953a721d7e734d6fb732a191c093ed64d2e1bac877)
node4-1  | 2025-03-10 07:22:21.009+00:00 | BftProcessorExecutor-QBFT-0 | INFO  | QbftBesuControllerBuilder | Imported empty block #85 / 0 tx / 0 pending / 0 (0.0%) gas / (0x292204550b8b165174317c953a721d7e734d6fb732a191c093ed64d2e1bac877)
```

## **Contributing**

Feel free to open an issue or submit a pull request if you find any bugs or want to improve this setup.
