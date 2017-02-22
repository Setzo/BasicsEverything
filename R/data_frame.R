complex.vector <- c(2, 2i, 2 + 2i, 4 + 5i)
character.vector <- c('str1', 'str2', 'str3', 'str4')
logical.vector <- c(TRUE, F, T, FALSE)
integer.vector <- c(1L, 2L, 3L, 4L)
numeric.vector <- c(1, .2, 3, 4.5)

# Data Frame.
test.data.frame <-
  data.frame(
    complex.vector,
    character.vector,
    logical.vector,
    integer.vector,
    numeric.vector
  )

# Data Frame. Do not convert strings to Factors.
test.data.frame <-
  data.frame(
    complex.vector,
    character.vector,
    logical.vector,
    integer.vector,
    numeric.vector,
    stringsAsFactors = FALSE
  )

# List.
typeof(test.data.frame)
str(test.data.frame)

# List.
test.data.frame[1]
typeof(test.data.frame[1])

# Vector (Complex in this case).
test.data.frame[[1]]
typeof(test.data.frame[[1]])

# Vector (Logical in this case).
test.data.frame[["logical.vector"]]
test.data.frame$logical.vector

# First three columns.
test.data.frame[1:3]
# List.
typeof(test.data.frame[1:3])
# Two columns.
test.data.frame[c("complex.vector", "logical.vector")]
# First row, thrid column value.
test.data.frame[1,3]
# Two columns, three rows.
test.data.frame[1:3, 1:2]
# First and third row, first and second column.
test.data.frame[c(1,3),c(1,2)]
# First column
test.data.frame[,1]
# First row
test.data.frame[1,]
# First two rows.
test.data.frame[c(T,T,F,F,F),]
# First two rows. Fourth and fifth column.
test.data.frame[c(T,T,F,F,F),c(F,F,F,T,T)]

# Filter.
test.data.frame[character.vector == 'str3',]
# Filter.
test.data.frame$numeric.vector[numeric.vector > 2]
