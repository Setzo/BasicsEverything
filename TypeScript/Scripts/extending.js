var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var CustomEngine = (function () {
    function CustomEngine() {
    }
    CustomEngine.prototype.start = function (callback) {
        window.setTimeout(function () {
            callback(true, 'Custom');
        }, 1000);
    };
    CustomEngine.prototype.stop = function (callback) {
        window.setTimeout(function () {
            callback(true, 'Custom');
        }, 1000);
    };
    ;
    return CustomEngine;
}());
var Engine2 = (function () {
    function Engine2(horsePower, engineType) {
        this.horsePower = horsePower;
        this.engineType = engineType;
    }
    Engine2.prototype.start = function (callback) {
        var _this = this;
        window.setTimeout(function () {
            callback(true, _this.engineType);
        }, 1000);
    };
    Engine2.prototype.stop = function (callback) {
        var _this = this;
        window.setTimeout(function () {
            callback(true, _this.engineType);
        }, 1000);
    };
    ;
    return Engine2;
}());
var Accessory = (function () {
    function Accessory(accessoryNumber, title) {
        this.accessoryNumber = accessoryNumber;
        this.title = title;
    }
    return Accessory;
}());
var Auto = (function () {
    function Auto(options) {
        this._basePrice = options.basePrice;
        this._engine = options.engine;
        this.make = options.make;
        this.model = options.model;
        this.state = options.state;
        this.year = options.year;
    }
    Auto.prototype.calculateTotal = function () {
        return this._basePrice * 1.2;
    };
    Auto.prototype.addAccessories = function () {
        var _this = this;
        var accessories = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            accessories[_i - 0] = arguments[_i];
        }
        this.accessoryList = '';
        accessories.forEach(function (accessory) {
            _this.accessoryList += accessory.accessoryNumber + ' ' + accessory.title + '<br />';
        });
    };
    Auto.prototype.getAccessoryList = function () {
        return this.accessoryList;
    };
    Object.defineProperty(Auto.prototype, "basePrice", {
        get: function () {
            return this._basePrice;
        },
        set: function (basePrice) {
            if (basePrice < 0)
                throw 'wrong';
            this._basePrice = basePrice;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Auto.prototype, "engine", {
        get: function () {
            return this._engine;
        },
        set: function (engine) {
            if (engine == undefined)
                throw 'wrong';
            this._engine = engine;
        },
        enumerable: true,
        configurable: true
    });
    return Auto;
}());
var Truck = (function (_super) {
    __extends(Truck, _super);
    function Truck(options) {
        _super.call(this, options);
        this.bedLength = options.bedLength;
        this.fourByFour = options.fourByFour;
    }
    return Truck;
}(Auto));
window.onload = function () {
    var auto = new Truck({
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
    console.log(auto.engine.horsePower);
    auto.engine.start(function (bool, str) {
        console.log('Started: ' + bool.toString() + ' ' + str);
    });
};
//# sourceMappingURL=extending.js.map