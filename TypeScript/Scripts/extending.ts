interface IEngine2 {
    start(callback: (startStatus: boolean, engineType: string) => void): void;

    stop(callback: (stopStatus: boolean, engineType: string) => void): void
}

interface IAutoOptions {
    basePrice: number;
    engine: IEngine2;
    state: string;
    make: string;
    model: string;
    year: number;
}

interface ITruckOptions extends IAutoOptions {
    bedLength: string;
    fourByFour: boolean;
}

class CustomEngine implements IEngine2 {

    start(callback: (startStatus: boolean, engineType: string) => void) {
        window.setTimeout(() => {
            callback(true, 'Custom');
        }, 1000);
    }

    stop(callback: (stopStatus: boolean, engineType: string) => void) {
        window.setTimeout(() => {
            callback(true, 'Custom');
        }, 1000);
    };
}

class Engine2 implements IEngine2 {

    constructor(public horsePower: number, public engineType: string) {
    }

    start(callback: (startStatus: boolean, engineType: string) => void) {
        window.setTimeout(() => {
            callback(true, this.engineType);
        }, 1000);
    }

    stop(callback: (stopStatus: boolean, engineType: string) => void) {
        window.setTimeout(() => {
            callback(true, this.engineType);
        }, 1000);
    };
}

class Accessory {
    constructor(public accessoryNumber: number, public title: string) {
    }
}

class Auto {

    private _basePrice: number;
    private _engine: IEngine2;

    public make: string;
    public model: string;

    public state: string;
    public year: number;

    public accessoryList: string;

    constructor(options: IAutoOptions) {
        this._basePrice = options.basePrice;
        this._engine = options.engine;
        this.make = options.make;
        this.model = options.model;
        this.state = options.state;
        this.year = options.year;
    }

    calculateTotal(): number {
        return this._basePrice * 1.2;
    }

    addAccessories(...accessories: Accessory[]): void {

        this.accessoryList = '';
        accessories.forEach(accessory => {
            this.accessoryList += accessory.accessoryNumber + ' ' + accessory.title + '<br />';
        });
    }

    getAccessoryList(): string {
        return this.accessoryList;
    }

    get basePrice(): number {
        return this._basePrice;
    }

    set basePrice(basePrice: number) {
        if (basePrice < 0) throw 'wrong';
        this._basePrice = basePrice;
    }

    get engine(): IEngine2 {
        return this._engine;
    }

    set engine(engine: IEngine2) {
        if (engine == undefined) throw 'wrong';
        this._engine = engine;
    }
}

class Truck extends Auto {

    public bedLength: string;
    public fourByFour: boolean;

    constructor(options: ITruckOptions) {

        super(options);
        this.bedLength = options.bedLength;
        this.fourByFour = options.fourByFour;
    }
}

window.onload = () => {

    let auto = new Truck({
        basePrice: 10000,
        engine: new Engine2(200, 'V4'),
        make: 'Seat',
        model: 'Toledo',
        state: 'new',
        year: 1994,
        bedLength: '400 m',
        fourByFour: false
    });

    auto.addAccessories(new Accessory(30, 'Super'), new Accessory(404, 'Not Found'));
    console.log(auto.bedLength + ' ' + auto.accessoryList);
    console.log(auto.fourByFour);
    console.log(auto.calculateTotal());
    console.log((<Engine2>auto.engine).horsePower);

    auto.engine.start((bool: boolean, str: string) => {
        console.log('Started: ' + bool.toString() + ' ' + str);
    });
};