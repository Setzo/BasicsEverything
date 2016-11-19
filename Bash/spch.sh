#!/usr/bin/env bash

# Bash builtin, always returns true.
: # Exit code 0

# Will create new empty file
# : > file

echo "\n"
echo $0
echo `basename $0`

echo $?

echo "Process id: ${$}"

cat <<< 'string'

echo $(( $? == 0 ? 1 : 2 ))
echo $(( $? ? 1 : 2 ))

cd /home/

# Current working directory
echo ~+

# Previous working directory
echo ~-

# CTRL D to stop
#cat - > file.txt
