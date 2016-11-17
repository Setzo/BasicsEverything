#!/usr/bin/env bash

# Check number of arguments.
if [[ $# -ne 2 ]]; then
    echo "Need exactly two arguments."
    exit 1
fi

strlen1=${#1}
strlen2=${#2}

if [[ $strlen1 -gt $strlen2 ]]; then
    echo "String one is longer."
elif [[ $strlen1 -lt $strlen2 ]]; then
    echo "String two is longer."
else
    echo "Equally long strings."
fi

exit 0
