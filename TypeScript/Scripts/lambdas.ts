
module lambdas {

    var multiply : (x: number, y: number) => number = (x, y) => x * y;

    var pow : (x, y) => any = (x, y) => x ** y;

    var safePow: (x?: number, y?: number) => number = (x?, y?) => (x || 1) ** (y || 1);

    console.log(multiply(6, 2));

    var helloWorld: (name?: string) => void;

    helloWorld = (name?: string) => {
        console.log(name || 'unknown');
    };

    helloWorld();
    helloWorld('type');

    console.log(safePow(5, 2));
    console.log(safePow(null, 2));
    console.log(safePow(2));
    console.log(safePow());

    var square: (rect: {x: number, y: number}) => number;

    square = (rect: {x: number, y: number}) => {
        return rect.x * rect.y;
    };

    console.log(square({
        x: 2,
        y: 3
    }));
}