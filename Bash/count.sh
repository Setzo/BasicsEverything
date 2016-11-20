#!/usr/bin/env bash

declare reverse=""
declare -i begin=0
declare -i step=1

# OPTARG will contain option argument (if any given).
while getopts "b:s:r" opt; do
    case "$opt" in
    r)
        reverse="yes"
       ;;
    b)
        [[ ${OPTARG} =~ ^-?[0-9]+$ ]] || { echo "${OPTARG} is not a number" >&2; exit 1; }
        start="${OPTARG}"
        ;;
    s)
        [[ ${OPTARG} =~ ^-?[0-9]+$ ]] || { echo "${OPTARG} is not a number" >&2; exit 1; }
        step="${OPTARG}"
        ;;
    \?)
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
