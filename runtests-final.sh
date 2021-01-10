#!/bin/sh
# The final test

# Install the ApacheBench:
ab -V || (echo Install ApacheBench for Ubuntu && sudo apt-get install apache2)

myArray="groovy jsp freemarker velocity thymeleaf jade scalate mustache pebble handlebars jtwig httl chunk htmlFlow trimou rocker ickenham rythm liqp kotlinx ujorm"
echo "$myArray" | tr ' ' '\n' | while read item; do
        echo Warming up: $item
        result=`ab -q -n 25000 -c 25 -s 120 http://localhost:8080/$item | grep "Time taken for tests"`
done

for i in $(seq 1 10); do
    echo "=== An iteration: #$i"
    echo "$myArray" | tr ' ' '\n' | while read item; do
        result=`ab -q -n 25000 -c 25 -s 120 http://localhost:8080/$item | grep "Time taken for tests"`
        echo $item $result
    done
done

