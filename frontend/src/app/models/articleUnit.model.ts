import { ArticleModel } from './article.model';
import { OrderModel } from './order.model';
import { WarehouseModel } from './warehouse.model';

export interface ArticleUnitModel {
  unitId: number;
  unitExpirationDate: string;
  unitSerialNumber: string;
  unitBatchModel: string;
  unitManufactureDate: string;
  unitCostPrice: number;
  article?: ArticleModel;
  warehouse?: WarehouseModel;
  order?: OrderModel;
}
