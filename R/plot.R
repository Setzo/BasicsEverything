library(datasets)
data(iris)

boxplot(iris$Sepal.Length)
boxplot(iris$Sepal.Length, horizontal = T)
boxplot.stats(iris$Sepal.Length)
boxplot(iris[1:4], horizontal = T)

hist(iris$Sepal.Length)
hist(
  iris$Sepal.Length,
  main = 'Sepal length histogram',
  xlab = 'Sepal length'
)

var(iris$Sepal.Length)
sd(iris$Sepal.Length)
