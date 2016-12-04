#!/usr/bin/env bash

if [[ $1 == '-l' ]]; then
    # Redirect output to logfile
    exec > log.txt
fi

declare -i i=0

while true; do
    echo "Round: $(( ++i ))"
    sleep 1
done
