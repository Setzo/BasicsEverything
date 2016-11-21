#!/usr/bin/env bash

: ${1?"Usage: $(basename $0 .sh) ARGUMENT PATTERN REPLACEMENT"}
: ${2?"Usage: $(basename $0 .sh) ARGUMENT PATTERN REPLACEMENT"}
: ${3?"Usage: $(basename $0 .sh) ARGUMENT PATTERN REPLACEMENT"}

argument="$1"
pattern="$2"
replacement="$3"

# All of the above does not change real value of the parameter
echo "Argument: $argument"
echo "Pattern: $pattern"
echo "Replacement: $replacement"

# Removes shortest matching from front
echo "# ${argument#$pattern}"

# Removes longest matching from front
echo "## ${argument##$pattern}"

# Removes shortest matching from end
echo "% ${argument%$pattern}"

# Removes longest matching from end
echo "%% ${argument%%$pattern}"

echo ":5 ${argument:5}" # Cut off 5 first characters
echo ":5:2 ${argument:5:2}" # Cut 2 characters from 5th one

# First match of $pattern will be replaced with $replacement
echo "/ / ${argument/$pattern/$replacement}"

# All matches of $pattern will be replaced with $replacement
echo "// / ${argument//$pattern/$replacement}"

# All matches of $pattern deleted
echo "// /$ ${argument//$pattern/}"

# If $argument starts with $pattern, replace it with $replacement
echo "/# / ${argument/#$pattern/$replacement}"

# Delete prefix if exists
echo "/# /$ ${argument/#$pattern/}"

# If $argument ends with $pattern, replace it with $replacement
echo "/% / ${argument/%$pattern/$replacement}"

# Delete suffix if exists
echo "/% /$ ${argument/%$pattern/}"

exit 0
