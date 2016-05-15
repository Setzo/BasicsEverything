var interfaces;
(function (interfaces) {
    var square = function (num) { return num * num; };
    var rectSquare;
    rectSquare = function (rect) {
        return rect.y !== undefined ? rect.x * rect.y : rect.x * rect.x;
    };
    var p = {
        name: 'sdsad',
        age: 32,
        kids: 1,
        calcPets: function () {
            return this.age / 5;
        },
        decreaseAgeBy: function (years) {
            this.age -= years;
        },
        greet: function (msg) {
            console.log(msg);
        }
    };
    var pets = p.calcPets();
    console.log(pets);
    function average() {
        var numbers = [];
        var add = function (num) {
            numbers.push(num);
        };
        var calc = function () {
            var sum = 0;
            numbers.forEach(function (number) {
                sum += number;
            });
            return sum / numbers.length;
        };
        return {
            add: add,
            calc: calc
        };
    }
    var avgService = average();
    avgService.add(1);
    avgService.add(2);
    avgService.add(3);
    console.log(avgService.calc());
})(interfaces || (interfaces = {}));
//# sourceMappingURL=functionsInterfaces.js.map