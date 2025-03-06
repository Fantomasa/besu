#!/bin/bash

for i in {1..100}
do
  echo "Sending transaction $i"
  
  TX_HASH=$(cast send --rpc-url http://127.0.0.1:8545 \
    --private-key 0xae6ae8e5ccbfb04590405997ee2d52d2b330726137b875053c36d94e974d162f \
    0xf17f52151EbEF6C7334FAD080c5704D77216b732 \
    --value 0.1ether \
    --gas-price 1000000000 \
    --gas-limit 22000)

  echo "Transaction hash: $TX_HASH"
done

echo "All transactions sent and mined."