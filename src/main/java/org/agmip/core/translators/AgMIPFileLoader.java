package org.agmip.core.translators;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.agmip.core.types.AdvancedHashMap;


public class AgMIPFileLoader {
	
	static String WEATHERLOCATIONNAME   = "wsta_name";	
	static String WEATHERTIMESERIES     = "WeatherDaily";
	static String WEATHERTIMESERIESDATE = "w_date";

	public AdvancedHashMap<String, Object> readFile(String arg0){	
		
		String filename = arg0;
		
		AdvancedHashMap<String, Object> result = new AdvancedHashMap<String, Object>();

		try {
			FileInputStream fstream = new FileInputStream(filename);
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// first line should contain name of weather location. Example:
			// *WEATHER DATA : Jessore, Bangladesh + MERRA
			String strLine = br.readLine();
			int p = strLine.indexOf(":");
			String nameValue = String.copyValueOf (strLine.toCharArray(),  p + 1,  strLine.length() - strLine.indexOf(":")-1);
			result.put(WEATHERLOCATIONNAME, nameValue.trim());

			// skip blank lines
			while ((strLine = br.readLine()) != null){
				if (strLine.trim().length() > 0)
					break;
			}
			// return if nothing left
			if (strLine == null)
			 	return result;
			 	
			// read metadata of weatherlocation. Example:
			//@ INSI      LAT     LONG  ELEV   TAV   AMP REFHT WNDHT
			//  BDJE   23.200   89.330     6  26.3   5.9 -99.0 -99.0			
			
			String tmp = strLine.replaceAll(" +", " ");  // remove double spaces
			String[] headers = tmp.toLowerCase().split(" ");
			
			strLine = br.readLine().trim();
			tmp = strLine.replaceAll(" +", " "); 
			String[] values  = tmp.split(" ");
			
			// start with second header (@ is no variable)
			for (int i = 1; i < headers.length; i++) {
		    	if (i-1 < values.length) {
		    		if (headers[i] == "inst")
		    			headers[i] = "wsta_inst";
		    		result.put(headers[i], values[i-1]);
		    	}
		    		
		    	else 
		    		result.put(headers[i], null);
		    }
			
			//Read daily weather. Example:
			//@DATE    YYYY  MM  DD  SRAD  TMAX  TMIN  RAIN  WIND  DEWP  VPRS  RHUM
			//1980001  1980   1   1  15.0  26.0  12.2   0.0   1.4   4.8   8.6    25
			//......
			ArrayList<AdvancedHashMap<String, Object>> dailyArray = new ArrayList<AdvancedHashMap<String, Object>>();
			
			// skip blank lines
			while ((strLine = br.readLine()) != null){
				if (strLine.trim().length() > 0)
					break;
			}
			
			// return if nothing left
			if (strLine == null)
				return result;
			
			//first read headers
			tmp = strLine.replaceAll(" +", " "); 
			headers = tmp.toLowerCase().split(" ");
			
			while ((strLine = br.readLine()) != null) {
				AdvancedHashMap<String, Object> daily = new AdvancedHashMap<String, Object>();
				
				tmp = strLine.replaceAll(" +", " "); 
				values  = tmp.toLowerCase().split(" ");
								
				// store date as one string using format yyyymmdd
				String date = values[1];
				if (values[2].length() < 2) 
					date = date + '0';
				date = date + values[2];
				if (values[3].length() < 2) 
					date = date + '0';
				date = date + values[3];
				
				daily.put(WEATHERTIMESERIESDATE, date);
				
				// get rest of the values;
				for (int i = 4; i < headers.length; i++) {
			    	if (i < values.length)
			    		daily.put(headers[i], values[i]);
			    	else 
			    		daily.put(headers[i], null);
			    }
				
			    dailyArray.add(daily);
    		}
			result.put(WEATHERTIMESERIES, dailyArray);
			
			//Close the input stream
			in.close();
			
		} catch (FileNotFoundException e) {
			  System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("IO error!");
		}
		

		return result;
	}
	
}
