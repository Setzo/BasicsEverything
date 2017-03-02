int.vec0 <- c(1L, 2L, 3L)
int.vec1 <- c(5L, 2L, 5L)

GetTotal <- function(x, y, z = 5L, ...) {
  x + y + z + sum(...)
}

GetTotal(int.vec0, int.vec1)
GetTotal(y = int.vec0, x = int.vec1)
GetTotal(y = int.vec0, x = int.vec1, z = int.vec0)
GetTotal(y = int.vec0, x = int.vec1, z = int.vec0, zz = 1, 4, 5, 6, 1, 1, 100)

GetSummary <- function(x, y) {
  return(list(avg = mean(x + y), deviation = sd(x + y)))
}

result <- GetSummary(int.vec0, int.vec1)

# List.
typeof(result)

# Access results.
result$avg
result$deviation

# Access func argument names / body.
formals(GetTotal)
body(GetTotal)
