var Car2 = (function () {
    function Car2(engine) {
        this.engine = engine;
    }
    Car2.prototype.start = function () {
        alert(this.engine);
    };
    Car2.prototype.stop = function () {
        alert(this.engine + ' stopped');
    };
    return Car2;
}());
window.onload = function () {
    var car = new Car2('meow');
    car.start();
    car.stop();
};
//# sourceMappingURL=car.js.map