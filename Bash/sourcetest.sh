#!/usr/bin/env bash

# Handles errors
# $1 - Error message
# $2 - Exit code
error() {
    echo "Error: ${1:-"Unknown error"}"
    usage
    exit ${2:-1}
} >&2

# Returns 0 if argument is a number
isnum () {
    declare -r num_exp='^[0-9]+$'
    declare -r octal_exp='^0(.*)'

    num_error='ok'

    if [[ $1 =~ $num_exp ]]; then
        if [[ $1 =~ $octal_exp ]]; then
            error "${1} is not a number, did you mean ${BASH_REMATCH[1]}?" 1
        fi
    else
        error "${1} is not a number" 1
    fi

    return 0
}
