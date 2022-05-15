package id.ac.ui.cs.advprog.workatwebservice.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Stats")
@Data
@NoArgsConstructor
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = true)
    private int id;

    @Column(name = "average_attempt")
    private int averageAttempt;

    @Column(name = "total_menang")
    private int totalMenang;

    @Column(name = "total_kalah")
    private int totalKalah;

    public Stats(int id, int averageAttempt, int totalMenang, int totalKalah){
        this.id = id;
        this.averageAttempt = averageAttempt;
        this.totalMenang = totalMenang;
        this.totalKalah = totalKalah;
    }

}
