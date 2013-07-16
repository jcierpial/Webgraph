package readFile;
import java.util.*;

public class Page {
	public ArrayList<Integer> outLinks;
	int numOutLinks;
	ArrayList<String> keyPhrases;
	int numKeyPhrases;
	String title;
	
	public Page(String title){
		this.title = title;
		numOutLinks = 0;
		numKeyPhrases = 0;
		outLinks = new ArrayList<Integer>();
		keyPhrases = new ArrayList<String>();
	}
	
	public void addOutlink(int id){
		numOutLinks ++;
		outLinks.add(id);
	}
	
	public void addKeyPhrase(String kp){
		numKeyPhrases ++;
		keyPhrases.add(kp);
	}
	
	public int getNumberOfOutLinks( ){
		return numOutLinks;
	}
	
	public ArrayList<Integer> getOutLinks( ){
		return outLinks;
	}
	
	public int getNumberOfKeyPhrases( ){
		return numKeyPhrases;
	}
	
	public ArrayList<String> getKeyPhrases( ){
		return keyPhrases;
	}
	
}
