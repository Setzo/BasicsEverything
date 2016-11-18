#!/usr/bin/env bash

#
# Extract file:
# Use: mytar filename
#
# Compress directory:
# Use: mytar dir filename
#

if [[ $# -eq 0 ]]; then
    echo 'Need at least one argument' >&2
    exit 1
fi

if [[ $# -gt 2 ]]; then
    echo 'Maximum of 2 arguments exceeded.' >&2
    exit 1
fi

if [[ ! $1 ]]; then
    echo 'First argument cannot be empty' >&2
    exit 1
fi

if [[ ! -e $1 ]]; then
    echo "File / directory '${1}' does not exist" >&2
    exit 1
fi

if [[ -d $1 ]]; then
    operation='c'
    if [[ ! $2 ]]; then
        echo 'Need name of file or directory to create as second arg.' >&2
        exit 1
    fi
    tarfile="$2"
    dir="$1"
else
    operation='x'
    tarfile="$1"
    dir=''
fi

case $tarfile in
    *.tgz|*.gz|*.gzip)
        zip='z'
        echo 'Using gzip' >&2;;
    *.bz|*.bz2|*.bzip|*.bzip2)
        zip='j'
        echo 'Using bzip2' >&2;;
    *.Z)
        zip='Z'
        echo 'Using compress' >&2;;
    *.tar)
        zip=''
        echo 'No compression used' >&2;;
    *)
        echo "Unknown extension: ${tarfile}" >&2
        exit 3;;
esac

command="tar ${operation}${zip}f $tarfile"
if [[ $dir ]]; then
    command="${command} ${dir}"
fi

if ! $command; then
    echo "Error: tar exited with status: $?" >&2
    exit $?
fi

echo 'Ok' >&2
