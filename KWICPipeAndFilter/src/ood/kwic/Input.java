package ood.kwic;
/**
 * Input filter
 * Read the data from File input stream and write them into a pipe
 */
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Input extends Filter{
	
	private InputStream input;
	private boolean isNewWord = false;
	private boolean isLineStart = false;
	private boolean isNewLine = false;
	
	
	public Input(InputStream input,Pipe writer) {
		super(null, writer);
		this.input = input;
	}
	
	

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
		try {
			int ch = input.read(); //read a character
			
			while(ch != -1) {  //read all data one by one
				
				if(ch == '\n') {
					isNewLine = true;
				}else if(ch == ' '){
					isNewWord = true;
				}else if(ch == '\t') {
					isNewWord = true;
				}else {
					if(isNewWord) {
						if(isLineStart) {
							writer.write(' '); //if a word is done, write a ' ' into pipe
						}
						isNewWord = false;
					}
					if(isNewLine) {
						writer.write('\n');  //if it is a new line, write a \n into pipe
						isNewLine = false;
						isLineStart = false;
					}
					writer.write(ch);  //write the character into pipe
					isLineStart = true;
				}
				
				ch = input.read();
			}
			
			writer.write('\n');
			input.close();
			writer.closeWriter();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Could not read the input file.");
			//System.exit(1);
		}
	}
}
