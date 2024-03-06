package gui;

import javax.swing.*;

public class MyJDialog extends JDialog {
    public MyJDialog(JFrame frame, String title, boolean modal) {
        super(frame, title, modal);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
