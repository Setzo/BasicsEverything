#!/usr/bin/env bash

set -x

if [[ ! $1 ]]; then
    echo 'Need at least one argument.'
    exit 1
fi

if type "$1" > /dev/null 2>&1; then
    echo 'Command with this name already exists.'
    exit 1
fi

exit 0
