import { OrderModel } from './order.model';

export interface OrderStatusModel {
  orderStatusId: number;
  orderStatusName: string;
  orderStatusDescription: string;
  orders?: OrderModel[];
}
