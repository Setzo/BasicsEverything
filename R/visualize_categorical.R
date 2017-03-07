library(datasets)
data(iris)

# List / plot.
table(iris$Species)
barplot(table(iris$Species))

# Percentage in decimals / percents.
prop.table(table(iris$Species))
prop.table(table(iris$Species)) * 100

install.packages('psych')
library(psych)
library(lattice)

describeBy(iris, group = iris$Species)

# ~Sepal.Length | Species ~~~~~ Formula notation.
# Histograms by species
histogram(
  ~Sepal.Length | Species,
  data = iris,
  layout = c(1, 3),
  col = 'black'
)

# Boxplot by species
boxplot(
  Sepal.Length~Species,
  data = iris
)
