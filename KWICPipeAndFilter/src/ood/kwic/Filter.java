package ood.kwic;
/**
 * Filter
 * it is the parent calss for all filter
 * it includes two Pipe object, one for reading, one for writing, 
 * therefore, filters could connect to each other by these two pipe
 * @author kaimingcui
 *
 */
public abstract class Filter implements Runnable {
	
	protected Pipe reader;  //two pipe
	protected Pipe writer;
	
	public Filter(Pipe reader,Pipe writer) {
		this.reader = reader;
		this.writer = writer;
	}
	
	//getter and setter even though I did not use them because I set the Pipes as protected
	public Pipe getReader() {
		return this.reader;
	}
	
	public Pipe getWriter() {
		return this.writer;
	}
	
	public void  run() {   //implement thread
		process();
	}
	
	abstract public void process();  //all filters need to implement this method to run
	
	public void start() {  //start the thread

			Thread th = new Thread(this);
			th.start();
	}

}
