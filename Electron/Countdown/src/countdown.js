module.exports = function countdown(tick) {

    let count = 100

    let timer = setInterval(_ => {
        tick(count--)
        if(count === -1) {
            clearInterval(timer)
        }
    }, 100)
}