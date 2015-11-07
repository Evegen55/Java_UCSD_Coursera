package document;

import java.util.List;

/**
 * A naive implementation of the Document abstract class.
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocumentMOOC extends DocumentMOOC
{
	/** Create a new BasicDocument object
	 *
	 * @param text The full text of the Document.
	 */
	public BasicDocumentMOOC(String text)
	{
		super(text);
	}


	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 *
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		List<String> tokens = getTokens("[a-zA-Z]+");
		return tokens.size();
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
	public int getNumSentences()
	{
		// The pattern below will break for floating point numbers,
		// abbreviations, and other edge cases
		List<String> tokens = getTokens("[^?.!]+");
		return tokens.size();
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
	public int getNumSyllables()
	{
		List<String> tokens = getTokens("[a-zA-Z]+");
		int totalSyllables = 0;
		for (String word : tokens)
		{
			totalSyllables += countSyllables(word);
		}
		return totalSyllables;
	}


	/* The main method for testing this class.
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCaseMOOC(new BasicDocumentMOOC("This is a test.  How many???  Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCaseMOOC(new BasicDocumentMOOC(""), 0, 0, 0);
		testCaseMOOC(new BasicDocumentMOOC("sentence, with, lots, of, commas.!  (And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCaseMOOC(new BasicDocumentMOOC("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCaseMOOC(new BasicDocumentMOOC("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		testCaseMOOC(new BasicDocumentMOOC("Segue."), 2, 1, 1);

	}

}
