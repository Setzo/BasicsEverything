define(["require", "exports", './wowify'], function (require, exports, wowify_1) {
    "use strict";
    var things = ['one', 'two', 'three'];
    var result = wowify_1.superWowify.apply(void 0, things.concat(['four']));
    console.log(result);
    $('body').html(result);
});
//# sourceMappingURL=prog.js.map