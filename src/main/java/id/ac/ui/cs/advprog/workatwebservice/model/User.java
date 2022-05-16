package id.ac.ui.cs.advprog.workatwebservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "discord_user")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "status")
    private String status;

    public User(String userId, String status){
        this.userId = userId;
        this.status = status;
    }
}
