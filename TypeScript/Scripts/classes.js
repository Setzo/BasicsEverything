var Engine = (function () {
    function Engine(horsePower, type) {
        this.horsePower = horsePower;
        this.type = type;
    }
    return Engine;
}());
var Car = (function () {
    function Car(_engine) {
        this._engine = _engine;
    }
    Object.defineProperty(Car.prototype, "engine", {
        get: function () {
            return this._engine;
        },
        set: function (value) {
            if (value == undefined) {
                throw 'wrong';
            }
            this._engine = value;
        },
        enumerable: true,
        configurable: true
    });
    Car.prototype.start = function () {
        alert('Engine started: ' + this._engine.type);
    };
    return Car;
}());
window.onload = function () {
    var soc = new Car(new Engine(200, 'V8'));
    console.log(soc.engine.horsePower);
    soc.engine.type = 'V12';
    soc.start();
};
//# sourceMappingURL=classes.js.map