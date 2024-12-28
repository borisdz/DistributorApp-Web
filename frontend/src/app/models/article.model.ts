import { ArticleUnitModel } from './articleUnit.model';
import { CategoryModel } from './category.model';
import { ManufacturerModel } from './manufacturer.model';
import { PriceModel } from './price.model';

export interface ArticleModel {
  articleId: number;
  articleName: string;
  articleWeight: number;
  category?: CategoryModel;
  manufacturer?: ManufacturerModel;
  prices?: PriceModel[];
  articleUnits?: ArticleUnitModel[];
}
