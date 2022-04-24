package id.ac.ui.cs.advprog.workatwebservice.core;

import lombok.Data;

@Data
public class GameObject {
    private String gameId;
    private String userId;
    private int jumlahAttempt;
    private int startingTime;
    private int endTime;
    private boolean finalState;
    private String correctWord;

    public GameObject(String gameId, String userId) {
        this.gameId = gameId;
        this.userId = userId;
    }
}
