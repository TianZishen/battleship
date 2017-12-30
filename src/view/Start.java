package view;

import battleship.DraggedTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Start extends JFrame{

    private JPanel contentPane;

    public Start(){

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(660, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.BLUE);
        add(contentPane);

        JLabel title = new JLabel("海战棋");
        title.setFont(new Font("黑体",Font.BOLD, 80));
        title.setBounds(200,100,300,100);
        contentPane.add(title);

        JButton button = new JButton("开始游戏");
        button.setFont(new Font("黑体",Font.BOLD, 16));
        button.setBounds(250,250,160,60);
        button.setFocusPainted(false);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                dispose();
            }
        });
        contentPane.add(button);

    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Start frame = new Start();
                    frame.setVisible(true);
                    frame.setTitle("Battle Ship");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
