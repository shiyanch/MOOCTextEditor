package textgen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		if(sourceText.length() == 0)
			return;
		
		List<String> words = new ArrayList<>();  
		Pattern pattern = Pattern.compile("[^ ]+");
		Matcher matcher = pattern.matcher(sourceText);
		
		while (matcher.find()) {
			words.add(matcher.group());
		}
		
		starter = words.get(0);
		String prevWord = starter;
		
		for(int i=1;i<words.size();i++) {
			Iterator iterator = wordList.iterator();
			boolean found = false;
			while(iterator.hasNext()) {
				ListNode currentNode = (ListNode)iterator.next();
				if(currentNode.getWord().equals(prevWord)) {
					currentNode.addNextWord(words.get(i));
					found = true;
				}
			}
			
			if(!found) {
				ListNode newNode = new ListNode(prevWord);
				newNode.addNextWord(words.get(i));
				wordList.add(newNode);
			}
			
			prevWord = words.get(i);
		}
		
		ListNode lastNode = null;
		Iterator iterator = wordList.iterator();
		while(iterator.hasNext()) {
			ListNode currentNode = (ListNode)iterator.next();
			if(currentNode.getWord().equals(words.get(words.size()-1))) {
				lastNode = currentNode;
				lastNode.addNextWord(starter);
				break;
			}
		}
		if(lastNode == null) {
			lastNode = new ListNode(words.get(words.size()-1));
			lastNode.addNextWord(starter);
			wordList.add(lastNode);
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if(wordList.isEmpty() || starter.equals("") || numWords <= 0)
			return "";
		
		String currWord = starter;
		StringBuilder sb = new StringBuilder("");
		sb.append(currWord);
		
		while(numWords > 1) {
			ListNode lastNode = null;
			Iterator iterator = wordList.iterator();
			while(iterator.hasNext()) {
				ListNode currentNode = (ListNode)iterator.next();
				if(currentNode.getWord().equals(currWord)) {
					String word = currentNode.getRandomNextWord(rnGenerator);
					sb.append(" ");
					sb.append(word);
					currWord = word;
					numWords--;
					break;
				}
			}
		}
		
		return sb.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList.clear();
		starter = "";
		train(sourceText);
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int random = generator.nextInt(nextWords.size());
	    return nextWords.get(random);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


