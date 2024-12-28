import { ArticleUnitModel } from './articleUnit.model';
import { CityModel } from './city.model';
import { ManagerModel } from './manager.model';
import { VehicleModel } from './vehicle.model';

export interface WarehouseModel {
  warehouseId: number;
  warehosueAddress: string;
  city?: CityModel;
  manager?: ManagerModel;
  vehicles?: VehicleModel[];
  articleUnits?: ArticleUnitModel;
}
