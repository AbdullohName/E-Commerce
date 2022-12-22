package Infinity.Intex.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "order_id_seq")
    private Integer id;
    private String fullName;
    @NotBlank(message = "Field must be not is empty")
    private String phoneNumber;
    private String address;
    @CreationTimestamp
    private Timestamp orderDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean isView;
    @OneToOne
    private Product product;
}
