#!/bin/sh

# Install ApacheBench:
ab -V || (echo Install ApacheBench for Ubuntu && sudo apt-get install apache2)

myArray="groovy jsp freemarker velocity thymeleaf jade scalate mustache pebble handlebars jtwig httl chunk htmlFlow trimou rocker ickenham rythm liqp kotlinx ujorm"
echo "$myArray" | tr ' ' '\n' | while read item; do
  result=`ab -q -n 100 -c 10 -s 120 http://localhost:8080/$item | grep "Time taken for tests"`
  echo $item $result
done

