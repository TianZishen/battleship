package view;

import java.awt.*;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import battleship.Game;
import controller.GameController;

/**
 * 游戏视图类，为游戏主窗口
 *
 */
public class GameView extends JFrame {
    /** 游戏对象的引用 */
    private Game game;
    /** 棋盘视图的引用 */
    private BoardView bv1;
    private BoardView bv2;
    private InfoView iv;

    public GameView(String name, Game g){
        super(name);
        this.game = g;

        // 设置窗体属性
        this.setSize(new Dimension(1000, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setLocation(100, 100);
        this.getContentPane().setBackground(Color.blue);

        // 添加成员组件
        //玩家棋盘
        this.bv1 = new BoardView(this.game.getBoards()[0]);
        //this.bv1.addMouseListener(new GameController(this));
        getContentPane().add(this.bv1);

        //信息视窗
        this.iv = new InfoView();
        iv.label.setFont(new Font("宋体",Font.BOLD,25));
        iv.label.setText("海战棋");
        getContentPane().add(this.iv);

        //ai棋盘，玩家投炸弹
        this.bv2 = new BoardView(this.game.getBoards()[1]);
        this.bv2.addMouseListener(new GameController(this));
        getContentPane().add(this.bv2);

        this.pack();
        this.setVisible(true);
    }

    /**
     * 当游戏结束时展示的视图
     * @param w_id 获胜玩家的ID或-2
     */
    public void showEndGameView(int w_id){
        JDialog jd = new JDialog();
        jd.setTitle("Game over");
        jd.setPreferredSize(new Dimension(200,100));
        jd.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        jd.setLocationRelativeTo(this);

            // say congrats to the winner
            jd.add(new JLabel("Player "+this.game.getPlayerById(w_id).getPlayerName()+" wins!", JLabel.CENTER));


        //this.game.endGame(w_id);
        jd.pack();
        jd.setVisible(true);
    }

    public void endHumanRound(){
        game.endRound();
        if(game.isGameEnded()){
            showEndGameView(game.getWinnerId());
        }
        Random random= new Random();
        int x,y,blockId;
        do{
            x = random.nextInt(10);
            y = random.nextInt(10);
            blockId = x*10+y;
        } while(bv1.getBoard().checkBlockIsBoomed(blockId));
        bv1.getBoard().boomBlock(blockId);
        game.endRound();
        if(game.isGameEnded()){
            showEndGameView(game.getWinnerId());
        }
    }

    public BoardView getBv1() {
        return bv1;
    }

    public void setBv1(BoardView bv) {
        this.bv1 = bv;
    }

    public BoardView getBv2() {
        return bv2;
    }

    public void setBv2(BoardView bv2) {
        this.bv2 = bv2;
    }

    public InfoView getIv() {
        return iv;
    }

    public void setIv(InfoView iv) {
        this.iv = iv;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GameView g = new GameView("Morpion", new Game(3,1));

    }
}