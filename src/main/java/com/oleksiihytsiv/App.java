package com.oleksiihytsiv;

import com.oleksiihytsiv.algorithms.CaesarCipher;
import com.oleksiihytsiv.algorithms.CryptoAlgorithm;
import com.oleksiihytsiv.algorithms.Utils;
import com.oleksiihytsiv.attack.AttackSimulation;
import com.oleksiihytsiv.attack.CaesarCipherAttack;
import com.oleksiihytsiv.ui.MainFrame;
import com.oleksiihytsiv.validation.CaesarCipherValidator;
import com.oleksiihytsiv.validation.KeyValidator;

public class App {
  public static void main(String[] args) {
    CryptoAlgorithm cryptoAlgorithm = new CaesarCipher();
    KeyValidator keyValidator = new CaesarCipherValidator(
        Utils.EN_ALPHABET.length() + Utils.SPECIAL_CHARACTERS.length());
    AttackSimulation attackSimulation = new CaesarCipherAttack();

    var mainFrame = new MainFrame(cryptoAlgorithm, keyValidator, attackSimulation);
    mainFrame.setVisible(true);
  }
}
