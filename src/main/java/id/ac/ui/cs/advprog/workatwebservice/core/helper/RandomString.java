package id.ac.ui.cs.advprog.workatwebservice.core.helper;

import java.util.Random;

public class RandomString {
  public static String generateRandomString(int length) {
    int leftLimit = 65;
    int rightLimit = 122;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
        int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
        while (randomLimitedInt > 90 && randomLimitedInt < 97) {
          randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
        }
        buffer.append((char) randomLimitedInt);
    }

    return buffer.toString();
  }
}
