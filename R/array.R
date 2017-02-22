m11.vector <- c(1L, 2L, 3L, 4L)
m12.vector <- c(9L, 4L, 3L, 11L)

m21.vector <- c(7L, 11L, 34L, 24L)
m22.vector <- c(91L, 43L, 13L, 1L)

m1.matrix <- cbind(m11.vector, m12.vector)
m2.matrix <- cbind(m21.vector, m22.vector)

# In theory unlimites dimensions.
test.array <- array(c(m1.matrix, m2.matrix), dim = c(4, 2, 2))

m1.matrix
m2.matrix
test.array

# Row nunmber, column number, whatever number.
test.array[2,2,2]
