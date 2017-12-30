package battleship;

import javax.swing.*;
import java.awt.*;

public class BGPanel extends JPanel{

    /** 棋盘一格的大小 */
    public static final int GRID_SIZE = 35;

    public BGPanel(){
        this.setPreferredSize(new Dimension(GRID_SIZE*10,
                GRID_SIZE*10));
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        this.makeLines(g2);
        //this.makeMarks(g2);
    }


    /**
     * 画出棋盘线
     * @param g 画图对象
     */
    public void makeLines(Graphics2D g){
        // TODO
        g.setFont(new Font(null, 0,10));
        int i;
        for(i = 0; i < 11; i++){
            g.drawLine(i*GRID_SIZE ,0,i*GRID_SIZE,10*GRID_SIZE);

            g.drawLine(0,i*GRID_SIZE,10*GRID_SIZE,i*GRID_SIZE);
        }
    }
}
