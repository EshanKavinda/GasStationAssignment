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
    this.clearFeilds()
    this.orderDetailSearch = this.formBuilder.group({
      orderSearch : ['']
    })
  }

  clearFeilds(){
    this.orderDetail = this.formBuilder.group({
      type : [1],
      quantity : [6000],
      stationName: [''],
      contact: ['']
    });
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
        this.clearFeilds()
      }else {
        alert('Order creation failed')
      }
    },err=>{
      alert('Server error...')
      console.log(err);
    });
  }

  searchOrder(){
    let orderId = this.orderDetailSearch.value.orderSearch;
    this.orderService.searchOrder(orderId).subscribe(res=>{
      console.log(res);
      if (res === null){
        alert('Invalid order id')
      }else {
        let currentStatus = res.currentStatus;
        if (currentStatus === 'SCHEDULLED'){
          let alertString = 'Order status: '+currentStatus+'\nOrder will be arrived within '+res.workFlowStatus?.schedule?.arrival_date;
          alert(alertString)
        }else if (currentStatus === 'DISPATCHED'){
          confirm('Order status: '+currentStatus+'\nIs the order received to your gas station?\nPress OK button if the order is received. Otherwise press CANCEL.' );
        }else {
          alert('Order status: '+currentStatus);
        }

      }
    }, error => {
      console.log(error);
      alert('Server error')
    });
  }

}
