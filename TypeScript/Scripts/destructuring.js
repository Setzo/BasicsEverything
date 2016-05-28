var data = {
    city: 'Gdynia',
    street: 'Swietojanska',
    streetNumber: 32
};
var cityAlias = data.city, street = data.street, streetNumber = data.streetNumber, _a = data.postcode, postcode = _a === void 0 ? '84240' : _a;
var employee = {
    address: data,
    position: {
        department: 'DevOps',
        name: 'Dev'
    }
};
var city = employee.address.city, nameAlias = employee.position.name;
function destParams(_a) {
    var _b = _a.address, cityAlias = (_b === void 0 ? {
        city: 'Reda'
    } : _b).city, depName = _a.position.name;
    return {
        city: cityAlias,
        depName: depName
    };
}
var func = destParams(employee);
var arr = ['mike', 'bob'];
var _b = arr || [], _c = _b[0], arrDestParam1 = _c === void 0 ? 'default1' : _c, _d = _b[1], arrDestParam2 = _d === void 0 ? 'default2' : _d, _e = _b[2], arrDestParam3 = _e === void 0 ? 'default3' : _e, restOfParams = _b.slice(3);
//# sourceMappingURL=destructuring.js.map