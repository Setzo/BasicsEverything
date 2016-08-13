
class Engine {

    constructor(public horsePower: number, public type: string) {
    }
}

class Car {

    constructor(private _engine: Engine) {
    }

    get engine(): Engine {
        return this._engine;
    }

    set engine(value: Engine) {
        if(value == undefined) {
            throw 'wrong';
        }

        this._engine = value;
    }

    start(): void {
        alert('Engine started: ' + this._engine.type);
    }
}

window.onload = () => {

    var soc = new Car(new Engine(200, 'V8'));

    console.log(soc.engine.horsePower);
    soc.engine.type = 'V12';
    soc.start();
};