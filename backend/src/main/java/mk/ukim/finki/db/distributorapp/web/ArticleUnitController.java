package mk.ukim.finki.db.distributorapp.web;

import mk.ukim.finki.db.distributorapp.model.dto.ArticleUnitDto;
import mk.ukim.finki.db.distributorapp.service.ArticleUnitService;
import org.springframework.http.HttpStatus;
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
        //List<ArticleUnit> units = this.articleUnitService.getAllArticleUnits();

        //return ResponseEntity.ok(units);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ArticleUnitDto> addUnit(@RequestBody ArticleUnitDto articleUnitDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("edit")
    public ResponseEntity<ArticleUnitDto> editUnit(@RequestBody ArticleUnitDto articleUnitDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        this.articleUnitService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
