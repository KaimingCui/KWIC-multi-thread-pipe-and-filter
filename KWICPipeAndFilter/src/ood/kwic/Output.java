package ood.kwic;
/**
 * output filter
 * output the result on screen and also store the results into a file called "output.txt"
 */
import java.io.FileOutputStream;
import java.io.IOException;

public class Output extends Filter{
	private FileOutputStream fos;
	
	
	public Output(Pipe reader,FileOutputStream fos) {
		super(reader,null);
		this.fos = fos;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		try {
			int ch = reader.read();
			while(ch!=-1) {
				
				System.out.print((char)ch);
				fos.write((char)ch);
				ch = reader.read();
			}
			
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Could not ouput the file.");
			//System.exit(1);
		}
	}
}
