const electron = require('electron')
const fs = require('fs')
const path = require('path')

const { desktopCapturer, screen, ipcRenderer: ipc } = electron

function getMainSource(desktopCapturer, screen, done) {

    const options = {
        types: ['screen'],
        thumbnailSize: screen.getPrimaryDisplay().workAreaSize
    }

    desktopCapturer.getSources(options, (err, sources) => {
        if (err) { console.log(err) }

        const isMainSource = source => source.name === 'Entire screen' || source.name == 'Screen 1'
        done(sources.filter(isMainSource)[0])
    })
}

function onCapture(evt, targetDir) {
    getMainSource(desktopCapturer, screen, source => {
        const png = source.thumbnail.toPng()
        const filePath = path.join(targetDir, new Date() + '.png')
        writeScreenshot(png, filePath)
    })
}

function writeScreenshot(png, filePath) {
    fs.writeFile(filePath, png, err => console.log(err))
}

ipc.on('capture', onCapture)