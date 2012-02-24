
package org.agmip.core.translators;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;


public class TestSentinelLoader {

	SentinelFileLoader sentinelFileLoader;
	
	
	public void setUp() throws Exception {
		sentinelFileLoader = new SentinelFileLoader();
	}

	
	public void test() throws IOException, Exception{
		JSONAdapter j = new JSONAdapter();
		
		ArrayList<AdvancedHashMap<String, Object>> result =
				sentinelFileLoader.readFile("D:\\UserData\\projecten\\AGMIP\\Sentinel\\SampleData_Silver.csv");
		System.out.println(result.size());
		System.out.println(j.toJSON(result.get(0)));
		
		AdvancedHashMap<String, Object> adate = new AdvancedHashMap<String, Object>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = dateFormat.parse("2012/03/20");
		System.out.println(dateFormat.format(date));
		
		adate.put("date", date);
		System.out.println(j.toJSON(adate));
	}

}
