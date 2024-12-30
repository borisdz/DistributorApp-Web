import { CityModel } from './city.model';

export interface RegionModel {
  regionId: number;
  regionName: string;
  cities?: CityModel[];
}
