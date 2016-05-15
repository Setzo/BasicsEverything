/// <reference path="typings/knockout.d.ts" />
var dynamicTypes;
(function (dynamicTypes) {
    var name = ko.observable('John Papa');
    var id = ko.observable(1);
    var guy = {
        id: id,
        fullName: name
    };
    var value = guy.fullName();
    console.log(guy.fullName);
})(dynamicTypes || (dynamicTypes = {}));
//# sourceMappingURL=dynamic-types.js.map