
let myStr = 'myStr';

let myTemplate = `${myStr} myTemplate ${2 + 2}`;

console.log(myTemplate);


console.log(friends`${myStr} myTemplate ${(2 + 2).toLocaleString()}`);

function friends(str: string[], ...str2: string[]) {

    const result = [];

    str2.forEach((val, idx) => {
        result.push(str[idx], val);
    });

    result.push(str[str.length - 1]);

    return result.join('');
}

const names = ['Alice', 'Bob', 'Mike', 'Harvey'];

names.forEach((key, idx) => {
    console.log(key + ' ' + idx);
});

names.forEach((key) => {
    console.log(key);
});

for(let name in names) {
    console.log(name);
}

for(let name of names) {
    console.log(name);
}

for(let name of names) {
    for(let letter of name) {
        console.log(letter);
    }
}