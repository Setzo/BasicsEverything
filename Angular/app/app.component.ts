
import {Component} from "angular2/core";
import {ProductListComponent} from "./products/product-list.component";
import 'rxjs/Rx';
import {ProductService} from "./products/product.service";
import {HTTP_PROVIDERS} from "angular2/http";
import {ROUTER_PROVIDERS, RouteConfig, ROUTER_DIRECTIVES} from "angular2/router";
import {WelcomeComponent} from "./home/welcome.component";
import {ProductDetailComponent} from "./products/product-detail.component";

@Component({
    selector: 'pm-app',
    templateUrl: 'app/app.component.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [ProductService, HTTP_PROVIDERS, ROUTER_PROVIDERS]
})

@RouteConfig([
    {path: '/welcome', name: 'Welcome', component: WelcomeComponent, useAsDefault: true},
    {path: '/products', name: 'Products', component: ProductListComponent},
    {path: '/product/:id', name: 'Product', component: ProductDetailComponent},
])

export class AppComponent {
    pageTitle: string = 'AppComponent';
}