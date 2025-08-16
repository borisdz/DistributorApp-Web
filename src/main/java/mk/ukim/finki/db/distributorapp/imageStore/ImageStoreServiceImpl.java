package mk.ukim.finki.db.distributorapp.imageStore;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.imageStore.dto.ImageStoreDto;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageStoreServiceImpl implements ImageStoreService {

    private final ImageStoreRepository imageStoreRepository;

    @Value(value = "${app.upload.dir:${user.home}/resources/upload")
    private String uploadDir;

    @Value(value = "${app.image.url.prefix:/images/}")
    private String imageUrlPrefix;

    @Override
    @Transactional
    public Integer storeAndCompress(MultipartFile file, String entType, Long entId) throws IOException {
        String original = file.getOriginalFilename();
        String ext = "";

        if(original!=null&&original.contains(".")){
            ext=original.substring(original.lastIndexOf(".")+1);
        }

        String randomName = UUID.randomUUID() +ext;

        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path fullOutputPath = uploadPath.resolve(randomName);
        Thumbnails.of(file.getInputStream())
                .size(800,800)
                .outputQuality(0.8)
                .toFile(fullOutputPath.toFile());

        String publicPath = imageUrlPrefix+randomName;
        ImageStore record = new ImageStore();
        record.setImgPath(publicPath);
        record.setImgEntType(entType);
        record.setImgEntId(entId);

        return imageStoreRepository.create(
                record.getImgPath(),
                record.getImgEntType(),
                record.getImgEntId()
        );
    }

    @Override
    public List<ImageStoreDto> findByImgEntTypeAndImgEntId(String entType, Long entId) {
        return this.imageStoreRepository.findByImgEntTypeAndImgEntId(entType, entId);
    }
}
