package MineSweeper;

public class Main {

    int[][] board;
    Bomb[] bomb_location;

    public Main(){
        board = new int[9][9];
        bomb_location = new Bomb[9];
        setBomb();


    }

    public void print(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getBoard(){
        return board;
    }

    public void setBomb(){
        int num_bomb_on_board = 0;
        int[] adjx = {1,0,-1,0,1,1,-1,-1};
        int[] adjy = {0,1,0,-1,1,-1,1,-1};
		while(num_bomb_on_board < 9) {
			int x = (int)(Math.random()* 9);
			int y = (int)(Math.random()* 9);
			if(board[x][y] == 0) {
				board[x][y] = 8;
				num_bomb_on_board++;
                for(int i = 0; i < adjx.length; i++){
                    int nextx = adjx[i] + x;
                    int nexty = adjy[i] + y;
                    if(valid(nextx,nexty)){
                        board[nextx][nexty]++;
                    }

                }
			}        
		}
    }

    public boolean valid(int x, int y){
        return x >=0 && x < 9 && y >= 0 && y < 9;
    }

    public static void main(String args[]){
        new Main();
    }
    
}
