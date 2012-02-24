import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.agmip.core.types.AdvancedHashMap;
import org.junit.Before;
import org.junit.Test;
import org.agmip.util.*;
import java.io.IOException;


public class TestSentinelLoader {

	SentinelFileLoader sentinelFileLoader;
	
	@Before
	public void setUp() throws Exception {
		sentinelFileLoader = new SentinelFileLoader();
	}

	@Test
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
