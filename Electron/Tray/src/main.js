const electron = require('electron')
path = require('path')

const {app, Tray, Menu} = electron

app.on('ready', _ => {
    const tray = new Tray(path.join('src', 'wp.png'))
    const menu = Menu.buildFromTemplate([
        {
            label: 'Weee',
            click: _ => {
                app.quit()
            }
        },{
            label: 'Weeeeeeeeeee',
            click: _ => {
                app.quit()
            }
        },{
            label: 'Exit',
            click: _ => {
                app.quit()
            }
        }
    ])

    tray.setContextMenu(menu)
    tray.setToolTip(':D')
})
