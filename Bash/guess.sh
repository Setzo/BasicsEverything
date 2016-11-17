#!/usr/bin/env bash

# Random number
target=$(($RANDOM % 100))

# Initialize empty var
guess=

until [[ $guess -eq $target ]]; do
    read -p "Guess: " guess
    if [[ $guess -lt $target ]]; then
        echo 'Higher'
    elif [[ $guess -gt $target ]]; then
        echo 'Lower'
    else
        echo 'Correct'
    fi
done

exit 0
