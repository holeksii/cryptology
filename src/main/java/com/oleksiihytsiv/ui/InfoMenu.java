package com.oleksiihytsiv.ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class InfoMenu extends JMenu {

  private JMenuItem aboutDeveloper;

  public InfoMenu() {
    super("Info");
    aboutDeveloper = new JMenuItem("About developer");
    addAboutDeveloperListener();
    add(aboutDeveloper);
  }

  public void addAboutDeveloperListener() {
    aboutDeveloper.addActionListener(actionListener -> {
      var message = "Oleksii Hytsiv";
      var title = "About developer";
      JOptionPane.showMessageDialog(null, message, title,
          JOptionPane.INFORMATION_MESSAGE);
    });
  }
}
