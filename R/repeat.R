# Infinite loop.
#
# repeat {
#
# }

Inc <- function(x) {
  x + 1
}

CountTo <- function(limit) {
  count <- 0
  repeat {
    print(count)
    count <- Inc(count)
    if(count > limit) {
      break
    }
  }
}

CountByTwoTo <- function(limit) {
  count <- 0
  repeat {
    count <- Inc(count)
    if(count > limit) {
      break
    }
    if(count %% 2 != 0) {
      # Continue.
      next
    }
    print(count)
  }
}

CountTo(5)
CountByTwoTo(10)
