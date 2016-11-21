#!/usr/bin/env bash

PS3='Whats 4*5: ' # Sets the prompt string.
echo

select result in 2 4 5 10 20
do
    if (( result == 20 )); then
        echo 'correct'
        exit 0
    else
        echo 'nope'
        exit 1
    fi
    break
done

exit
