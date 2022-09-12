import { Component, OnInit } from '@angular/core';
import {DispatchService} from "./service/dispatch.service";
import {NewOrder} from "../order/model/order.model";

@Component({
  selector: 'app-dispatch',
  templateUrl: './dispatch.component.html',
  styleUrls: ['./dispatch.component.scss']
})
export class DispatchComponent implements OnInit {

  scheduledOrderList: NewOrder[] = [];

  constructor(private dispatchService: DispatchService) { }

  ngOnInit(): void {
    this.getScheduledOrders()
  }

  getScheduledOrders() {
    this.dispatchService.getScheduledOrders().subscribe(res=>{
      this.scheduledOrderList = res;
    },err=>{
      console.log("error while fetching data.")
    });
  }

  dispatchOrder(oid: any){
    this.dispatchService.dispatchOrder(oid).subscribe(res=>{
      alert(res);
      this.getScheduledOrders();
    },err=>{
      alert('Server error');
      console.log(err)
    });
  }

}
