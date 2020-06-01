package ood.kwic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KWICStarter {
		
		// TODO Auto-generated method stub
	public void starter(){
		try {
			String file = "./input.txt"; //input file name
			String deleteWords = "./noise.txt";	 //noises file
			String outputfile = "./output.txt"; //output file name
			
			//instantiate the Pipes
			Pipe in_ut = new Pipe();
			Pipe ut_cs = new Pipe();
			Pipe cs_ut = new Pipe();
			Pipe ut_a = new Pipe();
			Pipe a_ds = new Pipe();
			Pipe ds_out = new Pipe();
			
			//Read the noise file and stores the stopwords into a list
			ArrayList<String> stopWords = new ArrayList<String>();
			String str = "";
			FileInputStream de = new FileInputStream(deleteWords);
			BufferedReader br = new BufferedReader(new InputStreamReader(de));
			while((str = br.readLine())!=null) {
				stopWords.add(str);
			}
			br.close();
			de.close();
			
			//file stream for input and output
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(outputfile);
			
			//instantiate the filters and connect them by pipes
			Input input = new Input(fis,in_ut);
			UpperTransform ut1 = new UpperTransform(in_ut,ut_cs);
			CircularShifter cs = new CircularShifter(ut_cs,cs_ut);
			UpperTransform ut2 = new UpperTransform(cs_ut,ut_a);
			Alphabetizer a = new Alphabetizer(ut_a,a_ds);
			DeleteStopword ds = new DeleteStopword(a_ds,ds_out,stopWords);
			Output output = new Output(ds_out,fos);
			
			//start the threads
			input.start();
			ut1.start();
			cs.start();
			ut2.start();
			a.start();
			ds.start();
			output.start();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException eh) {
			eh.printStackTrace();
		}
	}

		
	}
