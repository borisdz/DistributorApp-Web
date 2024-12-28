import { UserModel } from './user.model';
import { WarehouseModel } from './warehouse.model';

export interface CityModel {
  cityId: number;
  cityName: string;
  users?: UserModel[];
  warehouses?: WarehouseModel[];
}
