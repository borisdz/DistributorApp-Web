package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.entities.ArticleUnit;
import mk.ukim.finki.db.distributorapp.service.ArticleUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articleUnit")
public class ArticleUnitController {
    private final ArticleUnitService articleUnitService;

    public ArticleUnitController(ArticleUnitService articleUnitService) {
        this.articleUnitService = articleUnitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleUnit>> getAllUnits() {
        List<ArticleUnit> units = this.articleUnitService.getAllArticleUnits();

        return ResponseEntity.ok(units);
    }
}
