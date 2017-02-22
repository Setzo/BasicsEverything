complex.vector <- c(2, 2i, 2 + 2i, 4 + 5i)
character.vector <- c('str1', 'str2', 'str3', 'str4')
logical.vector <- c(TRUE, F, T, FALSE)
integer.vector <- c(1L, 2L, 3L, 4L)
numeric.vector <- c(1, .2, 3, 4.5)

# List.
test.list <-
  list(
    complex.vector[1],
    character.vector[1],
    logical.vector[1],
    integer.vector[1],
    numeric.vector[1],
    complex.vector
  )

str(test.list)
test.list

# Assoc list.
named.list <-
  list(
    complex = complex.vector[1],
    character = character.vector[1],
    logical = logical.vector[1],
    integer = integer.vector[1],
    numeric = numeric.vector[1]
  )

# Structure
str(named.list)
named.list

# List type.
test.list[1]
typeof(test.list[1])

# Element type.
test.list[[1]]
typeof(test.list[[1]])

# Multiple elements.
test.list[1:3]

# Accessing assoc values.
named.list$character
named.list[['complex']]
named.list[c('complex', 'integer')]

length(named.list)
