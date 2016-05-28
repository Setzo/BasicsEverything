define(["require", "exports"], function (require, exports) {
    "use strict";
    function wowify() {
        var strings = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            strings[_i - 0] = arguments[_i];
        }
        strings.forEach(function (item, index) {
            strings[index] = item + " wow.";
        });
        return strings.join(',');
    }
    exports.superWowify = wowify;
    function mehify() {
        var strings = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            strings[_i - 0] = arguments[_i];
        }
        strings.forEach(function (item, index) {
            strings[index] = item + " meh.";
        });
        return strings.join(',');
    }
    exports.mehify = mehify;
});
//# sourceMappingURL=wowify.js.map