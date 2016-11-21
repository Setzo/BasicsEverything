#!/usr/bin/env bash

param="${nullvar-"test"}"
echo "- ${param}"

param="${nullvar:-"test"}"
echo ":- ${param}"

echo '--------'
echo 'nullvar'
echo '--------'

nullvar=
param="${nullvar-"test"}"
echo "- ${param}"

param="${nullvar:-"test"}"
echo ":- ${param}"

echo '--------'
echo 'default'
echo '--------'

echo "${var="xxx"}"
echo "${var="zzz"}" # var equals xxx at this point,
                    # thus not changing its value to zzz

# Num of parameters.
# echo ${#@}
# echo ${#*}

# Num of elements in array
# echo ${#array[@]}
# echo ${#array[*]}

echo "${PWD##*/}" # Current working dir basename
echo "${0##*/}" # Script name

txz1=5
txz2=3
txz3=1
atxz4=66

echo "${!txz@}" # Expands to txz* of declared variables.
echo "${!txz*}" # Expands to txz* of declared variables.

echo ${var?No parameter} # xxx
# echo ${p?} # error, exit 1
# : ${a?} - good way to check for param +- null function
echo ${parameter?No parameter} # parameter not present, so print 'No parameter ' and exit 1

exit 0
