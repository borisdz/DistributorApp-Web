package mk.ukim.finki.db.distributorapp.reports;

import mk.ukim.finki.db.distributorapp.article.Article;
import mk.ukim.finki.db.distributorapp.reports.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Article, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * from ArticleSalesChangeOnPriceChange()")
    List<ArticleSalesChange> getArticleSalesChangeOnPriceChange();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM SalesComparisonByCategoryAndRegionInQuantityAndIncome()")
    List<RegionCategorySales> getSalesComparisonByCategoryAndRegion();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM SalesPrognosingOnArticlesAccToSalesInPreviousWeeks()")
    List<SalesForecast> getSalesForecasts();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ArticleSalesInWarehouseByQuarters()")
    List<WarehouseQuarterlySales> getWarehouseQuarterlySales();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ArticleSalesInWarehouseByQuarters() WHERE warehouseId = :warehouseId")
    List<WarehouseQuarterlySales> getWarehouseQuarterlySalesByWarehouseId(@Param("warehouseId") Long warehouseId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM YearlyIncomeProfitAndExpensesByWarehouses()")
    List<WarehouseYearlyFinancials> getWarehouseYearlyFinancials();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM YearlyIncomeProfitAndExpensesByWarehouses() WHERE warehouseId = :warehouseId")
    List<WarehouseYearlyFinancials> getWarehouseYearlyFinancialsByWarehouseId(@Param("warehouseId") Long warehouseId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM AnalyticsOfProductsByIncomeExpensesAndProfit()")
    List<ProductAnalytics> getProductAnalytics();

    @Query(
            nativeQuery = true,
            value = "select * from AnalyticsOfProductsByIncomeExpensesAndProfit() WHERE category = :category")
    List<ProductAnalytics> getProductAnalyticsByCategory(@Param("category") String category);
}