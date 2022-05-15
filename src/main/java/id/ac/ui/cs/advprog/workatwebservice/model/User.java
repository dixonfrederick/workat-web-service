package id.ac.ui.cs.advprog.workatwebservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private String userId;

    @Column(name = "status")
    private String status;

    public User(String userId, String status){
        this.userId = userId;
        this.status = status;
    }
}
