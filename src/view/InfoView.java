package view;

import javax.swing.*;
import java.awt.*;

public class InfoView extends JPanel {

    JLabel label;

    public InfoView() {

        this.setLayout(null);
        this.setPreferredSize(new Dimension(100,350));
        label = new JLabel();
        label.setBounds(5,100,90,80);
        add(label);
    }


}
