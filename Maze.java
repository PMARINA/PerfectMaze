import java.util.Scanner;
public class Maze{
	private static boolean[][] north;
	private static boolean[][] south;
	private static boolean[][] east;
	private static boolean[][] west;
	private static boolean[][] check;
	private final static int buffer = 1; //This is how many extra there will be before and after the actual maze's coordinates, to accomodate special cases;

	public static void main(String[] args) throws Exception{
		int n = Integer.parseInt(args[0]); //Process Input
		n = validateN(n); //Check if n is valid. If not, get a new n 
		//value. Otherwise, leave n unchanged
		init(n); //Set up the arrays that define whether there is a wall 
		//to the north/south/east/west of a given point, and define 
		//the visited array
		generateMaze(north.length-buffer-1, buffer);//start at bottom 
		//left corner
		drawMaze();//output
		////("The program is now terminating");
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
				////("i: " + i + "\nj: " + j);
				//sc.next(); //use for debugging purposes -- step-by
				//step drawing
			}
		}
	}
	private static void generateMaze(int i, int j)throws Exception{
		////("call to generateMaze(" + i + ", " + j + ");");
		// StdDraw.clear();
		// drawMaze();
		// StdDraw.textLeft(0,0,"i: " + i+ " & j: " + j);
		// while( ! StdDraw.hasNextKeyTyped()){
			// String s= null;
		// }
		// StdDraw.nextKeyTyped();
		if(checkDone() || visNeighbors(i,j)) return;
		while(! visNeighbors(i,j)){
			int random = (int)(Math.random()*4.0);
			int[] ranToArr = getDirections(random,i,j);
			int newX = ranToArr[0];
			int newY = ranToArr[1];
			int counter = 0;
			while(check[newX][newY]){
				random = (int )(Math.random() * 4.0);
				int[] ran = getDirections(random,i,j);
				newX = ran[0];
				newY = ran[1];
			}
			// StdDraw.clear();
			// drawMaze();
			knock(i,j,newX,newY); //knock down the wall between the two cells.
			check[i][j] = true;
			////("I: " + i + "\tJ: " + j + "\tNx: " + newX + "\tNy: " + newY);
			if(!visNeighbors(newX,newY))generateMaze(newX,newY);
			else check[newX][newY] = true;
			
		}
		//("Returning");
	}
	private static boolean checkDone(){
		for(int i = 0; i<check.length;i++){
			for(int j = 0; j<check[i].length;j++){
				if(!check[i][j])return false;
			}
		}
		return true;
	}
	private static void knock(int i, int j, int nx, int ny)throws Exception{
		if(i<nx){
			//We're going right/east
			east[i][j] = false;
			west[nx][ny] = false;
		}
		else if(i==nx){
			//We're going north/south
			//Do a north/south check
			if(j<ny){
				//We're going up/north
				north[i][j] = false;
				south[nx][ny] = false;
			}
			else if(j==ny){
				while(true){
					StdDraw.clear(StdDraw.RED);
					throw new Exception("same point given to knock(" + i + ", " + j+ ", int, int)");
				}
			}
			else{
				//We're going down/south
				south[i][j] = false;
				north[nx][ny] = false;
			}
		}
		else{
			//We're going left/west
			west[i][j] = false;
			east[nx][ny] = false;
		}
	}

	/*
	 * This method checks if all the neighbors of this cell have been
	 * visited. Note that this will not include diagonals.
	 * Test 2/11/17: 1402: exhibiting correct behaviour when absolute
	 * return true or false
	 */
	private static boolean visNeighbors(int i, int j) throws Exception{
		//return (check[i+1][j] && (check[i][j+1] && (check[i-1][j] && check[i][j-1])));
		boolean ret = (check[i+1][j] && (check[i][j+1] && (check[i-1][j] && check[i][j-1])));
		//("I: "+ i + "\tJ: " +j + "\tvisNeighbor returns: " + ret);
		/*
		for(int a = 0; a<check.length;a++){
			for(int b= 0; b<check.length;b++){
				if(check[a][b])System.out.print("+");
				else System.out.print("=");
			}
			//("");
		}
		*/
		//Thread.sleep(500);
		return ret;
	}

	/*
	 * This method finds the direction that the maze generator should go in
	 * North = 0, South = 1, East = 2, West = anyOtherInt
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
			////("The input is not valid. Please enter a valid input:");
			Scanner sc = new Scanner(System.in);
			n = sc.nextInt();
		}
		return n;
	}
	/*
	 * Initialize all the arrays to the correct states:
	 * N/S/E/W are all true, as walls need to be there
	 * for all the cells.
	 * Check is false for all the internal parts, not for
	 * the buffer parts, as the buffer parts shouldn't be
	 * used in the generation. This will remove necessity for
	 * border check in getDirections.
	 */
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
		for(int i = 0; i<check.length;i++)for(int j = 0; j<check[i].length;j++) check[i][j] = true;
		for(int i = buffer; i<check.length-buffer;i++)for(int j = buffer; j<check[i].length-buffer;j++)check[i][j] = false;
	}
}
