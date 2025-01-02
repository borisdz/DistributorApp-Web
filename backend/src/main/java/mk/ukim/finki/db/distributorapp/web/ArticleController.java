package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.model.entities.Article;
import mk.ukim.finki.db.distributorapp.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/all")
    ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/all/dto")
    ResponseEntity<List<ArticleDto>> getAllArticlesDto() {
        List<ArticleDto> articles = articleService.getAllArticlesDto();
        return ResponseEntity.ok(articles);
    }
}