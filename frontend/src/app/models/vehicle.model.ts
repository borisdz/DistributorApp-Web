import { DeliveryModel } from './delivery.model';
import { DriverModel } from './driver.model';
import { WarehouseModel } from './warehouse.model';

export interface VehicleModel {
  vehicleId: number;
  vehicleCarryWeight: number;
  vehicleServiceInterval: number;
  vehicleKilometers: number;
  vehicleLastService: string;
  vehicleLastServiceKm: number;
  vehiclePlate: string;
  vehicleVin: string;
  vehicleRegDate: string;

  warehouse?: WarehouseModel;
  driver?: DriverModel;
  deliveries?: DeliveryModel[];
}
