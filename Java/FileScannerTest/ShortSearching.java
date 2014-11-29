
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortSearching {
	public static void main(String[] args) throws FileNotFoundException {

		String byteObs = "C:/Users/Setzo/Desktop/byteObs.txt";
		File textFile = new File(byteObs);
		Scanner input = new Scanner(textFile);
		short[][] matrix = new short[1000][1000];
		byte k = 0;

		while (input.hasNextLine()) {
			for (int i = 0; i <= 999; i++) {
				for (int j = 0; j <= 999; j++) {
					if (k != 2) {
						if (input.hasNextShort()) {
							matrix[i][j] = input.nextShort();
							System.out.printf("%d ", matrix[i][j]);
							k++;
						}
					}
					else {
						input.skip("|");
						k=0;
					}
				}
			}
		}

		input.close();
	}
}
*/
