import { ArticleModel } from './article.model';

export interface ManufacturerModel {
  manufacturerId: number;
  manufacturerName: string;
  manufacturerAddress: string;
  manufacturerMobile: string;
  manufacturerEmail: string;
  articles?: ArticleModel[];
}
