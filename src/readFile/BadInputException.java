package readFile;

public class BadInputException extends Error {
	static final long serialVersionUID=0;
	public String toString(){
		return "Bad or unreadable input file";
	}
}
