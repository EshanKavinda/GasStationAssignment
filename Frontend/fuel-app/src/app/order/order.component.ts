import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms'
import {NewOrder} from "./model/order.model";
import {OrderService} from "./service/order.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  orderDetail !: FormGroup;
  orderDetailSearch !: FormGroup;
  newOrderObject : NewOrder = new NewOrder();

  constructor(private formBuilder : FormBuilder, private orderService: OrderService) { }

  ngOnInit(): void {
    this.orderDetail = this.formBuilder.group({
      type : [''],
      quantity : [''],
      stationName: [''],
      contact: ['']
    });

    this.orderDetailSearch = this.formBuilder.group({
      orderSearch : ['']
    })
  }

  addOrder(){
    console.log(this.orderDetail)
    this.newOrderObject.fuelTypeId = this.orderDetail.value.type;
    this.newOrderObject.quantity = this.orderDetail.value.quantity;
    this.newOrderObject.fuelStationName = this.orderDetail.value.stationName;
    this.newOrderObject.fuelStationEmail = this.orderDetail.value.contact;

    this.orderService.createOrder(this.newOrderObject).subscribe(res=>{
      if (res.currentStatus === 'ORDER_CREATED'){
        alert('Your order succefully created. Order id is: '+res.orderId)
      }else {
        alert('Order creation failed')
      }
    },err=>{
      console.log(err);
    });
  }

  searchOrder(){
    let orderId = this.orderDetailSearch.value.orderSearch;
    this.orderService.searchOrder(orderId).subscribe(res=>{
      console.log(res);
      alert('Order status is:'+ res.currentStatus);
    }, error => {
      console.log(error);
    });
  }

}
