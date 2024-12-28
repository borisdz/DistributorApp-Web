import { OrderModel } from './order.model';
import { UserModel } from './user.model';

export interface CustomerModel extends UserModel {
  edb: string;
  companyName: string;
  address: string;
  openTime: string;
  closeTime: string;
  representativeImage: string;
  orders?: OrderModel[];
}
