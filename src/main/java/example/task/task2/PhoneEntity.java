package example.task.task2;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
public class PhoneEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int pno;
    private String pname;
    private String ppnumber;
}
