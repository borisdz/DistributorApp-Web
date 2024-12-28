import { ArticleModel } from './article.model';

export interface CategoryModel {
  categoryId: number;
  categoryName: string;
  articles?: ArticleModel[];
}
