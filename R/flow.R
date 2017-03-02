
IsAverageOverFifty <- function(vec) {
  if(mean(vec) > 50) {
    return(TRUE)
  }
  FALSE
}

SwitchTest <- function(word) {
  switch(word,
         'm one' = {
           return(-1)
         },
         'zero' = {
           return(0)
         },
         'one' = {
           return(1)
         },
         'two'
  )
}

IsAverageOverFifty(c(1, 2, 3, 4))
IsAverageOverFifty(c(60, 50, 100, 233))

SwitchTest

# 1, 3, 2, 3, 1
ifelse(c(T, T, F, T, T), c(1, 3), c(2))

test.vec <- c(1, 2, 4, 3, 6)
# No, No, Yes, No, Yes
ifelse(test.vec > 3, c('Yes'), c('No'))
