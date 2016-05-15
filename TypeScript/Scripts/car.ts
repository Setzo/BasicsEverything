class Car2 {

    constructor(private engine: string) {
    }

    start(): void {
        alert(this.engine);
    }

    stop(): void {
        alert(this.engine + ' stopped');
    }
}

window.onload = function() {

    var car = new Car2('meow');

    car.start();
    car.stop();
};