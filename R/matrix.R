integer.vector <- c(1L, 2L, 3L, 4L)
int.vector <- c(9L, 4L, 3L, 11L)

# Row bind.
rbind.matrix <- rbind(integer.vector, int.vector)
rbind.matrix

# Column bind.
cbind.matrix <- cbind(integer.vector, int.vector)
cbind.matrix

# Assign names to rows.
rownames(cbind.matrix) <- c('a', 'b', 'c', 'd')
# Assign names to columns.
colnames(cbind.matrix) <- c('a', 'b')

str(cbind.matrix)
cbind.matrix

# Create matrix by filling each row one value at a time.
random.matrix <- matrix(c(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), ncol = 3, nrow = 4, byrow = T)
random.matrix
# Create matrix by filling each column one value at a time.
random.matrix <- matrix(c(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), ncol = 3, nrow = 4)
random.matrix

# Everything. Equal to `random.matrix`
random.matrix[,]
# Second row, second column.
random.matrix[2,2]
# Second row.
random.matrix[2,]
# Second column.
random.matrix[,2]
# First - third value from first column.
random.matrix[1:3]
# First and second row, second and third column. CONTRADICTING with the one above.
random.matrix[1:2, 2:3]
# First and last row, interlacing each other one value by one.
random.matrix[c(T,F,F,T)]
# First and third row.
random.matrix[c(1,3),]

random.matrix

# Sums of row values.
rowSums(random.matrix)
# Sums of column values.
colSums(random.matrix)
# Means of row values.
rowMeans(random.matrix)
# Means of column values.
colMeans(random.matrix)

