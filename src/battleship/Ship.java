package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ship extends JPanel implements ActionListener{
    int length;

    public Ship(int length){
        this.length = length;
        this.setBackground(Color.GRAY);

    }

    @Override
    public void actionPerformed(ActionEvent event){
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();
        this.setBounds(x,y,height,width);



    }

}
