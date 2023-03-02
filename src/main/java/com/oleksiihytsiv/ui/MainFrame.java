package com.oleksiihytsiv.ui;

import java.nio.charset.StandardCharsets;

import com.oleksiihytsiv.algorithms.Utils;
import com.oleksiihytsiv.validation.CaesarCipherValidator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.oleksiihytsiv.algorithms.CryptoAlgorithm;
import com.oleksiihytsiv.attack.AttackSimulation;
import com.oleksiihytsiv.attack.AttackTimeAndKey;
import com.oleksiihytsiv.validation.KeyValidator;

public class MainFrame extends JFrame {

  private CryptoAlgorithm cryptoAlgorithm;
  private KeyValidator keyValidator;
  private AttackSimulation attackSimulation;

  private File file;
  private StringBuffer fileText;

  private MenuBar menuBar;

  // divide screen by 3 parts
  private JPanel leftPanel;
  private JPanel centerPanel;
  private JPanel rightPanel;

  // left panel
  private TextPanel sourceText;

  // right panel
  private TextPanel originText;

  // center panel
  private JTextField keyTextField;
  private JButton encryptButton;
  private JButton decryptButton;
  private JButton attackButton;
  private JButton frequencyTable;

  public MainFrame(CryptoAlgorithm cryptoAlgorithm, KeyValidator keyValidator,
      AttackSimulation attackSimulation) {
    this.cryptoAlgorithm = cryptoAlgorithm;
    this.keyValidator = keyValidator;
    this.attackSimulation = attackSimulation;

    initUI();
  }

  private void initUI() {
    setTitle("Crypto App");

    setSize(1600, 900);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    this.fileText = new StringBuffer();

    menuBar = new MenuBar();
    setJMenuBar(menuBar);

    initLeftPanel();
    initCenterPanel();
    initRightPanel();

    addMenuActionListeners();
    addButtonsActionListeners();
  }

  private void initLeftPanel() {
    leftPanel = new JPanel();
    leftPanel.setBounds(0, 0, 600, 900);

    sourceText = new TextPanel("Source text", 35, 40, 15f);
    leftPanel.add(sourceText);

    add(leftPanel);
  }

  private void initCenterPanel() {
    centerPanel = new JPanel();
    centerPanel.setBounds(600, 0, 400, 900);

    JTextField keyLabel = new JTextField("KEY");
    keyLabel.setFont(keyLabel.getFont().deriveFont(20f));
    centerPanel.add(keyLabel);
    keyTextField = new JTextField(16);
    keyTextField.setFont(keyTextField.getFont().deriveFont(20f));
    keyTextField.setBounds(0, 0, 400, 100);
    centerPanel.add(keyTextField);

    encryptButton = new JButton("   Encrypt   ");
    encryptButton.setFont(encryptButton.getFont().deriveFont(20f));
    encryptButton.setBounds(0, 0, 400, 100);
    centerPanel.add(encryptButton);

    decryptButton = new JButton("   Decrypt   ");
    decryptButton.setFont(decryptButton.getFont().deriveFont(20f));
    decryptButton.setBounds(0, 0, 400, 100);
    centerPanel.add(decryptButton);

    attackButton = new JButton("Attack");
    attackButton.setFont(attackButton.getFont().deriveFont(20f));
    attackButton.setBounds(0, 0, 400, 600);
    centerPanel.add(attackButton);

    frequencyTable = new JButton("Frequency table");
    frequencyTable.setFont(frequencyTable.getFont().deriveFont(20f));
    frequencyTable.setBounds(0, 0, 400, 100);
    centerPanel.add(frequencyTable);

    add(centerPanel);
  }

  private void initRightPanel() {
    rightPanel = new JPanel();
    rightPanel.setBounds(1000, 0, 600, 900);

    originText = new TextPanel("Origin text", 33, 40, 15f);
    rightPanel.add(originText);

    add(rightPanel);
  }

  // add action listeners to the menu items
  public void addMenuActionListeners() {
    addMenuFileOpenActionListener();
    addMenuFileSaveActionListener();
    addMenuFileSaveAsActionListener();
    addMenuFileExitListener();
  }

  public void addMenuFileOpenActionListener() {
    menuBar.getFileMenu().getOpen().addActionListener(actionListener -> {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      int result = fileChooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        try {
          this.file = fileChooser.getSelectedFile();
          // if file extention is txt
          if (this.file.getName().endsWith(".txt")) {
            // read all lines from file and add them to the fileText
            Files.readAllLines(this.file.toPath()).forEach(
                line -> this.fileText.append(line));
            sourceText.setText(this.fileText.toString());
            this.fileText = new StringBuffer();
          } else {
            // if file extention is not txt
            // read all bytes from file and add them to the fileText
            byte[] bytes = Files.readAllBytes(this.file.toPath());
            sourceText.setText(new String(bytes, StandardCharsets.UTF_8));
          }
          // Files.readAllLines(this.file.toPath()).forEach(
          // line -> this.fileText.append(line));
          // sourceText.setText(this.fileText.toString());
          // this.fileText = new StringBuffer();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void addMenuFileSaveActionListener() {
    menuBar.getFileMenu().getSave().addActionListener(actionListener -> {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      int result = fileChooser.showSaveDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        try {
          if (this.file == null) {
            this.file = fileChooser.getSelectedFile();
          }
          Files.write(this.file.toPath(), originText.getText().getBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void addMenuFileSaveAsActionListener() {
    menuBar.getFileMenu().getSaveAs().addActionListener(actionListener -> {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      int result = fileChooser.showSaveDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        try {
          this.file = fileChooser.getSelectedFile();
          Files.write(this.file.toPath(), originText.getText().getBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void addMenuFileExitListener() {
    menuBar.getFileMenu().getExit().addActionListener(actionListener -> {
      System.exit(0);
    });
  }

  public void addButtonsActionListeners() {
    addEncryptButtonActionListener();
    addDecryptButtonActionListener();
    addAttackButtonActionListener();
  }

  public void addEncryptButtonActionListener() {
    encryptButton.addActionListener(actionListener -> {
      try {
        if (Utils.isEnAlphabet(sourceText.getText())) {
          keyValidator = new CaesarCipherValidator(
              Utils.EN_ALPHABET.length() + Utils.SPECIAL_CHARACTERS.length());
        } else if (Utils.isUaAlphabet(sourceText.getText())) {
          keyValidator = new CaesarCipherValidator(
              Utils.UA_ALPHABET.length() + Utils.SPECIAL_CHARACTERS.length());
        } else if (Utils.isMultiLanguage(sourceText.getText())) {
          keyValidator = new CaesarCipherValidator(
              Utils.UA_ALPHABET.length() + Utils.SPECIAL_CHARACTERS.length()
                  + Utils.EN_ALPHABET.length());
        }
        keyValidator.validate(keyTextField.getText());
        originText.setText(cryptoAlgorithm.encrypt(sourceText.getText(),
            Integer.parseInt(keyTextField.getText())));
      } catch (Exception e) {
        var message = "Invalid key:\n" + e.getMessage();
        var title = "Error";
        JOptionPane.showMessageDialog(null, message, title,
            JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }

  public void addDecryptButtonActionListener() {
    decryptButton.addActionListener(actionListener -> {
      try {
        keyValidator.validate(keyTextField.getText());
        originText.setText(cryptoAlgorithm.decrypt(sourceText.getText(),
            Integer.parseInt(keyTextField.getText())));
      } catch (Exception e) {
        var message = "Invalid key:\n" + e.getMessage();
        var title = "Error";
        JOptionPane.showMessageDialog(null, message, title,
            JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }

  public void addAttackButtonActionListener() {
    attackButton.addActionListener(actionListener -> {
      var sourceText = this.sourceText.getText();
      var originText = this.originText.getText();
      AttackTimeAndKey a;

      String message = "";
      String title = "";
      try {
        a = attackSimulation.attack(sourceText, originText);
        message = "Key: " + a.getKey() + "\nTime: " + a.getTimeInMillis() + " ms";
        title = "Attack result";
      } catch (Exception e) {
        message = "Something went Wrong";
        title = "Error";
      }

      JOptionPane.showMessageDialog(null, message, title,
          JOptionPane.INFORMATION_MESSAGE);
    });
  }
}
