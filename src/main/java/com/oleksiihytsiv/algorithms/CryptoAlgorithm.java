package com.oleksiihytsiv.algorithms;

public interface CryptoAlgorithm {

  String encrypt(String text, int key);

  String decrypt(String text, int key);

}
