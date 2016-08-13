var myStr = 'myStr';
var myTemplate = myStr + " myTemplate " + (2 + 2);
console.log(myTemplate);
console.log((_a = ["", " myTemplate ", ""], _a.raw = ["", " myTemplate ", ""], friends(_a, myStr, (2 + 2).toLocaleString())));
function friends(str) {
    var str2 = [];
    for (var _i = 1; _i < arguments.length; _i++) {
        str2[_i - 1] = arguments[_i];
    }
    var result = [];
    str2.forEach(function (val, idx) {
        result.push(str[idx], val);
    });
    result.push(str[str.length - 1]);
    return result.join('');
}
var names = ['Alice', 'Bob', 'Mike', 'Harvey'];
names.forEach(function (key, idx) {
    console.log(key + ' ' + idx);
});
names.forEach(function (key) {
    console.log(key);
});
for (var name_1 in names) {
    console.log(name_1);
}
for (var _i = 0, names_1 = names; _i < names_1.length; _i++) {
    var name_2 = names_1[_i];
    console.log(name_2);
}
for (var _b = 0, names_2 = names; _b < names_2.length; _b++) {
    var name_3 = names_2[_b];
    for (var _c = 0, name_4 = name_3; _c < name_4.length; _c++) {
        var letter = name_4[_c];
        console.log(letter);
    }
}
var _a;
//# sourceMappingURL=templates.js.map