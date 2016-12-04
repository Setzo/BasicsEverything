#!/usr/bin/env bash

# Current date
declare -r date=`date`

# Note topic
declare -r topic="$1"

# Save directory
#declare notesdir="${HOME}"
#[[ $NOTESDIR ]] && notesdir="${NOTESDIR}"

declare notesdir="${NOTESDIR:-$HOME}"

# Make sure directory exists
if [[ ! -d $notesdir ]]; then
    mkdir -- "${notesdir}}" 2> /dev/null || { echo "Cannot make directory ${notesdir}" 1>&2; exit 1; }
fi

# Filename to save as
declare -r filename="${notesdir}/${topic}notes.txt"

if [[ ! -f $filename ]]; then
    touch -- "${filename}" 2> /dev/null || { echo "Cannot create file ${filename}"; exit 1; }
fi

[[ -w $filename ]] || { echo "${notesdir} is not writable" 1>&2; exit 1; }

read -p "Your note: " note

if [[ $note ]]; then
    echo "${date}: ${note}" >> "${filename}"
    echo "Note '${note}' saved to ${filename}" 1>&2
else
    echo "No input. Note wasn't saved"
    exit 2
fi

exit 0
