complex.vector <- c(2, 2i, 2 + 2i, 4 + 5i)
character.vector <- c('str1', 'str2', 'str3', 'str4')
logical.vector <- c(TRUE, F, T, FALSE)
integer.vector <- c(1L, 2L, 3L, 4L)
numeric.vector <- c(1, .2, 3, 4.5)

# Get type of data structure.
str(complex.vector)

# Check if numeric.
is.numeric(numeric.vector)
is.numeric(integer.vector)
is.numeric(logical.vector)

# Other checks.
is.character(character.vector)
is.complex(complex.vector)
is.logical(logical.vector)

# Create complex vector.
cplx.vector <- vector("complex", length = 4)

# 1 based indexing...
character.vector[1]

# Extract element 1, 2, 3
character.vector[1:3]

# Extract values with corresponding TRUE indexes.
character.vector[logical.vector]
character.vector[c(T, T, F, T)]

# Extract values with corresponding TRUE inexes after transformation.
character.vector[integer.vector >= 2]
character.vector[c(4, 5, 0, 0) >= 2]

# Coercion.
as.numeric(logical.vector)
as.character(logical.vector)
