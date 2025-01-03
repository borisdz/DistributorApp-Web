package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleDto;
import mk.ukim.finki.db.distributorapp.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/all")
    ResponseEntity<List<ArticleDto>> getAllArticlesDto() {
        List<ArticleDto> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @PutMapping("/add")
    ResponseEntity<Integer> addArticle(@RequestBody ArticleDto articleDto) {
        Integer res = this.articleService.create(articleDto);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/edit")
    ResponseEntity<Integer> editArticle(@RequestBody ArticleDto articleDto) {
        Integer res = this.articleService.editById(articleDto);
        return ResponseEntity.ok(res);    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        this.articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}