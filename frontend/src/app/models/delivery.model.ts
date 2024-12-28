import { DeliveryStatusModel } from './deliveryStatus.model';
import { OrderModel } from './order.model';
import { VehicleModel } from './vehicle.model';

export interface DeliveryModel {
  deliveryId: number;
  deliveryDateCreated: string;
  deliveryDate: string;
  deliveryStartKm: number;
  deliveryEndKm: number;
  deliveryStartTime: number;
  deliveryEndTime: number;
  deliveryStatus?: DeliveryStatusModel;
  vehicle?: VehicleModel;
  orders?: OrderModel[];
}
