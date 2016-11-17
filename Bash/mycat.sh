#!/usr/bin/env bash

while read -r; do
    printf "%s\n" "$REPLY"
done < "$1"
