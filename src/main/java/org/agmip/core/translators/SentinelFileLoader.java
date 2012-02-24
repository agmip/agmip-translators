package org.agmip.core.translators;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.agmip.core.types.*;
import org.agmip.core.types.weather.WeatherData;
import org.agmip.core.types.weather.WeatherFile;

public class SentinelFileLoader implements WeatherFile {
	
	
	public SentinelFileLoader()
	{
	}
	
	public ArrayList<AdvancedHashMap<String, Object>> readFile(String arg0){	
				
		String filename = arg0;
		
		ArrayList<AdvancedHashMap<String, Object>> result = new ArrayList<AdvancedHashMap<String, Object>>();
			
		
		try {
			FileInputStream fstream = new FileInputStream(filename);
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// first line should contain variable codes 
			String strLine  = br.readLine();
			String[] headers = strLine.toLowerCase().split(",");
			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) {
		
				AdvancedHashMap<String, Object> exp = new AdvancedHashMap<String, Object>();
				String[] values = strLine.split(",");
			    for (int i = 0; i < headers.length; i++) {
			    	if (i < values.length)
			    		exp.put(headers[i], values[i]);
			    	else 
			    		exp.put(headers[i], null);
			    }
			    result.add(exp);
			   
    		}
			
			//Close the input stream
			in.close();
			
		} catch (FileNotFoundException e) {
			  System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("IO error!");
		}
		
		return result;
	}


	
	public void readFile(String arg0, WeatherData arg1) {
		// TODO Auto-generated method stub
		
	}


	
	public void writeFile(String arg0, WeatherData arg1) {
		// TODO Auto-generated method stub
		
	}

}
