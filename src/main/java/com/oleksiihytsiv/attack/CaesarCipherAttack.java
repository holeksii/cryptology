package com.oleksiihytsiv.attack;

import com.oleksiihytsiv.algorithms.CaesarCipher;
import com.oleksiihytsiv.algorithms.CryptoAlgorithm;
import com.oleksiihytsiv.algorithms.Utils;

public class CaesarCipherAttack implements AttackSimulation {

  @Override
  public AttackTimeAndKey attack(String source, String origin) {
    long startTime = System.currentTimeMillis();
    int key = -1;

    String alphabet = Utils.SPECIAL_CHARACTERS;

    if (Utils.isUaAlphabet(source)) {
      alphabet = Utils.UA_ALPHABET + alphabet;
    } else if (Utils.isEnAlphabet(source)) {
      alphabet = Utils.EN_ALPHABET + alphabet;
    } else if (Utils.isMultiLanguage(source)) {
      alphabet = Utils.UA_ALPHABET + Utils.EN_ALPHABET + alphabet;
    } else {
      throw new IllegalArgumentException("Text is not in the supported alphabet");
    }

    for (char c : alphabet.toCharArray()) {
      if (c == origin.charAt(0)) {
        key = alphabet.indexOf(c) - alphabet.indexOf(source.charAt(0));
        break;
      }
    }

    CryptoAlgorithm cryptoAlgorithm = new CaesarCipher();
    if (!cryptoAlgorithm.encrypt(source, key).equals(origin)) {
      throw new RuntimeException();
    }

    long endTime = System.currentTimeMillis();

    return new AttackTimeAndKey(endTime - startTime, key);
  }
}
