package id.ac.ui.cs.advprog.workatwebservice.core.answer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Result {
  private int attemptsLeft;
  private String letterStates;
  private String error;
  private boolean isCorrect;
}
