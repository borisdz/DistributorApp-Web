package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.service.ArticleUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articleUnit")
public class ArticleUnitController {
    private final ArticleUnitService articleUnitService;

    public ArticleUnitController(ArticleUnitService articleUnitService) {
        this.articleUnitService = articleUnitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleUnitDto>> getAllUnits() {
        List<ArticleUnitDto> units = this.articleUnitService.getAllArticleUnits();
        return ResponseEntity.ok(units);
    }

    @PutMapping("/add")
    public ResponseEntity<Integer> addUnit(@RequestBody ArticleUnitDto articleUnitDto) {
        Integer result = this.articleUnitService.create(articleUnitDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("edit")
    public ResponseEntity<Integer> editUnit(@RequestBody ArticleUnitDto articleUnitDto) {
        Integer result = this.articleUnitService.edit(articleUnitDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        this.articleUnitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
