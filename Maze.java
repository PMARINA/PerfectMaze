import java.util.Scanner;
public class Maze{
	private static boolean[][] north;
	private static boolean[][] south;
	private static boolean[][] east;
	private static boolean[][] west;
	private static boolean[][] check;
	private final static int buffer = 2; //This is how many extra there will be before and after the actual maze's coordinates, to accomodate special cases;

	public static void main(String[] args){
		int n = Integer.parseInt(args[0]); //Process Input
		n = validateN(n); //Check if n is valid. If not, get a new n 
		//value. Otherwise, leave n unchanged
		init(n); //Set up the arrays that define whether there is a wall 
		//to the north/south/east/west of a given point, and define 
		//the visited array
		generateMaze(north.length-buffer-1, buffer);//start at bottom 
		//left corner
		drawMaze();//output
	}
	/*Takes the north/west/east/south arrays and uses them to draw the maze. 
	 * Lines will be drawn with length 1, 0.5 above/below/left of/right of 
	 * the center of the cell. Note that since arrays count from top left
	 * to bottom right, as opposed to StdDraw, which counts from bottom
	 * left to top right, the maze will be flipped. This should not be a 
	 * problem since the maze will be noncontinuous in either case. 
	 */
	private static void drawMaze(){
		StdDraw.setScale(0,north.length-1);
		StdDraw.setPenColor(StdDraw.RED);
		Scanner sc = new Scanner(System.in);
		for(int i = buffer; i<north.length-buffer;i++){
			for(int j = buffer; j<north[i].length-buffer;j++){
				if(north[i][j])StdDraw.line(i-0.5,j+0.5,i+0.5,j+0.5);
				if(south[i][j])StdDraw.line(i-0.5,j-0.5,i+0.5,j-0.5);
				if(east[i][j]) StdDraw.line(i+0.5,j-0.5,i+0.5,j+0.5);
				if(west[i][j])StdDraw.line(i-0.5,j-0.5,i-0.5,j+0.5);
				System.out.println("i: " + i + "\nj: " + j);
				sc.next();
			}
		}
	}
	private static void generateMaze(int i, int j){
		int random = (int)(Math.random()*3);
		int[] ranToArr = getDirections(random,i,j);
		int newX = ranToArr[0];
		int newY = ranToArr[1];
		if(visNeighbors(i,j)){
			return;
		}
		while(check[newX][newY]){
			random = (int )(Math.random() * 3);
			int[] ran = getDirections(random,i,j);
			newX = ran[0];
			newY = ran[1];
		}
	}
	/*
	 * This method checks if all the neighbors of this cell have been
	 * visited. Note that this will not include diagonals
	 */
	private static boolean visNeighbors(int i, int j){
		return check[i+1][j] && check[i][j+1] && check[i-1][j] && check[i][j-1];
	}

	/*
	 * This method finds the direction that the maze generator should go in
	 * North = 0, South = 1, East = 2, West = 3+
	 */
	private static int[] getDirections(int random, int i, int j){
		if(random ==0){//north
			return new int[]{i,j+1};
		}
		else if(random ==1)return new int[]{i,j-1};
		else if(random ==2)return new int[]{i+1,j};
		else return new int[]{i-1,j};
	}
	private static int validateN(int n){
		while(n<0){
			System.out.println("The input is not valid. Please enter a valid input:");
			Scanner sc = new Scanner(System.in);
			n = sc.nextInt();
		}
		return n;
	}
	private static void init(int n){
		north = new boolean[n+2*buffer][n+2*buffer];
		for(int i = 0; i<north.length;i++)for(int j = 0; j<north[i].length;j++)north[i][j] = true;
		south = new boolean[n+2*buffer][n+2*buffer];
		for(int i = 0; i<south.length;i++)for(int j = 0; j<south[i].length;j++)south[i][j] = true;
		east  = new boolean[n+2*buffer][n+2*buffer];
		for(int i = 0; i< east.length;i++)for(int j = 0; j< east[i].length;j++) east[i][j] = true;
		west  = new boolean[n+2*buffer][n+2*buffer];
		for(int i = 0; i< west.length;i++)for(int j = 0; j< west[i].length;j++) west[i][j] = true;
		check = new boolean[n+2*buffer][n+2*buffer];
	}
}
