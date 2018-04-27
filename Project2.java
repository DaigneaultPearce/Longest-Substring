/*
 * Daigneault Pearce
 * Project 2
 * Comp482
 * 
 * Read input form a file and print length of longest common substring
 * Comments appear immediately above their corresponding statements.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Project2 {
	
	//String 1
	static String x = null;
	//String 2
	static String y = null;
	//String 3
	static String z = null;
	//Dynamic table
	static int[][][] dTable;
	
	public static void main(String[] args) throws IOException{
		
		//Read string input
		readIn();
		//Initialize table
		tableInit();
		//Populate table
		tablePopulate();
		//Output value of last cell which contains the answer
		System.out.println(dTable[y.length()][x.length()][z.length()]);		
	}
	
	public static void readIn() throws IOException{
		
		Scanner scan = new Scanner ( new File ( "input2.txt" ) );
		y = scan.next();
		x = scan.next();
		z = scan.next();
		scan.close();	
	}
	
	public static void tableInit() {
		
		//create dynamic programming table based of input value lengths.
		dTable = new int[ y.length() + 1 ][ x.length() + 1 ][ z.length() + 1 ];
		
		//initialize zeros.
		for( int i = 0; i <= y.length(); i++ )
			dTable[i][0][0] = 0;
		
		for( int i = 0; i <= x.length(); i++)
			dTable[0][i][0] = 0;
		
		for( int i = 0; i <= z.length(); i++)
			dTable[0][0][i] = 0;
		
	}
	
	public static void tablePopulate() {
		
		//Initialize variables to hold character values
		String x_sub;
		String y_sub;
		String z_sub;
		
		//Assign a value to the cell.
		for( int i = 1; i <= y.length(); i++ ) {
			y_sub = y.substring( i - 1, i );
			for( int j = 1; j <= x.length(); j++ ) {
				x_sub = x.substring( j - 1, j );
				for (int k = 1; k <= z.length(); k++) {
					z_sub = z.substring( k - 1, k );
					//If all characters are equal increment by 1
					if( x_sub.equals(y_sub) && y_sub.equals(z_sub) )
						dTable[i][j][k] = 1 + dTable[ i - 1 ][ j - 1 ][ k - 1 ];
					//Otherwise assign largest adjacent cell
					else
						dTable[i][j][k] = Math.max(Math.max(dTable[ i - 1 ][ j ][ k ], dTable[ i ][ j ][ k - 1 ]), dTable[ i ][ j - 1 ][ k ]);
				}
			}
		}
	}
}
