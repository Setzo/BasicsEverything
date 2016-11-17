#!/usr/bin/env bash

if [[ $# -ne 1 || ! $1 ]]; then
    echo 'Needs exactly one parameter.'
    exit 1
fi

filename=`basename "$1"`
extension="${filename##*.}"
#filename="${filename%.*}"

case $extension in
    txt)
        echo 'text file';;
    java)
        echo 'java source file';;
    sh)
        echo 'shell source file';;
    *)
        echo 'file'
esac

exit 0;
