package Infinity.Intex.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "consult_id_seq")
    private Integer id;
    private String fullName;
    private String phoneNumber;
    @CreationTimestamp
    private Timestamp createdAt;
    private Boolean isView = false;
}
