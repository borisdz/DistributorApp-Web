import { RegionModel } from './region.model';
import { UserModel } from './user.model';
import { WarehouseModel } from './warehouse.model';

export interface CityModel {
  cityId: number;
  cityName: string;
  region?: RegionModel;
  users?: UserModel[];
  warehouses?: WarehouseModel[];
}
