package com.oleksiihytsiv.ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class FileMenu extends JMenu {

  private JMenuItem open;
  private JMenuItem save;
  private JMenuItem saveAs;
  private JMenuItem exit;

  public FileMenu() {
    super("File");

    open = new JMenuItem("Open");
    save = new JMenuItem("Save");
    saveAs = new JMenuItem("Save as");
    exit = new JMenuItem("Exit");

    add(open);
    add(save);
    add(saveAs);
    add(exit);
  }
}
