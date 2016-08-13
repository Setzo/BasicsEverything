module interfaces {

    interface Square {
        (x: number): number;
    }

    var square: Square = (num) => num * num;

    interface Rectangle {
        x: number;
        y?: number;
    }

    var rectSquare: (rect: Rectangle) => number;

    rectSquare = (rect) => {
        return rect.y !== undefined ? rect.x * rect.y : rect.x * rect.x;
    };

    interface Person {
        name: string;
        age?: number;
        kids: number;
        calcPets: () => number;
        decreaseAgeBy: (years: number) => void;
        greet: (msg: string) => void;
    }

    var p: Person = {
        name: 'sdsad',
        age: 32,
        kids: 1,
        calcPets: function() {
            return this.age / 5;
        },
        decreaseAgeBy: function(years) {
            this.age -= years;
        },
        greet: function(msg) {
            console.log(msg);
        }
    };

    var pets = p.calcPets();
    console.log(pets);

    interface Avg {
        add: (num: number) => void;
        calc: () => number;
    }

    function average(): Avg {

        var numbers: number[] = [];

        var add = (num) => {
            numbers.push(num);
        };

        var calc = () => {

            var sum = 0;
            numbers.forEach((number: number) => {
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
}