#!/usr/bin/env bash

sum() {
    echo $(( $1 + $2 ))
}

exitCodeSum() {
    return $(( $1 + $2 ))
}

sum 4 5

exitCodeSum 4 5

echo $? # result of `exitCodeSum 4 5`

startsWithA() {
    [[ $1 == [aA]* ]];
#    return $? - if not present will return $? by default, similar behaviour of exit
}

startsWithA asad
echo "asad: $?"
startsWithA vsdf
echo "vsdf: $?"

if startsWithA aaaa; then
    echo 'yup'
else
    echo 'nope'
fi
