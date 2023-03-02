package com.oleksiihytsiv.algorithms;

public class Utils {

  public static final String UA_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
  public static final String EN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String SPECIAL_CHARACTERS = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

  public static boolean isUaAlphabet(String text) {
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if ((UA_ALPHABET + SPECIAL_CHARACTERS)
          .indexOf(Character.toUpperCase(c)) == -1) {
        return false;
      }
    }
    return true;
  }

  public static boolean isEnAlphabet(String text) {
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if ((EN_ALPHABET + SPECIAL_CHARACTERS)
          .indexOf(Character.toUpperCase(c)) == -1) {
        return false;
      }
    }
    return true;
  }

  public static boolean isMultiLanguage(String text) {
    return !isUaAlphabet(text) && !isEnAlphabet(text);
  }

  public static void main(String[] args) {
    System.out.print(Character.isLowerCase(','));
  }
}
