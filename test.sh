#!/bin/bash

for i in {1..100}
do
  echo "Sending transaction $i"
  
  TX_HASH=$(cast send --rpc-url http://127.0.0.1:8545 \
    --private-key 0xc87509a1c067bbde78beb793e6fa76530b6382a4c0241e5e4a9ec0a0f44dc0d3 \
    0xFE3B557E8Fb62b89F4916B721be55cEb828dBd73 \
    --value 1ether \
    --gas-price 1000000000 \
    --gas-limit 22000)

  echo "Transaction hash: $TX_HASH"
done

echo "All transactions sent and mined."