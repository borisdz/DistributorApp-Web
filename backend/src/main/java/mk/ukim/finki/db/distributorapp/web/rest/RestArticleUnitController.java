package mk.ukim.finki.db.distributorapp.web.rest;

import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.articleUnit.dto.ArticleUnitSimpleDto;
import mk.ukim.finki.db.distributorapp.articleUnit.ArticleUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restarticleUnit")
public class RestArticleUnitController {
    private final ArticleUnitService articleUnitService;

    public RestArticleUnitController(ArticleUnitService articleUnitService) {
        this.articleUnitService = articleUnitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleUnitSimpleDto>> getAllUnits() {
        List<ArticleUnitSimpleDto> units = this.articleUnitService.getAllArticleUnits();
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
