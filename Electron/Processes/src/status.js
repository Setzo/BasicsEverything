const fs = require('fs')
const exec = require('child_process').exec
const os = require('os')

let timer

function isDir(dir) {
    try {
        return fs.lstatSync(dir).isDirectory()
    } catch (e) {
        return false
    }
}

function checkGitStatus(dir) {
    exec('git status', {
        cwd: dir
    }, (err, stdout, stderr) => {

        if (err) {
            return setStatus('unknown')
        }
        if (/nothing to commit/.test(stdout)) {
            return setStatus('clean')
        }

        return setStatus('dirty')
    })
}

function formatDir(dir) {
    return /^~/.test(dir)
        ? os.homeDir() + dir.substr(1).trim()
        : dir.trim()

}

function removeStatus() {
    const el = document.getElementById('status')
    el.classList.remove('unknown', 'clean', 'dirty')
    return el
}

function setStatus(status) {
    const el = removeStatus()
    el.classList.add(status)
}

document.getElementById('input').addEventListener('keyup', evt => {

    clearTimeout(timer)
    timer = setTimeout(_ => {
        const dir = formatDir(evt.target.value)

        if(isDir(dir)) {
            checkGitStatus(dir)
        }
    }, 500)
})