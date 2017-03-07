library(datasets)
data(iris)

str(iris)
head(iris)
iris

mean(iris$Sepal.Length)
# mean(iris$Sepal.Width)
# mean(iris$Petal.Length)
# mean(iris$Petal.Width)

median(iris$Sepal.Length)

# Range: max - min
# Vec: c(min, max)
range(iris$Sepal.Length)
# Range
diff(range(iris$Sepal.Length))

# Quartiles - divide data into 4 parts:
# Q2 <- median(wholeVec)
# Q1 <- median(0:Q2 of wholeVec)
# Q3 <- median(Q2:wholeVec$length)
# Five point summary <- c(wholeVec[0], Q1, Q2, Q3, wholeVec[wholeVec$length])
# min, max - excluding outliers (1,5 times Q1 / Q3 respectively)
summary(iris$Sepal.Length)
summary(iris)
