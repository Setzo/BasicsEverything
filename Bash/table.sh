#!/usr/bin/env bash

drawLine() {

    local line=''
    local delim="$1"
    for (( i = 0; i < $2; i++ )); do
        line="${line}${delim}"
    done
    printf "%s\n" "$line"
}

[[ ! $1 ]] && exit 0

declare -i len="${#2} + 4"
drawLine "$1" len
printf "${1} %s ${1}\n" "$2"
drawLine "$1" len
