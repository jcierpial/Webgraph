package readFile;

public class DriveReadFile {
	public static void main(String [ ] args){
		ReadFile rf = new ReadFile("inputgraph.txt");
		System.out.println(rf.getNumberOfWebpages());
		for(int i = 0; i<rf.getNumberOfWebpages(); i++){
			String title = rf.getPageTitle(i);
			int nol = rf.getNumberOfOutLinks(i);
			int nkp = rf.getNumberOfKeyPhrases(i);
			System.out.println(nol+" "+nkp+" " + title);
			for (int k = 0; k<nol; k++){
				System.out.print(rf.getOutLinks(i).get(k)+" ");
			}
			System.out.println();
			for (int k = 0; k<nkp; k++){
				System.out.println(rf.getKeyPhrases(i).get(k));
			}
			
		}
	}
}
