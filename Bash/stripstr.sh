#!/usr/bin/env bash

# Prints standard input to standard output
# discards all lines before the first line matching
# the string given as first argument

if [[ ! $1 ]]; then
    echo 'Need first argument' >&2
    exit 1
fi

## Read till first match, don't print unless match
#while read -r; do
#    if [[ $REPLY =~ $1 ]]; then
#        printf "%s\n" "$REPLY"
#        break
#    fi
#done
#
## Print rest of the file.
#while read -r; do
#    printf "%s\n" "$REPLY"
#done
#
#exit 0

found=''

while read -r; do
    if [[ ! $found ]]; then
        if [[ $REPLY =~ $1 ]]; then
            found='1'
        else
            continue
        fi
    fi
    printf "%s\n" "$REPLY"
done

exit 0
