#!/bin/bash



echo "---------- FIX BUG SASS 5.0 INCOMPATIBLE ----------"

echo " " 

echo "Desinstalando modulo 5.0" 

npm uninstall node-sass 

echo "Install version Node Sass 4.14"

npm install node-sass@4.14.1

echo "Waiting...."
sleep 2

echo "Iniciando Visual code"

code .

echo "Iniciando servidor web na porta 3000"

npm start
