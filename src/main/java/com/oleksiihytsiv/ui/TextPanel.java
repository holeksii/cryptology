package com.oleksiihytsiv.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class TextPanel extends JPanel {

  private JScrollPane scrollPane;
  private JTextArea textArea;

  public TextPanel(String Title, int width, int height) {
    setBorder(new TitledBorder(new EtchedBorder(), Title));

    textArea = new JTextArea(height, width);
    textArea.setEditable(true);
    JScrollPane scrollPane = new JScrollPane(textArea);

    add(scrollPane);
  }

  public TextPanel(String Title, int width, int height, float textSize) {
    this(Title, width, height);
    textArea.setFont(textArea.getFont().deriveFont(textSize));
  }

  public void setText(String text) {
    textArea.setText(text);
  }

  public String getText() {
    return textArea.getText();
  }
}
