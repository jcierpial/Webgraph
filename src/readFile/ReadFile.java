package readFile;
import java.io.*;
import java.util.*;

public class ReadFile {
	int numPages;
	Page [ ] pages;
	BufferedReader inputStream;
	
	public ReadFile(String fileName) {
		try{
			inputStream = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e){
			throw new BadInputException();
		} 
		
		numPages = readNum();
		pages = new Page [numPages];
		for (int i = 0; i < numPages;i++){
			int id = readNum();
			String title = readString( );
			
			pages[id] = new Page(title);
	
			String outLinks = readString( );
			int scPlace = outLinks.indexOf(';');
			while (scPlace != -1){
				try{
					String intString = outLinks.substring(0, scPlace);
					outLinks = outLinks.substring(scPlace+1);
					int linkTo = Integer.parseInt(intString);
					pages[id].addOutlink(linkTo);
				} catch (Throwable t){
					throw new BadInputException();
				}
				scPlace = outLinks.indexOf(';');	
			}
			String keyPhrases = readString( );
			int cPlace = keyPhrases.indexOf(',');
			while (cPlace != -1){
				try{
					String kp = keyPhrases.substring(0, cPlace).trim();
					keyPhrases= keyPhrases.substring(cPlace+1);
					pages[id].addKeyPhrase(kp);
				} catch (Throwable t){
					throw new BadInputException();
				}
				cPlace = keyPhrases.indexOf(',');
			}
				
			
		}
	}
	
	public int readNum( ){
		try{
			return readNum(inputStream.readLine());
		} catch (Throwable t){
			throw new BadInputException();
		}
	}
		
	public int readNum(String string){
		try{
			return Integer.parseInt(string);
		} catch (Throwable t){
			throw new BadInputException();
		}
		
	}
	
	public String readString(){
		try{
			return inputStream.readLine();
		} catch (Throwable t){
			throw new BadInputException();
		}
		
	}
	
	public int getNumberOfWebpages(){
		return numPages;
	}
	
	public String getPageTitle(int pageNo){
		return pages[pageNo].title;
	}	

	public int getNumberOfKeyPhrases(int pageNo){
		return pages[pageNo].getNumberOfKeyPhrases( );
	}
	
	public ArrayList<String> getKeyPhrases(int pageNo){
		return pages[pageNo].getKeyPhrases( );
	}

	public int getNumberOfOutLinks(int pageNo){
		return pages[pageNo].getNumberOfOutLinks( );
	}
	
	public ArrayList<Integer> getOutLinks(int pageNo){
		return pages[pageNo].getOutLinks( );
	}

}
	