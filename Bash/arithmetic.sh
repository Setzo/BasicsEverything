#!/usr/bin/env bash

let n=100/2*10
echo $n

x=0
((++x))
echo $x

y=$((x * 0xff))
echo $y

# ((++x))  -    command, does not substitute anything
# $((++x)) -    would trigger error, bash would replace
#               this with x after increment operation

