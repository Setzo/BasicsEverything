#!/usr/bin/env bash

# Bash builtin, always returns true.
: # Exit code 0

# Will create new empty file
# : > file

echo "\n"
echo $0
echo `basename $0`

echo $?

# Same as `:`
true
# Contradiction to `true`, exit code 1
false

echo "Process id: ${$}"

# -- - end of command params

cat <<< 'string'

echo $(( $? == 0 ? 1 : 2 ))
echo $(( $? ? 1 : 2 ))

cd /home/
sdsgvfdsgfdyu # Won't stop script execution. Will print error.
# Current working directory
echo ~+

# Previous working directory
echo ~-

# 64#ZZz - base 64 number with value of ZZz
echo $(( 64#ZZz ))

# 20#AHHH - base 20 number with value of AHHH
echo $(( 20#AHHH ))

# CTRL D to stop
#cat - > file.txt

# "$@" - "$1" "$2" "$3"..
# "$*" - "$1 $2 $3.."

# If no exit, will exit with exit status of
# last command. Equivalent to:
# exit $?
# exit $
# exit
