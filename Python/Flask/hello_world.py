from flask import Flask, render_template, send_from_directory
import os

app = Flask('lul')


@app.route('/')
def main():
    return 'Hi'


@app.route('/template')
def template():
    return render_template('index.html', param='Test')


@app.route('/test')
def test():
    return send_from_directory(os.path.join(app.root_path, 'test'), 'file.txt', mimetype='text/plain')


@app.route('/pdf')
def pdf():
    return send_from_directory(os.path.join(app.root_path, 'test'), 'file.pdf', mimetype='application/pdf')


app.run()
