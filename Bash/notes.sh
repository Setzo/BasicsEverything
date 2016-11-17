#!/usr/bin/env bash

# Get current date.
date=`date`

# Get filename.
filename="$HOME/$1notes.txt"

# Set debug flag.
set -x
# Read note.
read -p 'Your note: ' note
#unset debug flag.
set +x

if [[ "$note" ]]; then

    # Save note.
    echo "$date: $note" >> "$filename"
    echo "Note '${note}' saved to $filename."
else
    echo "No input. Note wasn't saved."
fi
