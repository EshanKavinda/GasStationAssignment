import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { DispatchComponent } from './dispatch/dispatch.component';
import { OrderComponent } from './order/order.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import { SearchOrderComponent } from './search-order/search-order.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    DispatchComponent,
    OrderComponent,
    SearchOrderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path:'order', component: OrderComponent},
      {path:'search', component: SearchOrderComponent},
      {path:'dispatch', component: DispatchComponent},
      { path: '', redirectTo: 'order', pathMatch: 'full' },
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
