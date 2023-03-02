package com.oleksiihytsiv.validation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CaesarCipherValidator implements KeyValidator {

  private int alphabetLength;

  @Override
  public void validate(String key) {
    try {
      int keyInt = Integer.parseInt(key);
      if (keyInt < 0 || keyInt > alphabetLength) {
        throw new IllegalArgumentException("Key must be in range [0, " + alphabetLength + "]");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Key must be a number");
    }
  }
}
