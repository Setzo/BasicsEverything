#!/usr/bin/env bash

declare -i count=0

count_lines() {

    while read -r; do
        (( ++count ))
    done;
}

$* | count_lines

echo "$count"
