package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.article.ArticleService;
import mk.ukim.finki.db.distributorapp.article.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
@CrossOrigin(origins = "*")
public class RestArticleController {
    private final ArticleService articleService;

    @GetMapping("/all-pages")
    public PagedModel<EntityModel<ArticleDto>> listArticles(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Long manufacturerId,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
            Pageable pageable,
            PagedResourcesAssembler<ArticleDto> assembler
    ) {
        Page<ArticleDto> res = articleService.getArticlesPageable(categoryId, manufacturerId, name, page, size);
        return assembler.toModel(res);
    }

    @GetMapping("/mobile/customer/articles")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<List<ArticleDto>> getArticles() {
        List<ArticleDto> articles = this.articleService.getAllArticles();
        return ResponseEntity.ok().body(articles);
    }

}
