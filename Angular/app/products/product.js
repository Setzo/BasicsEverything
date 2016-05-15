System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __extends = (this && this.__extends) || function (d, b) {
        for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
    var Product;
    return {
        setters:[],
        execute: function() {
            Product = (function (_super) {
                __extends(Product, _super);
                function Product(productId, productName, productCode, releaseDate, description, price, starRating, imageUrl) {
                    _super.call(this);
                    this.productId = productId;
                    this.productName = productName;
                    this.productCode = productCode;
                    this.releaseDate = releaseDate;
                    this.description = description;
                    this.price = price;
                    this.starRating = starRating;
                    this.imageUrl = imageUrl;
                }
                Product.prototype.calculateDiscount = function (percent) {
                    return this.price * ((100 - percent) / 100);
                };
                return Product;
            }(IProduct));
            exports_1("Product", Product);
        }
    }
});
//# sourceMappingURL=product.js.map