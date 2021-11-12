package com.meli.SpringbootBuildingblocks.utils;

import com.meli.SpringbootBuildingblocks.entities.User;
import java.util.Random;

public class UserTestUtils {

  public static User generateUser() {
    User user = new User();
    user.setFirstname(generateRandomAlphabetic());
    user.setUsername(generateRandomAlphabetic());
    user.setLastname(generateRandomAlphabetic());
    user.setSsn(generateRandomAlphabetic());
    user.setEmail(generateRandomAlphabetic());
    user.setRole(generateRandomAlphabetic());
    return user;
  }

  private static String generateRandomAlphabetic() {
    final int leftLimit = 97;
    final int rightLimit = 122;
    final int targetStringLength = 15;
    Random random = new Random();
    return random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
