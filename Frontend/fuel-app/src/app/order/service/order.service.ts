import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {NewOrder} from "../model/order.model";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})

export class OrderService{

  createNewOrderUrl : string;
  searchOrderUrl: string | undefined;

  constructor(private http : HttpClient) {
    this.createNewOrderUrl = 'http://localhost:8085/order';
  }

  createOrder(order : NewOrder): Observable<NewOrder> {
    return this.http.post<NewOrder>(this.createNewOrderUrl,order);
  }

  searchOrder(orderId: String): Observable<NewOrder>{
    this.searchOrderUrl = 'http://localhost:8085/order?oid='+orderId;
    return this.http.get<NewOrder>(this.searchOrderUrl)
  }

}
