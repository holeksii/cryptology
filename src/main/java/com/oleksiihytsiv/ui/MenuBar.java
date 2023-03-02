package com.oleksiihytsiv.ui;

import javax.swing.JMenuBar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MenuBar extends JMenuBar {

  private FileMenu fileMenu;
  private InfoMenu infoMenu;

  public MenuBar() {
    fileMenu = new FileMenu();
    infoMenu = new InfoMenu();
    add(fileMenu);
    add(infoMenu);
  }
}
