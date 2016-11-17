#!/usr/bin/env bash

echo -n "Are you sure (Y/N)?"
answered=
while [[ ! $answered ]]; do

    read -rsn1 answer
    if [[ $answer = [Yy] ]]; then
        answered='yes'
    elif [[ $answer = [Nn] ]]; then
        answered='no'
    fi
done

printf "\n%s\n" $answered
