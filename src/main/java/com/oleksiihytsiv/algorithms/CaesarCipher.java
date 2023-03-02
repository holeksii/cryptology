package com.oleksiihytsiv.algorithms;

import static com.oleksiihytsiv.algorithms.Utils.UA_ALPHABET;
import static com.oleksiihytsiv.algorithms.Utils.EN_ALPHABET;
import static com.oleksiihytsiv.algorithms.Utils.SPECIAL_CHARACTERS;
import static com.oleksiihytsiv.algorithms.Utils.isUaAlphabet;
import static com.oleksiihytsiv.algorithms.Utils.isEnAlphabet;
import static com.oleksiihytsiv.algorithms.Utils.isMultiLanguage;

public class CaesarCipher implements CryptoAlgorithm {

  @Override
  public String encrypt(String text, int key) {
    if (isUaAlphabet(text)) {
      return encrypt(text, key, UA_ALPHABET + SPECIAL_CHARACTERS);
    } else if (isEnAlphabet(text)) {
      return encrypt(text, key, EN_ALPHABET + SPECIAL_CHARACTERS);
    } else if (isMultiLanguage(text)) {
      return encrypt(text, key, UA_ALPHABET + EN_ALPHABET + SPECIAL_CHARACTERS);
    }

    throw new IllegalArgumentException("Text is not in the supported alphabet");
  }

  public String encrypt(String text, int key, String alphabet) {
    StringBuilder encryptedText = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      boolean isLowerCase = Character.isLowerCase(c);
      int index = alphabet.indexOf(Character.toUpperCase(c));
      if (index != -1) {
        int newIndex = (index + key) % alphabet.length();
        if (isLowerCase) {
          encryptedText.append(Character.toLowerCase(alphabet.charAt(newIndex)));
        } else {
          encryptedText.append(alphabet.charAt(newIndex));
        }
      }  else {
        encryptedText.append(c);
      }
    }
    
    return encryptedText.toString();
  }

  @Override
  public String decrypt(String text, int key) {
    return encrypt(text, -key);
  }

  public static void main(String[] args) {
    CaesarCipher c = new CaesarCipher();
    var source = "Hello World";
    System.out.println(c.encrypt(source, 2));
  }
}
