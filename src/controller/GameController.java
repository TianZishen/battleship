package controller;

import battleship.Board;
import view.GameView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 游戏控制器，用于响应玩家的鼠标操作
 *
 */
public class GameController implements MouseListener {
    /** 棋盘视图的引用 */
    private GameView gv;

    public GameController(GameView gv){
        this.gv = gv;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

        // TODO 3.1
        // 如果当前游戏仍未结束
        if(!this.gv.getGame().isGameEnded()){
            int blockId;

            Board board = gv.getBv2().getBoard();
            // 获取光标所在棋盘格
            blockId = gv.getBv2().getSelectedBlockId(arg0.getX(),arg0.getY());

            int x = board.getBlockXById(blockId);
            int y = board.getBlockYById(blockId);
            // 如果当前光标所在block没有棋子
            // 在棋盘格中hongzha
            if(!board.checkBlockIsBoomed(blockId)){
                board.boomBlock(blockId);
            }

            // 重画棋盘
            gv.getBv1().repaint();
            gv.getBv2().repaint();

            // 更新游戏状态，并判断游戏是否结束
            gv.endHumanRound();
            gv.getBv1().repaint();
            gv.getBv2().repaint();

        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}