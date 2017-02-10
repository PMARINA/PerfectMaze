import java.util.Scanner;
public class Maze{
	private static boolean[][] north;
	private static boolean[][] south;
	private static boolean[][] east;
	private static boolean[][] west;
	private final static int buffer = 2; //This is how many extra there will be before and after the actual maze's coordinates, to accomodate special cases;

	public static void main(String[] args){
		int n = Integer.parseInt(args[0]); //Process Input
		n = validateN(n); //Check if n is valid. If not, get a new n value. Otherwise, leave n unchanged
		init(n); //Set up the arrays that define whether there is a wall to the north/south/east/west of a given point. 
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
		north = new int[n+2*buffer][n+2*buffer];
		for(int i = 0; i<north.length;i++)for(int j = 0; j<north[i].length;j++)north[i][j] = true;
		south = new int[n+2*buffer][n+2*buffer];
		for(int i = 0; i<south.length;i++)for(int j = 0; j<south[i].length;j++)south[i][j] = true;
		east  = new int[n+2*buffer][n+2*buffer];
		for(int i = 0; i< east.length;i++)for(int j = 0; j< east[i].length;j++) east[i][j] = true;
		west  = new int[n+2*buffer][n+2*buffer];
		for(int i = 0; i< west.length;i++)for(int j = 0; j< west[i].length;j++) west[i][j] = true;
	}
}
