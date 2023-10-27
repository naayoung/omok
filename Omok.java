package omok;

import java.util.Arrays;
import java.util.Scanner;

public class Omok {
    public static void main(String[] args) {
        Player user = new Player("사용자", "O");
        Player computer = new Player("컴퓨터", "X");
        Board board = new Board(19);
        play(board, user, computer);
    }
    
    private static void play(Board board, Player user, Player computer) {
        Scanner sc = new Scanner(System.in);
        int time = 0;
        
    	board.print();
        
        result:
        while(true){
        	
        	while(true) {
        		System.out.print("사용자> ");
                char user_row = sc.next().charAt(0);
                String user_col = sc.next();
                int u_row = (int)(user_row)-65;
                int u_col = Integer.parseInt(user_col);
                
                if(u_row >= board.size || u_col >= board.size) {
                	System.out.println("보드 사이즈보다 큰 값은 입력할 수 없습니다.");
                	System.out.println("다시 입력해주세요.");
                	continue;
                } else if(board.map[u_col][u_row].equals(user.stone) || board.map[u_col][u_row].equals(computer.stone)) {
                	System.out.println("이미 배치된 자리입니다.");
                	System.out.println("다시 입력해주세요.");
                	continue;
                } else {
                	time++;
                	board.map[u_col][u_row] = user.stone;
                	board.print();
                	
            		if(search(board, user, u_col, u_row) == 2){
            			time--;
        	        	System.out.println("둘 수 없는 자리입니다.");
        	        	System.out.println("다시 입력해주세요.");
        	        	board.map[u_col][u_row] = ".";
                		continue;
            		} else if(search(board, user, u_col, u_row) == 3) {
            			time--;
            			System.out.println("33 입니다.");
        	        	System.out.println("다시 입력해주세요.");
        	        	board.map[u_col][u_row] = ".";
        	        	continue;
            		} else if(search(board, user, u_col, u_row) == 1) {
            			System.out.println(user.name + " 승리");
                		System.out.println(time +"수 진행");
                		break result;
            		}
                } break;
        	}
        	
            while(true) {
            	System.out.print("컴퓨터> ");
                char com_row = sc.next().charAt(0);
                int c_row = (int)(com_row)-65;
                String com_col = sc.next();
                int c_col = Integer.parseInt(com_col);
            
                if(c_row >= board.size || c_col >= board.size) {
                	System.out.println("보드 사이즈보다 큰 값은 입력할 수 없습니다.");
                	System.out.println("다시 입력해주세요.");
                	continue;
                } else if(board.map[c_col][c_row].equals(user.stone) || board.map[c_col][c_row].equals(computer.stone)) {
                	System.out.println("이미 배치된 자리입니다.");
                	System.out.println("다시 입력해주세요.");
                	continue;
                } else {
                	time++;
                	board.map[c_col][c_row] = computer.stone;
                	board.print();
                	
            		if(search(board, computer, c_col, c_row) == 2){
            			time--;
        	        	System.out.println("둘 수 없는 자리입니다.");
        	        	System.out.println("다시 입력해주세요.");
        	        	board.map[c_col][c_row] = ".";
                		continue;
            		} else if(search(board, computer, c_col, c_row) == 3) {
            			time--;
            			System.out.println("33 입니다.");
        	        	System.out.println("다시 입력해주세요.");
        	        	board.map[c_col][c_row] = ".";
        	        	continue;
            		} else if(search(board, computer, c_col, c_row) == 1) {
            			System.out.println(computer.name + " 승리");
                		System.out.println(time +"수 진행");
                		break result;
            		}
                } break;
            }
        }
    }
    //오목 확인
    static int search(Board board, Player player, int col, int row) {
    	int[] count = new int[4];
    	int count0 = 0;
    	int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int end = 0;
   
    	for(int num = -4; num < 5; num++) { 
    		if(row+num < 0 ) {
    			num++;
    		} else if(row+num+1 >= board.size) {
    			break;
    		} else {
    			if(board.map[col][row+num].equals(player.stone) && board.map[col][row+num] == board.map[col][row+num+1]) {
    				count1++;
    			}
    		}
    		count[0] = count1;
    	}
    	for(int num = -4; num < 5; num++) { 
			if(col+num < 0) {
				num++;
			} else if(col+num+1 >= board.size) {
    			break;
			} else {
				if(board.map[col+num][row].equals(player.stone) && board.map[col+num][row] == board.map[col+num+1][row]) {
    				count2++;
				}
			}
			count[1] = count2;
    	}
    	for(int num = -4; num < 5; num++) { 
			if(col+num < 0 || row-num < 1) {
				num++;
			} else if(col+num+1 >= board.size || row-num >= board.size ) {
				break;
			} else {
				if(board.map[col+num][row-num].equals(player.stone) && board.map[col+num][row-num] == board.map[col+num+1][row-num-1]) {
            		count3++;
				}
    		}
			count[2] = count3;
    	}
    	for(int num = -4; num < 5; num++) { 
			if(col+num < 0 || row+num < 0) {
				num++;
			} else if(col+num+1 >= board.size || row+num+1 >= board.size) {
				break;
			} else {
				if(board.map[col+num][row+num].equals(player.stone) && board.map[col+num][row+num] == board.map[col+num+1][row+num+1]) {
            		count4++;
				}
			}
			count[3] = count4;
    	}
    	//33확인
    	for(int i=0; i <= 3; i++) {
    		if(count[i] == 2) {
    			count0++;
    		}
    	}
    	if(count1 == 4 || count2 == 4 || count3 == 4 || count4 == 4) {
    		end = 1;
    	} else if(count1 > 4 || count2 > 4 || count3 > 4 || count4 > 4) {
    		end = 2;
    	} else if(count0 >= 2) {
    		end = 3;
    	}
		return end;
    }
}
