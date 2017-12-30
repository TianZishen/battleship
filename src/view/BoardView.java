package view;

import battleship.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class BoardView extends JPanel {

    /** 棋盘一格的大小 */
    public static final int GRID_SIZE = 35;
    /** 棋盘的引用 */
    private Board board;
    private int mode;

    public BoardView(Board b){
        this.board = b;
        this.setPreferredSize(new Dimension(GRID_SIZE*b.getSize(),
                GRID_SIZE*b.getSize()));
        this.mode = 0;
        this.setLayout(null);
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        this.makeLines(g2);
        if(this.board.getMode() == 1) this.makeMarks(g2);
        this.makeBombs(g2);
        System.out.println(board.toString());
    }

    /**
     * 画出棋子（标记）
     * @param g 画图对象
     */
    public void makeMarks(Graphics2D g){
        g.setColor(Color.gray);
        for(int i=0; i<this.board.getSize(); i++){
            for(int j=0; j<this.board.getSize(); j++){
                if(this.board.getBlocks()[i][j] == 1) {
                    System.out.print(1);
                    g.fillRect(i * 35, j * 35, 35, 35);
                }
                }
        }
    }

    public void makeBombs(Graphics2D g){
        g.setFont(new Font("SansSerif", Font.PLAIN, 30));
        g.setColor(Color.RED);
        for(int i=0; i<this.board.getSize(); i++){
            for(int j=0; j<this.board.getSize(); j++){
                if(this.board.getBlocks()[i][j] == 3){
                    if(this.board.getMode() == 1){
                        g.setColor(Color.gray);
                        g.fillRect(i * 35, j * 35, 35, 35);
                    }
                    g.setColor(Color.RED);
                    g.drawString("X",i*35+5,j*35+30);}
            }
        }

        g.setColor(Color.BLACK);
        for(int i=0; i<this.board.getSize(); i++){
            for(int j=0; j<this.board.getSize(); j++){
                if(this.board.getBlocks()[i][j] == 2)
                    g.drawString("X",i*35+5,j*35+30);
            }
        }

    }

    /**
     * 画出棋盘线
     * @param g 画图对象
     */
    public void makeLines(Graphics2D g){
        // TODO
        g.setFont(new Font(null, 0,10));
        int i;
        for(i = 0; i < board.getSize(); i++){
            g.drawLine(i*GRID_SIZE,0,i*GRID_SIZE,board.getSize()*GRID_SIZE);

            g.drawLine(0,i*GRID_SIZE,board.getSize()*GRID_SIZE,i*GRID_SIZE);
        }
    }

    /**
     * 根据光标的坐标返回当前被选中的棋盘格子对应的ID
     * @param x 光标x坐标
     * @param y 光标y坐标
     * @return 被选中的棋盘格子对应的ID，ID=格子所在列号 + 格子所在行号*棋盘大小 （行列号均从0开始）
     */
    public int getSelectedBlockId(int x, int y){
        // TODO
        int index_x = x/GRID_SIZE;
        int index_y = y/GRID_SIZE;
        return index_y*this.board.getSize() + index_x;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
