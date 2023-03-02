package com.oleksiihytsiv.attack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AttackTimeAndKey {

  private long timeInMillis;
  private int key;
}
