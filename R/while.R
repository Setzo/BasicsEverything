Inc <- function(x) {
  x + 1
}

CountTo <- function(limit) {
  count <- 0
  while(count <= limit) {
    print(count)
    count <- Inc(count)
  }
}

CountTo(5)
