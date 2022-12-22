package Infinity.Intex.service;

import Infinity.Intex.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image save(MultipartFile image, String name);
    Boolean deleteByName(String name);
}
