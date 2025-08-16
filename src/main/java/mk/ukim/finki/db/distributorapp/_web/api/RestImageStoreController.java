package mk.ukim.finki.db.distributorapp._web.api;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.imageStore.ImageStoreService;
import mk.ukim.finki.db.distributorapp.imageStore.dto.ImageStoreDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class RestImageStoreController {
    private final ImageStoreService imageStoreService;
    private final ServletContext servletContext;

    @Value(value = "${app.upload.dir:${user.home}/uploads}")
    private String uploadDir;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("entType") String entType,
            @RequestParam("entId") Long entId
    ) {
        if (file.isEmpty() || !StringUtils.hasText(entType) || entId == null) {
            return ResponseEntity.badRequest().body("Missing parameters");
        }

        try {
            Integer stored = imageStoreService.storeAndCompress(file, entType, entId);

            return ResponseEntity.ok(stored);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not store image: " + ex.getMessage());
        }
    }

    @GetMapping("/{filename:.+}")
    public void serveImage(
            @PathVariable String filename,
            HttpServletResponse response
    ) {
        try {
            Path file = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                return;
            }

            String contentType = servletContext.getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            response.setContentType(contentType);

            StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
        } catch (MalformedURLException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } catch (IOException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/list")
    public List<ImageStoreDto> listImages(
            @RequestParam String entType,
            @RequestParam Long entId
    ){
        return imageStoreService.findByImgEntTypeAndImgEntId(entType,entId);
    }
}
