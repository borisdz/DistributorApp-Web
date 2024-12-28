import { ArticleModel } from './article.model';

export interface PriceModel {
  priceId: number;
  price: number;
  priceEffectiveDate: string;
  article?: ArticleModel;
}
