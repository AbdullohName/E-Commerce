package Infinity.Intex.service.Impl;

import Infinity.Intex.model.Image;
import Infinity.Intex.repository.ImageRepository;
import Infinity.Intex.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static Infinity.Intex.utils.Util.UPLOAD_DIRECTORY;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(MultipartFile image, String name) {
        name = name.replaceAll(" ","-");
        Image imageData = new Image();
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists())
            uploadDir.mkdirs();
        int index = image.getOriginalFilename().lastIndexOf('.');
        String extension = image.getOriginalFilename().substring(index + 1);
        String imgName = name + "." + extension;
        String imgPath = uploadDir.getPath() + "/" + imgName;
        try {
            image.transferTo(Path.of(imgPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageData.setPhotoName(imgName);
        return repository.save(imageData);
    }

    @Override
    public Boolean deleteByName(String name) {
        try {
            return repository.deleteByPhotoName(name);
        } catch (Exception e) {
         return false;
        }
    }

//    public String filePath(){
//        LocalDate now = LocalDate.now();
//        Integer year = now.getYear();
//        Integer months = now.getMonthValue();
//        Integer day = now.getDayOfMonth();
//
//        return String.format("%d%d%d", year, months, day ,now);
//    }
}
