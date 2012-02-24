package org.agmip.core.translators;
import java.io.IOException;

import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;


public class AgMIPFileLoaderTest {

	AgMIPFileLoader agMIPFileLoader;
	
	
	public void setUp() throws Exception {
		agMIPFileLoader = new AgMIPFileLoader();
	}
	
	public void test() throws IOException, Exception{
		JSONAdapter j = new JSONAdapter();
		
		AdvancedHashMap<String, Object> result =
				agMIPFileLoader.readFile("D:\\Bhai\\ClimateBaseline\\INJA0XXX.AgMIP");
		System.out.println(result.size());
		System.out.println(j.toJSON(result));
	}

}
