import { ProFormaModel } from './proForma.model';

export interface ProFormaStatusModel {
  proFormaStatusId: number;
  proFormaStatusName: string;
  proFormaStatusDescription: string;
  proFormas?: ProFormaModel[];
}
