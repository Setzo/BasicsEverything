#!/usr/bin/env bash

[[ $1 ]] || { echo 'Argument missing.' >&2; exit 1; }

# && - execute if previous one succeeded
# || - execute if previous one failed

exit 0
