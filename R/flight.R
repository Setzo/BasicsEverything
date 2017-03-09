origData <- read.csv2('data/flight.csv', sep = ',', header = T, stringsAsFactors = F)
nrow(origData)

airports <- c('ATL', 'LAX', 'ORD', 'DFW', 'JFK', 'SFO', 'CLT', 'LAS', 'PHX')
origData <- subset(origData, DEST %in% airports & ORIGIN %in% airports)

head(origData, 2)
tail(origData, 2)
origData$X <- NULL

cor(origData[c('ORIGIN_AIRPORT_SEQ_ID', 'ORIGIN_AIRPORT_ID')])
cor(origData[c('DEST_AIRPORT_SEQ_ID', 'DEST_AIRPORT_ID')])

origData$ORIGIN_AIRPORT_SEQ_ID <- NULL
origData$DEST_AIRPORT_SEQ_ID <- NULL

mismatched <- origData[origData$CARRIER != origData$UNIQUE_CARRIER,]

origData$UNIQUE_CARRIER <- NULL

onTimeData <- origData[
  !is.na(origData$ARR_DEL15)
  & origData$ARR_DEL15 != ''
  & !is.na(origData$DEP_DEL15)
  & origData$DEP_DEL15 != ''
  ,
]

onTimeData$DISTANCE <- as.integer(onTimeData$DISTANCE)
onTimeData$CANCELLED <- as.integer(onTimeData$CANCELLED)
onTimeData$DIVERTED <- as.integer(onTimeData$DIVERTED)

onTimeData$ARR_DEL15 <- as.factor(onTimeData$ARR_DEL15)
onTimeData$DEP_DEL15 <- as.factor(onTimeData$DEP_DEL15)
onTimeData$DEST_AIRPORT_ID <- as.factor(onTimeData$DEST_AIRPORT_ID)
onTimeData$ORIGIN_AIRPORT_ID <- as.factor(onTimeData$ORIGIN_AIRPORT_ID)
onTimeData$DAY_OF_WEEK <- as.factor(onTimeData$DAY_OF_WEEK)
onTimeData$DEST <- as.factor(onTimeData$DEST)
onTimeData$ORIGIN <- as.factor(onTimeData$ORIGIN)
onTimeData$DEP_TIME_BLK <- as.factor(onTimeData$DEP_TIME_BLK)
onTimeData$CARRIER <- as.factor(onTimeData$CARRIER)

delayedDistribution <- tapply(onTimeData$ARR_DEL15, onTimeData$ARR_DEL15, length)
noOnTime <- delayedDistribution[1]
noDelayed <- delayedDistribution[2]
percentageDelayed <- noDelayed / (noDelayed + noOnTime)
percentageDelayed
