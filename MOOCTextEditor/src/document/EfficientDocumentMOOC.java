package document;

import java.util.List;

/**
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words,
 * and sentences and then stores those values.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class EfficientDocumentMOOC extends DocumentMOOC {

	private int numWords;  // The number of words in the document
	private int numSentences;  // The number of sentences in the document
	private int numSyllables;  // The number of syllables in the document

	public EfficientDocumentMOOC(String text)
	{
		super(text);
		processText();
	}


	/** Return true if this string is a word (as opposed to punctuation)
	 * @param tok The string to check
	 * @return true if tok is a word, false otherwise. */
	private boolean isWord(String tok)
	{
	    // Note: This is a fast way of checking whether a string is a word
	    // You probably don't want to change it.
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}


    /** Passes through the text one time to count the number of words, syllables and
     * sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below.
     */
	private void processText()
	{
		// Provide this first line in the starter code.
		// Words are only strings of letters.  No numbers.
		// TODO: Finish this method.  Remember the countSyllables method from
		// Document.  That will come in handy here.
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		// This solution from user https://gist.github.com/Luckygirlllll
        String lastStr="";
        int totalSyllables = 0;

		for (String str: tokens) {

			if(isWord(str))	{

				numWords++;
				totalSyllables += countSyllables(str);

		    } else {
			numSentences++;
			lastStr=str;
		    }
		}

		if((tokens.lastIndexOf(lastStr)+1)!=tokens.size()) {
			numSentences++;
		}

		numSyllables = totalSyllables;




	}


	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 *
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
		//TODO: write this method.  Hint: It's simple
		return numWords;
	}

	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of
	 * characters in the document, even if they don't end with a punctuation mark.
	 *
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
        //TODO: write this method.  Hint: It's simple
        return numSentences;
	}

	/**
	 * Get the number of sentences in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for an "e" at the
	 * end of a word if the word has another set of contiguous vowels,
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
        //TODO: write this method.  Hint: It's simple
        return numSyllables;
	}

	// Can be used for testing
	// We encourage you to add your own tests here.
	public static void main(String[] args)
	{
	    testCaseMOOC(new EfficientDocumentMOOC("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCaseMOOC(new EfficientDocumentMOOC(""), 0, 0, 0);
        testCaseMOOC(new EfficientDocumentMOOC("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCaseMOOC(new EfficientDocumentMOOC("many???  Senteeeeeeeeeences are"), 6, 3, 2);    

	}


}
