#!/usr/bin/env bash

# Variable attributes are not exported to other processes
# Variables are scoped to script they are executed in
# Current working directory is also scoped to the script

# Equivalent
# declare -x test='test'
# export test='test'

# Export without immediately assigning value
# export test

#declare var='outer'
#echo "Outer before: ${var}"
#export_b.sh
#echo "Outer after: ${var}"

# Out :
# Outer before: outer
# Inner before:
# Inner after: inner
# Outer after: outer

declare -x var='outer'
echo "Outer before: ${var}"
export_b.sh
echo "Outer after: ${var}"

# Out:
# Outer before: outer
# Inner before: outer
# Inner after: inner
# Outer after: outer

exit 0
