#!/bin/sh
# The final test

# Install the ApacheBench:
ab -V || (echo Install ApacheBench for Ubuntu && sudo apt-get install apache2)

for i in $(seq 1 10); do
    echo "### An iteration: #$i [$(date +%Y-%m-%d,%H:%M:%S)]"
    echo "$myArray" | tr ' ' '\n' | while read item; do
        result=`ab -q -n 25000 -c 25 -s 120 http://localhost:8080/$item | grep "Time taken for tests"`
        echo $item $result
    done
done
echo "### Done [$(date +%Y-%m-%d,%H:%M:%S)]"

