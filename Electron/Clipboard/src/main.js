const electron = require('electron')
const path = require('path')

const {app, clipboard, Menu, Tray, globalShortcut} = electron

const STACK_SIZE = 5
const MAX_LEN = 20

function checkClipboardForChange(clipboard, onChange) {

    let latest
    let cache = clipboard.readText()

    setInterval(_ => {
        latest = clipboard.readText()
        if (latest !== cache) {
            cache = latest
            onChange(cache)
        }
    }, 1000)
}

function addToStack(item, stack) {
    return [item].concat(stack.length >= STACK_SIZE ? stack.slice(0, stack.length - 1) : stack)
}

function formatItem(item) {
    return item && item.length > MAX_LEN
        ? item.substr(0, MAX_LEN) + '...'
        : item
}

function formatMenuTemplateForStack(clipboard, stack) {
    return stack.map((item, i) => {
        return {
            label: 'copy ' + formatItem(item),
            click: _ => clipboard.writeText(item),
            accelerator: 'Ctrl+' + (i + 1)
        }
    })
}

function registerShortcuts(globalShortcut, clipboard, stack) {
    globalShortcut.unregisterAll()

    stack.map((item, i) => {
        globalShortcut.register('Ctrl+' + (i + 1), _ => {
            clipboard.writeText(item)
        })
    })
}

app.on('ready', _ => {

    let stack = []

    const tray = new Tray(path.join('src', 'wp.png'))
    tray.setContextMenu(Menu.buildFromTemplate([{
        label: '<Empty>',
        enabled: false
    }]))

    checkClipboardForChange(clipboard, text => {
        stack = addToStack(text, stack)
        tray.setContextMenu(Menu.buildFromTemplate(formatMenuTemplateForStack(clipboard, stack)))
        registerShortcuts(globalShortcut, clipboard, stack)
    })
})

app.on('will-quit', _ => {
    globalShortcut.unregisterAll()
})