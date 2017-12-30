package battleship;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Game {
    /** list of two players */
    private Player[] players;
    /** 游戏棋盘 */
    private Board[] boards = new Board[2];
    /** 当前游戏模式: 1-对战AI, 2-与人对战 */
    private int gameMode;
    /** 游戏是否已结束 */
    private boolean gameEnded;
    private int winnerId;
    /** 当前玩家ID */
    private int current_pid;
    
    
    public Game(int size, int mode){
        this.gameMode = mode;
        this.gameEnded = false;


        //根据游戏模式初始化玩家
        this.players = new Player[2];

        this.players[0] = new Player("Human", 0);
        this.players[1] = new Player("AI",1);
        //if(this.gameMode == 2){
        //    this.players[1] = new HumanPlayer("2", 2, 1);
        //}
        //else if(this.gameMode == 1){
        //    this.players[1] = new AIPlayer("AI", 2, 1);
        //}

        this.current_pid = 0;  // 设为第一个玩家

        //玩家棋盘
        this.boards[0] = new Board();
        this.boards[0].setMode(1);
        Board board1 = this.boards[0];

                File file = new File("map.mp");
                Reader reader = null;
                try {
                    // 一次读一个字符
                    reader = new InputStreamReader(new FileInputStream(file));
                    int tempchar;

                    for(int i = 0;i<10;i++){
                        for(int j = 0;j<10;j++){
                            tempchar = reader.read();
                            String s = String.valueOf((char)tempchar);
                            board1.setBlock(i*10+j,Integer.parseInt(s));
                        }
                    }
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }



                //Ai qipan
        this.boards[1]= new Board();
        file = new File("Bmap.mp");
        reader = null;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;

            for(int i = 0;i<10;i++){
                for(int j = 0;j<10;j++){
                    tempchar = reader.read();
                    String s = String.valueOf((char)tempchar);
                    this.boards[1].setBlock(i*10+j,Integer.parseInt(s));
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getCurrentPlayer(){
        return this.players[this.current_pid];
    }

    public Player getPlayerById(int w_id){
        return this.players[w_id];
    }


    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public void endRound(){
        if(boards[0].checkWinner() == 0){
            gameEnded = true;
            winnerId = 1;
        }else if(boards[1].checkWinner() == 0){
            gameEnded = true;
            winnerId = 0;

        }
        
        ;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Board[] getBoards() {
        return boards;
    }

    public void setBoards(Board[] boards) {
        this.boards = boards;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public int getCurrent_pid() {
        return current_pid;
    }

    public void setCurrent_pid(int current_pid) {
        this.current_pid = current_pid;
    }
}
