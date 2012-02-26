import org.agmip.core.translators.AgMIPFileLoader;
import org.agmip.core.types.AdvancedHashMap;
import org.junit.BeforeClass;
import org.junit.Test;

public class AquaCropConverterTest {
	
	static AgMIPFileLoader agMIPFileLoader = new AgMIPFileLoader();
	static AquaCropConverter aquaCropConverter = new AquaCropConverter();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		agMIPFileLoader = new AgMIPFileLoader();
		aquaCropConverter = new AquaCropConverter();
	}

	@Test
	public void test() {		
	//	AdvancedHashMap<String,Object> result =
		//		agMIPFileLoader.readFile("D:\\UserData\\projecten\\AGMIP\\Sentinel\\BDJE0XXX.AgMIP");
		
		
		aquaCropConverter.writeFile("D:\\UserData\\projecten\\AGMIP\\Sentinel\\BDJE0XXX_AquaCrop.tmp", 
		           agMIPFileLoader.readFile("D:\\UserData\\projecten\\AGMIP\\Sentinel\\BDJE0XXX.AgMIP"));
		
	}

}
