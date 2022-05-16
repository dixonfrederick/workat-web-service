package id.ac.ui.cs.advprog.workatwebservice.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import id.ac.ui.cs.advprog.workatwebservice.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "Stats")
@NoArgsConstructor
public class Stats {

    @Id
    @Column(name = "id", updatable = true)
    private String id;

    @Column(name = "average_attempt")
    private int averageAttempt;

    @Column(name = "total_menang")
    private int totalMenang;

    @Column(name = "total_kalah")
    private int totalKalah;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    public Stats(String id) {
        new Stats(id, 0, 0, 0);
    }

    public Stats(String id, int averageAttempt, int totalMenang, int totalKalah){
        this.id = id;
        this.averageAttempt = averageAttempt;
        this.totalMenang = totalMenang;
        this.totalKalah = totalKalah;
    }

}
