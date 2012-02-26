import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.agmip.core.types.AdvancedHashMap;
import org.agmip.core.types.weather.WeatherFile;


public class AquaCropConverter implements WeatherFile {

	@Override
	public AdvancedHashMap<String, Object> readFile(String arg0) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void writeFile(String arg0, AdvancedHashMap arg1) {
		
		AdvancedHashMap<String, Object> inputMap = arg1;
		
		ArrayList<AdvancedHashMap<String, Object>> dailyWeatherData;
		dailyWeatherData = (ArrayList<AdvancedHashMap<String, Object>>) inputMap.get("WeatherDaily");
		
		try {
			FileOutputStream fstream = new FileOutputStream(arg0);
			
			DataOutputStream out = new DataOutputStream(fstream);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			
			String wsName = (String) inputMap.getOr("wsta_name", "<name of weather station>"); 
			AdvancedHashMap<String, Object> daily = dailyWeatherData.get(0);
			String date  = (String) daily.getOr("w_date", "19010101");
			String year  = String.copyValueOf(date.toCharArray(), 0, 4);
			String month = String.copyValueOf(date.toCharArray(), 4, 2);
			String day   = String.copyValueOf(date.toCharArray(), 6, 2);
			
			bw.write(wsName + "\n");
			bw.write("      1 : Daily records (1=daily, 2=10-daily and 3=monthly\n");
			bw.write(String.format("%7s : First day of record (1, 11 or 21 for 10-day or 1 for months\n", day));
			bw.write(String.format("%7s : First month of record" + "\n", month));
			bw.write(String.format("%7s : First year of record (1901 if not linked to a specific year)\n\n", year));

			bw.write("  TMin (C)   TMax (C)" + "\n");
			bw.write("========================" + "\n");
			
			for (int i = 0; i < dailyWeatherData.size(); i++){
				daily = dailyWeatherData.get(i);
				String tmin = (String) daily.getOr("tmin", ""); 
				String tmax = (String) daily.getOr("tmax", ""); 
				bw.write(String.format("%10s%10s\n", tmin, tmax));
			}

			out.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("IO error");
		}
		
		System.out.println(dailyWeatherData.size());
		

	}



}
