#!/bin/bash

export TENANCY_ID=311b510f-411e-4d83-b3f7-90a7c0c246c6
export ZOOKEEPER_ADDR=172.17.0.1:2181
export BIND_ADDR=127.0.0.1
export NUM_VPEERS=3

echo "Starting simple peers...\n"
java -Daeron.dir=/tmp/aeron_simple/ -jar ./build/simple-0.1.0-standalone.jar
