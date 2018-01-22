#!/bin/bash
cd ./kickoff/
lein clean
lein uberjar

cd ../simple/
lein clean
lein uberjar

cd ../special/
lein clean
lein uberjar

cd ..

mkdir -p ./build/
cp ./kickoff/target/kickoff-0.1.0-standalone.jar ./build/
cp ./simple/target/simple-0.1.0-standalone.jar ./build/
cp ./special/target/special-0.1.0-standalone.jar ./build/
