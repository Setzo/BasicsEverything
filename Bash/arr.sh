#!/usr/bin/env bash

declare -a arr=(1 2 3 4 5)

arr2=(1 2 3 4 5 6 7 8)

echo "arr2 values:  ${arr2[@]}"
echo "arr2 indices: ${!arr2[@]}"
echo "arr2 length:  ${#arr2[@]}"

# Bash4+ only
declare -A assocArray
