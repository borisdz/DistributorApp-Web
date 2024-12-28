import { DeliveryModel } from './delivery.model';

export interface DeliveryStatusModel {
  deliveryStatusId: number;
  deliveryStatusName: string;
  deliveryStatusDescription: string;
  deliveries?: DeliveryModel[];
}
