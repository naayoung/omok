package omok;

public class Board {
    int size;
    String[][] map;
    Board(int size) {
        this.size = size;
        map = new String[size][size];
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                map[row][col] = ".";
            }
        }
    }
    
    public void print() {
        for (int row = 0; row < size; row++) {
        	System.out.printf("%2s",row);
            for (int col = 0; col < size; col++) {
            	System.out.print(" "+ map[row][col]);
            }
            System.out.println();
       	 	if(row == size-1) {
       	 		System.out.print("   ");
       	 		for(int i = 0; i < size; i++) {
           	 		System.out.print((char)(i+65));
           	 		System.out.print(" ");
       	 		}
       	 	System.out.println();
       	 	}
        }
    }
}