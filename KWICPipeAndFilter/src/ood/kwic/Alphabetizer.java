package ood.kwic;
/**
 * Alphabetizer filter
 * sort the lines
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.IOException;

public class Alphabetizer extends Filter{
	
	public Alphabetizer(Pipe reader,Pipe writer){
		super(reader,writer);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		ArrayList<String> lines = new ArrayList<String>();
		StringBuilder strb = new StringBuilder("");
		
		try {
			int ch = reader.read();
			while(ch != -1) {
				strb.append((char)ch);
				if((char)ch == '\n') {
					String line = strb.toString();  //add each line into a list
					lines.add(line);
					strb.replace(0, strb.length(), "");  //reset the string builder
				}
				
				ch = reader.read();
			}
			
			Collections.sort(lines);  //sort the list<String>
			
			Iterator<String> it = lines.iterator();
			while(it.hasNext()) {
				char[] chars = it.next().toCharArray();
				for(int i = 0;i < chars.length;i++) {
					writer.write(chars[i]);  //write date into pipe
				}
			}
			
			writer.closeWriter();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Could not sort.");
			//System.exit(1);
		}
		
	}
}
