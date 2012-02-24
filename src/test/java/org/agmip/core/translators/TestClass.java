package org.agmip.core.translators;

import java.io.IOException;

import org.agmip.core.types.AdvancedHashMap;
import org.agmip.core.types.weather.WeatherData;
import org.agmip.util.JSONAdapter;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			SentinelFileLoader sfl=new SentinelFileLoader();
		AdvancedHashMap<String, Object> result;
		String json;
JSONAdapter j = new JSONAdapter();
AgMIPFileLoader agMIPFileLoader=new AgMIPFileLoader();;
		result =agMIPFileLoader.readFile("D:\\Bhai\\ClimateBaseline\\BDDH0XXX.AgMIP");
		System.out.println(result.size());
		InfocropWeather inc= new InfocropWeather();
			
			json=j.toJSON(result);
	//		System.out.println(json);
			
			inc.writeFile(json, result);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
