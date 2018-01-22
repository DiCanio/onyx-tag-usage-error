#!/bin/bash

export TENANCY_ID=311b510f-411e-4d83-b3f7-90a7c0c246c6
export ZOOKEEPER_ADDR=172.17.0.1:2181

echo "Kicking off a job now...\n"
java -Daeron.dir=/tmp/aeron_job/ -jar ./build/kickoff-0.1.0-standalone.jar
