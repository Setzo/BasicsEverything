var trs;
(function (trs) {
    var transformer = function (transform) {
        var numbers = [];
        for (var _i = 1; _i < arguments.length; _i++) {
            numbers[_i - 1] = arguments[_i];
        }
        return transform(numbers);
    };
    var added = transformer(function (numbers) {
        return numbers.reduce(function (x, y) { return x + y; });
    }, 2, 3, 4, 7, 7);
    var multiplied = transformer(function (numbers) {
        return numbers.reduce(function (x, y) { return x * y; });
    }, 2, 3, 4, 7, 7);
    var divided = transformer(function (numbers) {
        return numbers.reduce(function (x, y) { return x / y; });
    }, 2, 3, 4, 7, 7);
})(trs || (trs = {}));
//# sourceMappingURL=hc.js.map