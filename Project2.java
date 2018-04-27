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
	//Dynamic table
	static int[][] dTable;
	
	public static void main(String[] args) throws IOException{
		
		//Read string input
		readIn();
		//Initialize table
		tableInit();
		//Print table
		tablePrint();
		//Populate table
		System.out.println("\n" + tablePopulate());
		
		
		
	}
	
	public static void readIn() throws IOException{
		
		Scanner scan = new Scanner ( new File ( "input2.txt" ) );
		y = scan.next();
		x = scan.next();
		scan.close();
		
	}
	
	public static void tableInit() {
		
		//create dynamic programming table based of input value lengths.
		dTable = new int[ y.length() + 1 ][ x.length() + 1 ];
		
		//initialize zeros.
		for( int i = 0; i <= y.length(); i++ )
			dTable[i][0] = 0;
		
		for( int i = 0; i <= x.length(); i++)
			dTable[0][i] = 0;
		
	}
	
	public static void tablePrint() {
		
		for(int i = 0; i <= y.length(); i++) {
			System.out.println();
			for(int j = 0; j <= x.length(); j++)
				System.out.print(dTable[i][j]);
			
		}
	}
	
	public static int tablePopulate() {
		
		//initialize variables to hold character values
		String x_sub;
		String y_sub;
		int max = 0;
		
		for( int i = 1; i <= y.length(); i++ ) {
			y_sub = y.substring( i - 1, i );
			//System.out.print(y_sub);
			for( int j = 1; j <= x.length(); j++ ) {
				x_sub = x.substring( j - 1, j );
				//System.out.print(x_sub);
				if( x_sub.equals(y_sub) ) {
					dTable[i][j] = 1 + dTable[i - 1][j - 1];
				}
				else {
					dTable[i][j] = Math.max(dTable[ i - 1 ][ j ],dTable[ i  ][ j - 1 ]);
				}
				//update max
				max = dTable[i][j];
			}
		}
		tablePrint();
		return max;
	}
}
