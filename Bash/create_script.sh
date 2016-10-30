#!/bin/bash

if [[ ! $1 ]]; then
    echo 'Missing argument.'
    exit 1
fi

bindir="$HOME/bin"
filename="$bindir/$1"

if [[ -e "$filename" ]]; then
    echo "File $filename already exists."
    exit 1
fi
