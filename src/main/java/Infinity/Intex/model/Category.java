package Infinity.Intex.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "category_id_seq")
    private Integer id;
    private String nameUz;
    private String nameRu;
    private Integer parentId;
    @OneToMany(mappedBy = "categoryId")
    private List<Product> productList;
}
