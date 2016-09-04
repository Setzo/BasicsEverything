const delayms = 1

function getCurrentCity(callback) {
  setTimeout(function () {

    const city = "New York, NY"
    callback(null, city)

  }, delayms)
}

function getWeather(city, callback) {
  setTimeout(function () {

    if (!city) {
      callback(new Error("City required to get weather"))
      return
    }

    const weather = {
      temp: 50
    }

    callback(null, weather)

  }, delayms)
}

function getForecast(city, callback) {
  setTimeout(function () {

    if (!city) {
      callback(new Error("City required to get forecast"))
      return
    }

    const fiveDay = {
      fiveDay: [60, 70, 80, 45, 50]
    }

    callback(null, fiveDay)

  }, delayms)
}

suite.only("operations")

function fetchCurrentCity() {

  const operation = {}

  getCurrentCity((error, result) => {
    if(error) {
      operation.onError(error)
      return
    }
    operation.onSuccess(result)
  })

  operation.setCallbacks = (onSuccess, onError) => {
    operation.onSuccess = onSuccess
    operation.onError = onError
  }

  return operation;
}

test("fetchCurrentCity pass multiple callbacks, call all of them", done => {

  // Initiate operation.
  const operation = fetchCurrentCity()
  const multiDone = callDone(done).afterTwoCalls()

  operation.setCallbacks(result => multiDone())
  operation.setCallbacks(result => multiDone())

})

test("fetchCurrentCity pass the callbacks later on", done => {

  // Initiate operation.
  const operation = fetchCurrentCity()
  operation.setCallbacks(result => done(), error => done(error))
})