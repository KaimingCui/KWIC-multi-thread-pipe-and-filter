package ood.kwic;
/**
 * Upper letter transform filter
 * convert the first word of each line to upper letters
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class UpperTransform extends Filter{
	
	private boolean isFirstWord = true;
	
	public UpperTransform(Pipe reader,Pipe writer) {
		super(reader,writer);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		ArrayList<String> lines = new ArrayList<String>();
		StringBuilder strb = new StringBuilder();  //store a single line
		
		try {
			
			int ch = reader.read();
			
			
			while(ch != -1) {
				strb.append((char)ch);
				if(((char)ch) =='\n') {
					String line = strb.toString(); //store a single line into a string variable
					lines.add(line);		//add that line into a list<String>
					strb.replace(0, strb.length(), ""); //reset the stringbuilder
				}
				
				ch = reader.read();
			}
			
			Iterator<String> it = lines.iterator();
			while(it.hasNext()) {
				isFirstWord = true;
				char[] chars = ((String)it.next()).toCharArray();
				for(int i = 0;i <chars.length;i++) {
					if((chars[i]!=' ')&&isFirstWord == true) {
						if((chars[i]>='a')&&(chars[i]<='z')) { //convert the first word to upper letters
							chars[i] -=32;
						}
					}else {
						isFirstWord = false;
					}
					
					writer.write(chars[i]); //write the data into pipe
				}
			}

			writer.closeWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Could not change to upper letters.");
			//System.exit(1);
		}
	}
}
