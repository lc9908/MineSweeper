package MineSweeper;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class GameBoard extends JFrame implements MouseListener{

	JPanel grid[][] = new JPanel[9][9];
	int gameBoard[][];
	JLabel label[][] = new JLabel[9][9];
	boolean checked[][] = new boolean[9][9];

    public GameBoard(){
        setTitle("Minesweeper");
		setSize(700,700);
		setVisible(true);
		setLayout(new GridLayout(9,9));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//start_gamel
		build_board();

    }

	public void build_board(){
		Main gb = new Main();
		gameBoard = gb.getBoard();
		for(int i = 0; i < 9; i++) {
			for(int j =0; j <9; j++) {
				grid[i][j] = new JPanel();
				label[i][j] = new JLabel(""+ gameBoard[i][j]);
				label[i][j].setFont( new Font("Serif",Font.PLAIN,22));
				grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				grid[i][j].setBackground(Color.WHITE);
				grid[i][j].addMouseListener(this);
				add(grid[i][j]);		
			}
		}	
	}

	public void print(){
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String args[]) {
		new GameBoard();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() ==  MouseEvent.BUTTON3) { 
			for(int i = 0; i < gameBoard.length; i++){
			    for(int j = 0; j < gameBoard[i].length; j++){
			        if(grid[i][j] == e.getSource() && checked[i][j] == false) {
						if(grid[i][j].getBackground() == Color.WHITE){
							grid[i][j].setBackground(Color.RED);
						} else {
							grid[i][j].setBackground(Color.WHITE);
						}

						// grid[i][j].revalidate();
						// grid[i][j].repaint();
					}
					
			   }
			}
		} else if(e.getButton() ==  MouseEvent.BUTTON1){
			for(int i = 0; i < gameBoard.length; i++){
			    for(int j = 0; j < gameBoard[i].length; j++){
			        if(grid[i][j] == e.getSource()) {
						checkSide(i,j);
					}
					
			   }
			}
		}
		
		win_condition();
	}

	public void win_condition(){
		int w = 0;
		for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                if(checked[i][j] == false){
					w++;
				}
            }
        }
		if(w == 9){// You Win
			JOptionPane.showMessageDialog(null, "You Win!", "Result", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	public void lose(){
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard[i].length; j++){
				if(gameBoard[i][j] == 8){
					grid[i][j].setBackground(Color.BLACK);
				}
				grid[i][j].removeMouseListener(this);
			}		
		}
		JOptionPane.showMessageDialog(null, "You Lose!", "Result", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
		
	}

	public void checkSide(int row, int col){

		if(gameBoard[row][col] == 8 ){
			grid[row][col].setBackground(Color.BLACK);
			lose(); 
			return;
		} 

		if(grid[row][col].getBackground() != Color.WHITE){
			grid[row][col].setBackground(Color.WHITE);
		}

		grid[row][col].add(label[row][col]);
		grid[row][col].revalidate();
		grid[row][col].repaint();
		checked[row][col] = true;

		if(gameBoard[row][col] != 0)
			return;
		int x[] = {1,1,0,-1,-1,-1,0,1};
		int y[] = {0,1,1,1,0,-1,-1,-1};	
		for(int i = 0; i < x.length; i++){
			int adj_x = x[i] + row;
			int adj_y = y[i] + col;
			if(valid(adj_x,adj_y) && !checked[adj_x][adj_y]){
				checkSide(adj_x,adj_y);
			}
		}
	}

	public boolean valid(int x, int y){
		return x >= 0 && y >= 0 && x < 9  && y < 9;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
