package ood.kwic;
/**
 * Deletestopword filter
 * delete the lines that start with a noise word
 * the noise word are "a an the A AN THE"
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


public class DeleteStopword extends Filter{

	private ArrayList<String> stopWords;
	
	public DeleteStopword(Pipe reader,Pipe writer,ArrayList<String> deleteWords) {
		super(reader,writer);
		stopWords = deleteWords;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub

		try {

			ArrayList<String> lines = new ArrayList<String>();
			StringBuilder strb = new StringBuilder("");
			
			//still the same thing, read the data line by line and store each line into a list
			int ch = reader.read();
			while(ch != -1) {
				strb.append((char)ch);
				if(((char)ch)=='\n') {
					String line = strb.toString();
					lines.add(line);
					strb.replace(0, strb.length(), ""); //clear the string builder
				}
				
				ch = reader.read();
			}
			
			
			//Delete the shifts that start with a stopword
			for(int i = 0;i < lines.size();i++) {
				String targetLine = new String(lines.get(i));
				StringTokenizer token = new StringTokenizer(targetLine);
				String firstword = token.nextToken();
				for(int j = 0;j<stopWords.size();j++) {
					if(firstword.equals(stopWords.get(j))||firstword.equals(stopWords.get(j).toUpperCase())) {
						lines.remove(i);
						i--;
						break;
					}
				}
			}
			
			
			//write the data into pipe
			Iterator<String> it = lines.iterator();
			while(it.hasNext()) {
				char[] chars = it.next().toCharArray();
				for(int k = 0;k < chars.length;k++) {
					writer.write(chars[k]);
				}
			}
			
			writer.closeWriter();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not delete stopwords line.");
			//System.exit(1);
		}
	}
}
