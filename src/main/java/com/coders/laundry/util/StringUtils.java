package com.coders.laundry.util;

public class StringUtils {

  public static boolean isEmpty(String value) {
    return value == null || value.length() == 0;
  }

  public static boolean isBlank(String value) {
    boolean hasBlank = true;
    for (int i = 0; i < value.length(); i++) {
      char c = value.charAt(i);

      if (c != ' ') {
        hasBlank = false;
        break;
      }
    }

    return isEmpty(value) && hasBlank;
  }
}
