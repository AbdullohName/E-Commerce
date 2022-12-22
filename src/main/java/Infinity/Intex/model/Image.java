package Infinity.Intex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "image_id_seq")
    public Integer id;
    @Column(unique = true)
    private String photoName;
}
