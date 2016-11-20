#!/usr/bin/env bash

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
while getopts ":b:s:r" opt; do
    case "$opt" in
    r)
        reverse="yes"
       ;;
    b)
        [[ ${OPTARG} =~ ^-?[0-9]+$ ]] || { echo "${OPTARG} is not a number" >&2; exit 1; }
        begin="${OPTARG}"
        ;;
    s)
        [[ ${OPTARG} =~ ^-?[0-9]+$ ]] || { echo "${OPTARG} is not a number" >&2; exit 1; }
        step="${OPTARG}"
        ;;
    :)
        echo "Option -${OPTARG} is missing an argument" >&2
        exit 1
        ;;
    \?)
        echo "Unknown option: -${OPTARG}" >&2
        exit 1
        ;;
    esac
done

# OPTIND contains index of next argument to be processed.
shift $(( OPTIND - 1 ))

[[ $1 ]] || { echo "Missing an argument" >&2; exit 1; }
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
