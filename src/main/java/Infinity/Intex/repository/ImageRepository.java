package Infinity.Intex.repository;

import Infinity.Intex.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Boolean deleteByPhotoName(String name);
}