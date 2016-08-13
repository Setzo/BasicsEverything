import {Component, OnInit} from "angular2/core";
import {IProduct} from "./product";
import {ProductFilterPipe} from "./product-filter.pipe";
import {StarComponent} from "../shared/star.component";
import {ProductService} from "./product.service";
import {ROUTER_DIRECTIVES} from "angular2/router";

@Component({
    // selector: 'pm-products',
    templateUrl: 'app/products/product-list.component.html',
    styleUrls: ['app/products/product-list.component.css'],
    pipes: [ProductFilterPipe],
    directives: [StarComponent, ROUTER_DIRECTIVES]
})

export class ProductListComponent implements OnInit {
    pageTitle:string = 'Product List';
    imageWidth:number = 30;
    imageMargin:number = 2;
    showImages:boolean = false;
    listFilter:string;
    errorMessage:string;
    products:IProduct[];

    constructor(private _productService: ProductService) {
    }

    toggleImage():void {
        this.showImages = !this.showImages;
    }

    ngOnInit():void {
        this.products = this._productService.getProducts()
            .subscribe(
                products => this.products = products,
                error => this.errorMessage = <any>error
            );
    }

    onRatingClicked(message:string):void {
        this.pageTitle = 'Product List: ' + message;
    }
}