package battleship;

import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class DraggedTest extends JFrame {

    private JPanel contentPane;
    private List<Ship> ships = new ArrayList<>();
    private int[][] blocks;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DraggedTest frame = new DraggedTest();
                    frame.setVisible(true);
                    frame.setTitle("为你的舰队排兵布阵");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public DraggedTest() {
        this.blocks = new int[10][];
        for(int i=0; i<10; i++){
            this.blocks[i] = new int[10];
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(660, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        add(contentPane);

        BGPanel panel = new BGPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        //panel.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
        panel.setLayout(null);

        Ship ship1 = new Ship(2);
        ship1.setBounds(35*12+35, 35, 35*2, 35);
        ships.add(ship1);
        panel.add(ship1);

        Ship ship2 = new Ship(3);
        ship2.setBounds(35*12+35, 35*3, 35*3, 35);
        ships.add(ship2);
        panel.add(ship2);

        Ship ship3 = new Ship(3);
        ship3.setBounds(35*12+35, 35*5, 35*3, 35);
        ships.add(ship3);
        panel.add(ship3);

        Ship ship4 = new Ship(4);
        ship4.setBounds(35*12+35, 35*7, 35*4, 35);
        ships.add(ship4);
        panel.add(ship4);

        Ship ship5 = new Ship(5);
        ship5.setBounds(35*12+35, 35*9, 35*5, 35);
        ships.add(ship5);
        panel.add(ship5);

        JButton button = new JButton("I'm ready!");
        button.setBounds(35*6,35*11,35*4,35);
        button.setFocusPainted(false);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Ship ship:ships){
                    int x = ship.getX()/35;
                    int y = ship.getY()/35;
                    int width =ship.getWidth()/35;
                    int height = ship.getHeight()/35;
                    if(height == 1){
                        for(int i = 0; i <width;i++){
                            blocks[x+i][y] = 1;
                        }
                    }else if (width == 1){
                        for (int i = 0; i < height; i++){
                            blocks[x][y+i] = 1;
                        }
                    }

                }

                try{
                    String myFileName = "map.mp";
                    File file = new File(myFileName);
                    if (!file.exists()) { 
                        file.createNewFile();
                    }else {
                        file.delete();
                        file.createNewFile();
                    }
                }catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    // 打开一个随机访问文件流，按读写方式
                    RandomAccessFile randomFile = new RandomAccessFile("map.mp", "rw");
                    // 文件长度，字节数
                    long fileLength = randomFile.length();
                    //将写文件指针移到文件尾。
                    randomFile.seek(fileLength);
                    for(int i = 0; i<10; i++){
                        for(int j = 0; j<10; j++){
                            randomFile.writeBytes(String.valueOf(blocks[i][j]));
                        }
                     }
                    randomFile.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                GameView g = new GameView("击沉敌舰", new Game(3,1));
                dispose();

            }
        });
        panel.add(button);

        JButton button1 = new JButton("重新排布");
        button1.setBounds(35*13,35*11,35*4,35);
        button1.setFocusPainted(false);

        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ship1.setBounds(35*12+35, 35, 35*2, 35);
                ship2.setBounds(35*12+35, 35*3, 35*3, 35);
                ship3.setBounds(35*12+35, 35*5, 35*3, 35);
                ship4.setBounds(35*12+35, 35*7, 35*4, 35);
                ship5.setBounds(35*12+35, 35*9, 35*5, 35);
            }
        });

        panel.add(button1);
        MyListener m = new MyListener();
        for(Ship ship : ships){
            ship.addMouseListener(m);
            ship.addMouseMotionListener(m);
        }

    }
    // 写一个类继承鼠标监听器的适配器，这样就可以免掉不用的方法。
    class MyListener extends MouseAdapter{
        //这两组x和y为鼠标点下时在屏幕的位置和拖动时所在的位置
        int newX,newY,oldX,oldY;
        //这两个坐标为组件当前的坐标
        int startX,startY;

        @Override
        public void mousePressed(MouseEvent e) {
            //此为得到事件源组件
            Component cp = (Component)e.getSource();
            //当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
            startX = cp.getX();
            startY = cp.getY();
            oldX = e.getXOnScreen();
            oldY = e.getYOnScreen();
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            Component cp = (Component)e.getSource();
            //拖动的时候记录新坐标
            newX = e.getXOnScreen();
            newY = e.getYOnScreen();
            int moveX,moveY;
            if((newX - oldX) % 35 > 17){
                moveX = (((newX - oldX) / 35) + 1) * 35;
            }else moveX = ((newX - oldX) / 35) *35;

            if((newY - oldY) % 35 > 17){
                moveY = (((newY - oldY) / 35) + 1)* 35;
            }else moveY = ((newY - oldY) / 35) * 35;

            if((startX + moveX) > 350 || (startY + moveY) > 350){

            }
            //设置bounds,将点下时记录的组件开始坐标与鼠标拖动的距离相加
            cp.setBounds(startX+moveX, startY+moveY, cp.getWidth(), cp.getHeight());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Component cp = (Component)e.getSource();
            int x = cp.getX();
            int y = cp.getY();
            int width = cp.getWidth();
            int height = cp.getHeight();
            cp.setBounds(x,y,height,width);

        }

    }
}

