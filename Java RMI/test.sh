#!/bin/bash

javac RMI_Client
echo "i 30" | java RMI_Client

for i in `seq 1 100`; do
    (echo "d 30 100" | java RMI_Client)&
    (echo "w 30 100" | java RMI_Client)&
done;

echo "i 30" | java RMI_Client
