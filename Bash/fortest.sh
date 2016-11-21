#!/usr/bin/env bash

echo $#

# if no parameter specified will look at $@
for var do
    echo "e ${var}"
done

for var in "$@"; do
    echo "@ ${var}"
done

for var in "$*"; do
    echo "* ${var}"
done
