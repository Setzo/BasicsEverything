var lambdas;
(function (lambdas) {
    var multiply = function (x, y) { return x * y; };
    var pow = function (x, y) { return Math.pow(x, y); };
    var safePow = function (x, y) { return Math.pow((x || 1), (y || 1)); };
    console.log(multiply(6, 2));
    var helloWorld;
    helloWorld = function (name) {
        console.log(name || 'unknown');
    };
    helloWorld();
    helloWorld('type');
    console.log(safePow(5, 2));
    console.log(safePow(null, 2));
    console.log(safePow(2));
    console.log(safePow());
    var square;
    square = function (rect) {
        return rect.x * rect.y;
    };
    console.log(square({
        x: 2,
        y: 3
    }));
})(lambdas || (lambdas = {}));
//# sourceMappingURL=lambdas.js.map