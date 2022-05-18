package id.ac.ui.cs.advprog.workatwebservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "game_object")
@Data
@NoArgsConstructor
public class GameObject {
    @Id
    @Column(name = "game_id", updatable = false, nullable = false)
    private String gameId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "attempt_amount")
    private int attemptAmount;

    @Column(name = "starting_time")
    private int startingTime;

    @Column(name = "end_time")
    private int endTime;

    @Column(name = "final_state")
    private boolean finalState;

    @Column(name = "correct_word")
    private String correctWord;
}
