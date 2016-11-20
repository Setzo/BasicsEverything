#!/usr/bin/env bash

echo $#

for var in "$@"; do
    echo "${var}"
done

for var in "$*"; do
    echo "${var}"
done
