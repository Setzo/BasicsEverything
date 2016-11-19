#!/usr/bin/env bash

# Check number of arguments.
if [[ $# -ne 2 ]]; then
    echo "Need exactly two arguments."
    exit 1
fi

# Both args should be directories.
if [[ ! -d $1 ]]; then
    echo "Arg 1 is not directory"
fi

if [[ ! -d $2 ]]; then
    echo "Arg 2 is not directory"
fi

cFilesD1=`ls -A "$1" | wc -l`
cFilesD2=`ls -A "$2" | wc -l`

if [[ $cFilesD1 -gt $cFilesD2 ]]; then
    echo "${1} has more files."
elif [[ $cFilesD1 -lt $cFilesD2 ]]; then
    echo "${2} has more files."
else
    echo "Equal number of files."
fi

exit 0
