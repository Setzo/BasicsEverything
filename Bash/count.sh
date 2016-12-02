#!/usr/bin/env bash

# Script printing range of numbers

usage() {

    cat <<END
count [-r] [-b n] [-s n] stop

Print each number up to stop, beginning at 0
        -b: number to begin with (default: 0)
        -h: show this help message
        -r: reverses the count
        -s: sets step size (default: 1)
END
}

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
    [[ $1 =~ ^[0-9]+$ ]]
}

declare reverse=""
declare -i begin=0
declare -i step=1

# OPTARG will contain option argument (if any given).
# ":b:s:r"
# : - start in silent mode
# b: - option with parameter
# s: - option with parameter
# r - option withour parameter
#
# :) missing option argument option name
# in OPTARG
#
# \?) actual question mark returned -
# indication that option is unknown
# option name in OPTARG
#
while getopts ":hb:s:r" opt; do
    case "$opt" in
    h)
        usage
        exit 0
        ;;
    r)
        reverse="yes"
       ;;
    b)
        isnum "${OPTARG}" || error "${OPTARG} is not a number" 1
        begin="${OPTARG}"
        ;;
    s)
        isnum "${OPTARG}" || error "${OPTARG} is not a number" 1
        step="${OPTARG}"
        ;;
    :)
        error "Option -${OPTARG} is missing an argument" 1
        ;;
    \?)
        error "Unknown option: -${OPTARG}" 1
        ;;
    esac
done

# OPTIND contains index of next argument to be processed.
shift $(( OPTIND - 1 ))

[[ $1 ]] || error "Missing an argument" 1
declare end="${1}"

operator="+="
condition="<="

if [[ $reverse ]]; then
    tmp="${begin}"
    begin="${end}"
    end="${tmp}"
    operator="-="
    condition=">="
fi

for (( i=begin; i $condition end; i $operator step)) do
    echo $i;
done;
