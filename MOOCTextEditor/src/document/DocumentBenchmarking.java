package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 500;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
	    // You can play around with this
		int increment = 20000;

		// The number of steps to run.  
		// You can play around with this.
		int numSteps = 20;
		
		// THe number of characters to start with. 
		// You can play around with this.
		int start = 50000;
		
		// TODO: Fill in the rest of this method so that it runs two loops
		// and prints out timing results as described in the assignment 
		// instructions.
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			// numToCheck holds the number of characters that you should read from the 
			// file to create both a BasicDocument and an EfficientDocument.  
			
			/* Each time through this loop you should:
			 * 1. Print out numToCheck followed by a tab (\t) (NOT a newline)
			 * 2. Read numToCheck characters from the file into a String
			 *     Hint: use the helper method below.
			 * 3. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates a BasicDocument 
			 *     b. Calls fleshScore on this document
			 * 4. Print out the time it took to complete the loop in step 3 
			 *      (on the same line as the first print statement) followed by a tab (\t)
			 * 5. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates an EfficientDocument 
			 *     b. Calls fleshScore on this document
			 * 6. Print out the time it took to complete the loop in step 5 
			 *      (on the same line as the first print statement) followed by a newline (\n) 
			 */  
			
			
			//1
			System.out.print(numToCheck + "\t"); 
			//2
			String textForUsing = getStringFromFile(textfile, start);
			//Performance for BasicDocument
			
			long startTime = System.nanoTime();
			
			for(int i = 0; i<trials; i++) {
				BasicDocument exDoc = new document.BasicDocument(textForUsing);
				exDoc.getFleschScore();
				//We can use methods for compare results as seen below:
				//exDoc.countSyllables(textForUsing);
				//exDoc.getNumWords();
				//exDoc.getNumSentences();
				//exDoc.getNumSyllables();
			}
			long endTime = System.nanoTime();
			
			double estTime = ((endTime-startTime)/(Math.pow(10, 8)));
			
			//System.out.print("BasicDocument: " + estTime + "\t");
			System.out.print(estTime + "\t");
			
			//Performance for EfficientDocument
			long startTimeSec = System.nanoTime();
			
			for(int i = 0; i<trials; i++) {
				EfficientDocument exDocSec = new document.EfficientDocument(textForUsing);
				exDocSec.getFleschScore();
				//We can use methods for compare results as seen below:
				//exDocSec.countSyllables(textForUsing);
				//exDocSec.getNumWords();
				//exDocSec.getNumSentences();
				//exDocSec.getNumSyllables();
			}
			long endTimeSec = System.nanoTime();
			
			double estTimeSec = ((endTimeSec-startTimeSec)/(Math.pow(10, 8)));
			
			//System.out.print("EfficientDocument: " + estTimeSec + "\t");
			//System.out.print(estTimeSec + "\t" + "\n");
			System.out.print(estTimeSec + "\t");
			
			//Performance for BasicDocument by MOOC Team
			long startTimeMOOC = System.nanoTime();
			
			for(int i = 0; i<trials; i++) {
				BasicDocumentMOOC exDocMOOC = new document.BasicDocumentMOOC(textForUsing);
				exDocMOOC.getFleschScore();
				//We can use methods for compare results as seen below:
				//exDocMOOC.(textForUsing);
				//exDocMOOC.getNumWords();
				//exDocMOOC.getNumSentences();
				//exDocMOOC.getNumSyllables();
			}
			long endTimeMOOC = System.nanoTime();
			
			double estTimeMOOC = ((endTimeMOOC-startTimeMOOC)/(Math.pow(10, 8)));
			
			//System.out.print("BasicDocument by MOOC team: " + estTimeMOOC + "\t" + "\n");
			//System.out.print("BasicDocument by MOOC team: " + estTimeMOOC + "\t");
			System.out.print(estTimeMOOC + "\t");
			
			
			//Performance for EfficientDocument by MOOC Team
			long startTimeMOOCEff = System.nanoTime();
			
			for(int i = 0; i<trials; i++) {
				EfficientDocumentMOOC exDocMOOCEff = new document.EfficientDocumentMOOC(textForUsing);
				exDocMOOCEff.getFleschScore();
				//We can use methods for compare results as seen below:
				//exDocMOOCEff.(textForUsing);
				//exDocMOOCEff.getNumWords();
				//exDocMOOCEff.getNumSentences();
				//exDocMOOCEff.getNumSyllables();
			}
			long endTimeMOOCEff = System.nanoTime();
			
			double estTimeMOOCEff = ((endTimeMOOCEff-startTimeMOOCEff)/(Math.pow(10, 8)));
			
			//System.out.print("EfficientDocument by MOOC team: " + estTimeMOOCEff + "\t" + "\n");
			System.out.print(estTimeMOOCEff + "\t" + "\n");
			
			
			
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
