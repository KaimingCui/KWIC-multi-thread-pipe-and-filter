package ood.kwic;
/**
 * Pipe class
 * It is a pipe, using PipeReader and PipeWriter to connect each thread(filter)
 * It is used to transport the data between thread(filter)
 */
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Pipe {
	
	private PipedReader reader;
	private PipedWriter writer;
	
	public Pipe() throws IOException{
		reader = new PipedReader();
		writer = new PipedWriter();
		writer.connect(reader);
	}
	
	public int read() throws IOException{  //read method
			return reader.read();
	}
	
	public void write(int ch) throws IOException{ //write method
		writer.write(ch);
	}
	
	public void closeReader() throws IOException{  //close reader
		reader.close();
	}
	
	public void closeWriter() throws IOException{  //close writer
		writer.flush();
		writer.close();
	}
	
	public void flush() throws IOException{  //flush writer
		writer.flush();
	}
}
