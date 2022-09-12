import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {NewOrder} from "../../order/model/order.model";

@Injectable({
  providedIn: 'root'
})

export class DispatchService{

  scheduledOrdersListUrl: string
  dispatchOrderUrl: string

  constructor(private http : HttpClient) {
    this.scheduledOrdersListUrl = 'http://localhost:8085/orderstobedispatch';
    this.dispatchOrderUrl = 'http://localhost:8087/dispatch';
  }

  getScheduledOrders(): Observable<NewOrder[]>{
    return this.http.get<NewOrder[]>(this.scheduledOrdersListUrl);
  }

  dispatchOrder(oid: string): Observable<any>{
    console.log(oid)
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post(this.dispatchOrderUrl,{'oid': oid}, { headers, responseType: 'text'});
  }





}
