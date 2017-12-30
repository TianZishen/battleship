package battleship;

public class Board {
    private int[][] blocks;
    private int size;
    private int mode;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Board(){
        this.size = 10;
        this.blocks = new int[10][];
        mode = 0;
        for(int i=0; i<10; i++){
            this.blocks[i] = new int[10];
        }
    }

    public boolean checkHit(int blockId){
        int x;
        int y;
        x = blockId / 10;
        y = blockId - x*10;
        if(blocks[x][y] == 1){
            return true;
        }else return false;
    }

    /**
     * 检查当前棋盘状态，看是否已经产生赢家
     * @return 返回-1:游戏继续；0:全部摧毁
     */
    public int checkWinner(){
        int result = 0;
        for(int[] block : blocks){
            for(int b:block){
                if(b == 1) result = -1;
            }
        }
        return result;

    }

    public String toString(){
        String s = "---\n|";
        for(int i=0;i<this.size;i++){
            for(int j=0;j<this.size;j++){
                s += this.blocks[i][j]+"|";
            }
            s += "\n";
        }
        s += "---";
        return s;
    }

    public void setBlock(int blockId, int mark){
        int x;
        int y;
        x = blockId / 10;
        y = blockId - x*10;
        blocks[x][y] = mark;
    }

    public void boomBlock(int blockId){
        int x;
        int y;
        x = blockId / 10;
        y = blockId - x*10;
        getBlocks()[y][x] += 2;
    }

    public boolean checkBlockIsBoomed(int blockId){
        int x;
        int y;
        x = blockId / 10;
        y = blockId - x*10;
        if(blocks[y][x] >=2){
            return true;
        }else return false;

    }

    public int getBlockXById(int blockId){
        int y =blockId/this.size;
        return  blockId - y * this.size;
    }

    public int getBlockYById(int blockId){
        return  blockId/this.size;
    }

    public int[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(int[][] blocks) {
        this.blocks = blocks;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
