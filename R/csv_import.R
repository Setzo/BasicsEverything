file <- file.path('data/mock.csv')
csv.data <- read.csv(file, header = F)

str(csv.data)
csv.data

download.file('https://raw.githubusercontent.com/wpruszak/Learning/master/R/data/mock.csv', 'data/mock2.csv')
csv.data2 <- read.table('data/mock2.csv')
csv.data2
