#!/usr/bin/env bash

while read -r || [[ $REPLY ]]; do
    printf "%s\n" "$REPLY"
done < "$1"
