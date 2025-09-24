package mk.ukim.finki.db.distributorapp._web.api;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.proForma.ProFormaService;
import mk.ukim.finki.db.distributorapp.proForma.dto.ProFormaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pro-forma")
@CrossOrigin(origins = "*")
public class RestProFormaController {
    private final ProFormaService proFormaService;

    @GetMapping("/manager/all")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<ProFormaDto> getAllManagerProFormas(Principal principal) {
        // TODO: Implement this method
        return null;
    }

}
