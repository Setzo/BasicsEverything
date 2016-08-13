
module trs {

    var transformer: (transform: (numbers: number[]) => number, ...numbers: number[]) => number =
        (transform, ...numbers) => transform(numbers);

    var added = transformer((numbers: number[]) => {
        return numbers.reduce((x: number, y: number) => x + y);
    }, 2, 3, 4, 7, 7);

    var multiplied = transformer((numbers: number[]) => {
        return numbers.reduce((x: number, y: number) => x * y);
    }, 2, 3, 4, 7, 7);

    var divided = transformer((numbers: number[]) => {
        return numbers.reduce((x: number, y: number) => x / y);
    }, 2, 3, 4, 7, 7);
}