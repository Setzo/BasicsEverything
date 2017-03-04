# Create matrix by filling each row one value at a time.
random.matrix <- matrix(c(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), ncol = 3, nrow = 4, byrow = T)

rownames(random.matrix) <- c('A', 'B', 'C', 'D')
colnames(random.matrix) <- c('0', '1', '2')

random.matrix

# Sum of row values.
result <- vector('numeric', length = nrow(random.matrix))
for(row in 1:nrow(random.matrix)) {
  sum <- 0
  for(column in 1:ncol(random.matrix)) {
    sum <- sum + random.matrix[row, column]
  }
  result[row] <- sum
}
result

# Sum of row values.
apply(random.matrix, 1, sum)
apply(X = random.matrix, MARGIN = 1, FUN = sum)
# Columnwise mean.
apply(random.matrix, 2, mean)

# Map each value.
apply(random.matrix, 1:2, function(x) {
  x / 2
})

# Names of rows / columns containing highest value in each column / row.
rownames(random.matrix)[apply(random.matrix, 2, which.max)]
colnames(random.matrix)[apply(random.matrix, 1, which.max)]

# Input: list, vector -> Return list
# lapply
# Input: list, vector -> Return vector, matrix
# sapply
# Input: list, vector -> Return vector, matrix
# vapply
