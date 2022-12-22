package Infinity.Intex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "product_id_seq")
    private Integer id;
    @Column(unique = true)
    private String nameUz;
    @Column(unique = true)
    private String nameRu;
    private Double amount;
    private Double oldPrice;
    private Double currentPrice;
    private Integer categoryId;
    private Integer sellerId;
    private String frameRu;
    private String frameUz;    // рамка
    private Float size;
    private Float depth;
    private String status;
    private String equipmentRu;
    private String equipmentUz;
    @OneToOne
    private Image image;
}
