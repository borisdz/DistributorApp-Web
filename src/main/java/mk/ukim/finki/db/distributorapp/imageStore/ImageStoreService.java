package mk.ukim.finki.db.distributorapp.imageStore;

import mk.ukim.finki.db.distributorapp.imageStore.dto.ImageStoreDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageStoreService {

    Integer storeAndCompress(MultipartFile file, String entType, Long entId) throws IOException;

    List<ImageStoreDto>  findByImgEntTypeAndImgEntId(String entType, Long entId);


}
