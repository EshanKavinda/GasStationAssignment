class Created {
  status: string |undefined
  date: string | undefined
}
class Allocation {
  status: string |undefined
  date: string | undefined
}
class Schedule {
  status: string |undefined
  processdate: string | undefined
  arrival_date: string | undefined
}
class Dispatch {
  status: string |undefined
  date: string | undefined
}
class OrderReceived {
  status: string |undefined
  date: string | undefined
}
class WorkFlowStatus {
  created: Created | undefined
  allocation: Allocation | undefined
  schedule: Schedule | undefined
  dispatch: Dispatch | undefined
  orderReceived: OrderReceived | undefined
}

export class NewOrder {
  orderId: string | undefined
  fuelTypeId: number=0
  fuelDescription: string | undefined
  quantity: number=0
  fuelStationName: string=''
  fuelStationEmail: string=''
  currentStatus: string | undefined
  workFlowStatus: WorkFlowStatus | undefined
}
