package ood.kwic;
/**
 * circularshifter filter
 * circularly shift each line to get new line
 */
import java.io.IOException;
import java.util.StringTokenizer;

public class CircularShifter extends Filter {
	
	
	public CircularShifter(Pipe reader, Pipe writer) {
		super(reader,writer);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
		try {
			StringBuilder strb = new StringBuilder(); //to store a single line
			int ch = reader.read();
			
			while(ch!=-1) {
				if((char)ch != '\n') {
					strb.append((char)ch); //store a single line into this string builder
				}else {
					int i = 0;
					String line = strb.toString();
					StringTokenizer token = new StringTokenizer(line); //use StringTokenizer to seperate the line into words
					String[] words = new String[token.countTokens()]; //a string array that stores words
					while(token.hasMoreTokens()) {
						words[i] = token.nextToken();
						i++;
					}
					
					//shift the line
					for(i = 0;i < words.length;i++) {
						
						String afterShift = "";  //store the line after shift
						for(int j = i;j < (words.length+i);j++) {
							afterShift += words[j%words.length];
							if(j<(words.length +i-1)) {
								afterShift +=" ";
							}
						}
							afterShift += '\n';
							char[] chars = afterShift.toCharArray();
							for(int k = 0;k < chars.length;k++) {
								writer.write(chars[k]);  //write the data into pipe to next filter
							}
					}
					
					strb.replace(0, strb.length(), "");  //reset the string builder
				}
				
				ch = reader.read();
			}
			
			writer.closeWriter();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Could not do cicular shift");
			//System.exit(1);
		}
		
	}
}
