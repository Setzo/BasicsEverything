#!/usr/bin/env bash

for file in `find . -name '*.py'`; do
    file="`realpath $file`"
    declare file_path="${file%/*}"
    declare file_name="${file##*/}"
    declare no_dash_file_name="${file_path}/${file_name//-/_}"
    mv "${file_path}/${file_name}" "$no_dash_file_name"
done
