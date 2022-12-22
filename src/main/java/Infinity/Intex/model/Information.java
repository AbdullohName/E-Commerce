package Infinity.Intex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "info_id_seq")
    private Integer id;
    private String phoneNumber;
    private String address;
    private String workTime;
    private String telegram;
    private String instagram;
}
