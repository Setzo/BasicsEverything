const electron = require("electron")

const app = electron.app
const BrowserWindow = electron.BrowserWindow
const Menu = electron.Menu

app.on('ready', _ => {
    new BrowserWindow()

    const appName = app.getName()
    const template = [
        {
            label: appName,
            submenu: [{
                label: 'About ' + appName,
                click: _ => {
                    console.log('clicked')
                },
                role: 'about'
            }, {
                type: 'separator'
            }, {
                label: 'Quit',
                click: _ => {
                    app.quit()
                },
                accelerator: 'control+q'
            }]
        }
    ]

    const menu = Menu.buildFromTemplate(template)
    Menu.setApplicationMenu(menu)
})