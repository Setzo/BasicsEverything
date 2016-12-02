#!/usr/bin/env bash

# Script that shows histogram os used space by the
# directories in current working directory

# Print error
error() {
    echo "Error: ${1:-"Unknown error"}"
    exit ${2:-1}
} >&2


# Create tmp file
mk_temp() {
    mktemp || mktemp -t hist
} 2> /dev/null

# Check that we are using bash version 4+.
(( BASH_VERSINFO[0] < 4 )) && error "Bash version 4.x+ needed" 1

# Keep file sizes in an array
declare -A file_sizes
declare -r tempfile=$(mk_temp) || error "Cannot create tempfile" 2

# How wide is the terminal
declare -ir term_cols=$(tput cols)

# Longest file name, Largest filem, Total file size
declare -i max_name_len=0 max_size=0 total_size=0

# Draws line
drawline() {
    declare line=""
    declare char="-"
    for (( i = 0; i < $1; ++i )); do
        line="${line}${char}"
    done
    printf "%s" "$line"
}

# Reads output from du into array
read_filesizes() {
    while read -r size name; do
        file_sizes["$name"]="$size"
        (( total_size += size ))
        (( max_size < size )) && (( max_size = size ))
        (( max_name_len < ${#name} )) && (( max_name_len=${#name} ))
    done
}

# Store directory files information in temporary file
# Parse that file into associative array
{ du -d 0 */ || du --max-depth 0 *; } 2> /dev/null > "$tempfile"
read_filesizes < "$tempfile"

# Length of each line and percentage for each file
declare -i length percentage

# How many columns does line take
declare -i cols="term_cols - max_name_len - 10"

for k in "${!file_sizes[@]}"; do
    (( length = cols * file_sizes[$k] / max_size ))
    (( percentage = 100 * file_sizes[$k] / total_size ))
    printf "%-${max_name_len}s | %3d%% | %s\n" "$k" "$percentage" $(drawline "$length")
done

printf "%d Directories\n" "${#file_sizes[@]}"
printf "Total size: %d blocks\n" "$total_size"

# Remove temp file
rm "$tempfile"
exit 0
