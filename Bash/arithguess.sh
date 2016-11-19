#!/usr/bin/env bash

# Get random number <1; 99>
declare -ir target=$(( ($RANDOM % 100) + 1 ))

declare -i guess=0

until (( guess == target )); do
    read -p "Take a guess: " guess

    # Skip 0 guesses
    (( guess )) || continue

    if (( guess < target )); then
        echo 'Higher!'
    elif (( guess > target )); then
        echo 'Lower!'
    else
        echo 'Correct!'
    fi
done

exit 0
