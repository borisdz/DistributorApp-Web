import { CityModel } from './city.model';

export interface UserModel {
  userId: number;
  userName: string;
  userSurname: string;
  userEmail: string;
  userMobile: string;
  userImage: string;
  userCity?: CityModel;
}
