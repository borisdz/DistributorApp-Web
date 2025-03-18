package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.ArticleService;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
@CrossOrigin(origins = "*")
public class RestArticleController {
    private final ArticleService articleService;

    @GetMapping("/")
    public ResponseEntity<List<ArticleDto>> getArticles() {
        List<ArticleDto> articles = this.articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }
}
