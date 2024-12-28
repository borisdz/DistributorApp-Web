import { OrderModel } from './order.model';
import { ProFormaStatusModel } from './proFormaStatus.model';

export interface ProFormaModel {
  proFormaId: number;
  proFormaDeadline: string;
  proFormaDateCreated: string;
  proFormaStatus?: ProFormaStatusModel;
  order?: OrderModel;
}
