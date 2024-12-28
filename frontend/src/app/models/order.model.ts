import { ArticleUnitModel } from './articleUnit.model';
import { CustomerModel } from './customer.model';
import { DeliveryModel } from './delivery.model';
import { OrderStatusModel } from './orderStatus.model';
import { ProFormaModel } from './proForma.model';

export interface OrderModel {
  orderId: number;
  orderDate: string;
  orderSum: number;
  orderFulfillmentDate: string;
  orderComment: string;
  orderStatus?: OrderStatusModel;
  customer?: CustomerModel;
  delivery?: DeliveryModel;
  proForma?: ProFormaModel;
  articleUnits?: ArticleUnitModel[];
}
