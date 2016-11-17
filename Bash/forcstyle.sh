#!/usr/bin/env bash

if [[ $# -gt 2 ]]; then
    echo "Maximum of 2 arguments expected." >&2
    exit 1
fi

if [[ ! $1 ]]; then
    echo "Need line length argument." >&2
    exit 1
fi

if [[ $1 =~ ^[0-9]+$ ]]; then
    length="$1"
else
    echo "Argument needs to be a number." >&2
    exit 1
fi

char="="
if [[ $2 ]]; then
#    if [[ $2 =~ ^.{1}$ ]]; then
    if [[ ${#2} -eq 1 ]]; then
        char="$2"
    else
        echo "Argument 2 must be single character." >&2
        exit 1
    fi
fi

line=
for (( i = 0; i < length; ++i )); do
    line="${line}${char}"
done

printf "%s\n" "$line"

exit 0;
