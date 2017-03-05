install.packages('XML')
library(XML)

file <- file.path('data/mock.xml')
xml.data <- xmlToDataFrame(file, colClasses = c('integer', 'integer', 'integer', 'integer'))
xml.data
str(xml.data)
