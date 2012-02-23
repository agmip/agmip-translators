package org.agmip.core.translators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.agmip.core.types.AdvancedHashMap;
import org.agmip.core.types.weather.WeatherData;
import org.agmip.core.types.weather.WeatherFile;
import org.agmip.util.JSONAdapter;


public class InfocropWeather implements WeatherFile {

	public static String jsonExample = "{\"observed\": {\"hwah\": 2929.0, \"adap\": 76.0, \"cwam\": 5532.0, \"mdap\": 129.0}, \"management\": {\"plrs\": 61.0, \"plpoe\": 7.2, \"hacom\": \"H\", \"icpcr\": \"MZ\", \"ninumm\": 3.0, \"CropMgmt\": [{\"fedep\": 10.0, \"feamn\": 27.0, \"feacd\": \"AP001\", \"feamk\": 0.0, \"fecd\": \"FE001\", \"feamp\": 0.0, \"fdate\": \"4/7/82\"}, {\"fedep\": 10.0, \"feamn\": 35.0, \"feacd\": \"AP001\", \"feamk\": 0.0, \"fecd\": \"FE001\", \"feamp\": 0.0, \"fdate\": \"4/12/82\"}, {\"fedep\": 10.0, \"feamn\": 54.0, \"feacd\": \"AP001\", \"feamk\": 0.0, \"fecd\": \"FE001\", \"feamp\": 0.0, \"fdate\": \"5/17/82\"}], \"fen_tot\": 116.0}, \"meta\": {\"cul_id\": \"IB0035\", \"people\": \" BENNET,J.M. ZUR,B. HAMMOND,L.C. JONES,J.W.\", \"fl_long\": -82.37, \"cr\": \"MZ\", \"institutes\": \"University of Florida\", \"main_factor\": \"NxW\", \"num_years\": 1.0, \"wsta_long\": -82.37, \"wsta_source\": \"Measured\", \"data_source\": \"DSSAT\", \"exname\": \"UFGA8201.MZX\", \"flele\": 40.0, \"fertilizer\": \"P\", \"wsta_insi\": \"UFGA\", \"wsta_dist\": 0.0, \"fl_loc_1\": \"USA\", \"fl_loc_2\": \"FLA\", \"fl_loc_3\": \"Gainesville\", \"soil_id\": \"IBMZ910014\", \"hdate\": \"7/4/82\", \"fl_name\": \"Irrigation Park\", \"pdate\": \"2/25/82\", \"wsta_name\": \"Gainesville, FL\", \"exp_factors\": \"N; W\", \"id_field\": \"UFGA0002\", \"sltx\": \"LS\", \"eid\": 1.0, \"local_name\": \"Gainesville, FL\", \"irrig\": \"N\", \"distribution\": \"None\", \"wsta_lat\": 29.63, \"fl_lat\": 29.63, \"sl_source\": \"SCS\"}, \"weather\": {\"tav\": -82.37, \"wndht\": 3.0, \"wsta_name\": \"Gainesville, FL\", \"refht\": 2.0, \"elev\": 10.0, \"wsta_insi\": \"UFGA\", \"WeatherDaily\": [{\"w_date\": \"1/1/82\", \"srad\": 5.9, \"tmax\": 24.4, \"tmin\": 15.6, \"rain\": 19.0}, {\"w_date\": \"1/2/82\", \"srad\": 7.0, \"tmax\": 22.2, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"1/3/82\", \"srad\": 9.0, \"tmax\": 27.8, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"1/4/82\", \"srad\": 3.1, \"tmax\": 26.1, \"tmin\": 15.0, \"rain\": 9.4}, {\"w_date\": \"1/5/82\", \"srad\": 12.9, \"tmax\": 17.2, \"tmin\": 2.2, \"rain\": 0.0}, {\"w_date\": \"1/6/82\", \"srad\": 11.4, \"tmax\": 25.0, \"tmin\": 4.4, \"rain\": 0.0}, {\"w_date\": \"1/7/82\", \"srad\": 8.5, \"tmax\": 26.7, \"tmin\": 11.7, \"rain\": 0.0}, {\"w_date\": \"1/8/82\", \"srad\": 3.0, \"tmax\": 22.8, \"tmin\": 14.4, \"rain\": 5.3}], \"tamp\": 29.63}, \"soil\": {\"soil_id\": \"IBMZ910014\", \"sl_system\": \"SCS\", \"soillong\": -82.37, \"name\": \"Millhopper Fine Sand\", \"SoilLayer\": [{\"silt\": 11.8, \"sldul\": 0.086, \"slll\": 0.02, \"clay\": 0.9, \"slbdm\": 7.4, \"slcf\": 2.0, \"sloc\": 5.3, \"slphw\": 1.36, \"slsat\": 0.23, \"sllb\": 5.0}, {\"silt\": 11.8, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 0.9, \"slbdm\": 7.4, \"slcf\": 1.0, \"sloc\": 5.4, \"slphw\": 1.4, \"slsat\": 0.23, \"sllb\": 15.0}, {\"silt\": 6.4, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 4.6, \"slbdm\": 15.8, \"slcf\": 1.0, \"sloc\": 5.7, \"slphw\": 1.46, \"slsat\": 0.23, \"sllb\": 30.0}, {\"silt\": 5.4, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 5.8, \"slbdm\": 28.0, \"slcf\": 0.5, \"sloc\": 5.8, \"slphw\": 1.46, \"slsat\": 0.23, \"sllb\": 45.0}, {\"silt\": 5.4, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 5.8, \"slbdm\": 28.0, \"slcf\": 0.5, \"sloc\": 5.8, \"slphw\": 1.47, \"slsat\": 0.23, \"sllb\": 60.0}, {\"silt\": 4.2, \"sldul\": 0.076, \"slll\": 0.021, \"clay\": 9.6, \"slbdm\": 27.6, \"slcf\": 0.1, \"sloc\": 5.9, \"slphw\": 1.43, \"slsat\": 0.23, \"sllb\": 90.0}, {\"silt\": 4.2, \"sldul\": 0.076, \"slll\": 0.02, \"clay\": 9.6, \"slbdm\": 17.5, \"slcf\": 0.1, \"sloc\": 5.9, \"slphw\": 1.48, \"slsat\": 0.23, \"sllb\": 120.0}, {\"silt\": 3.6, \"sldul\": 0.13, \"slll\": 0.027, \"clay\": 8.3, \"slbdm\": 0.3, \"slcf\": 0.04, \"sloc\": 5.9, \"slphw\": 1.57, \"slsat\": 0.23, \"sllb\": 150.0}, {\"silt\": 3.6, \"sldul\": 0.258, \"slll\": 0.07, \"clay\": 8.3, \"slbdm\": 0.1, \"slcf\": 0.24, \"sloc\": 5.9, \"slphw\": 1.79, \"slsat\": 0.36, \"sllb\": 180.0}], \"sldr\": 0.65, \"salb\": 0.18, \"slnf\": 1.0, \"soillat\": 29.63, \"classifcation\": \"LS\"}}";
	  
	
	public void readFile(String arg0) {
		// TODO Auto-generated method stub

	}

	
	public void writeFile(String arg0,WeatherData weatherData) {
			 JSONAdapter adapter = new JSONAdapter();

		    File file;
		    
		    FileWriter output;
		    boolean newYearStart=true;
		    BufferedWriter br;
		    String stationName,latitude,longitude,altitude;
		    int dayCount=0;
		    float solarRadiance;
		    Date recordDt =new Date();
		    GregorianCalendar cal=new GregorianCalendar();
		    int year=0;
		    AdvancedHashMap<String, Object> record;
		    AdvancedHashMap<String, Object> data ;
		    SimpleDateFormat sd =new SimpleDateFormat("mm/dd/yy");
		    try {
		//		br.write("");
		    data= adapter.fromJSON(jsonExample);
		    stationName=data.getOr("wsta_name", "notGiven").toString();
		    latitude=data.getOr("wsta_lat","0.00").toString();
		    longitude=data.getOr("wsta_long","0.00").toString();
		    altitude=data.getOr("elev", "0.00").toString();
		    
		    ArrayList weatherRecords = (ArrayList) data.getOr("WeatherDaily", new ArrayList());
		    
		    
		    for(int i=0; i<weatherRecords.size();i++){
		    	record=adapter.exportRecord((Map) weatherRecords.get(0));
		    	solarRadiance= new Float(record.getOr("srad", "0.00").toString());
		    	solarRadiance=solarRadiance*1000;
		    	br=new BufferedWriter(new FileWriter(new File("a.tmp")));
		    	if(newYearStart)
		    	{
		    		altitude=record.getOr("elev", "0.00").toString();
		    		recordDt=sd.parse(record.getOr("w_date","1/1/11").toString());
		    		cal.setTime(recordDt);
		    		year=cal.get(Calendar.YEAR);
		    		newYearStart=false;
		    		file=new File("D:\\YashWorkspace\\"+stationName.substring(0, 4).toUpperCase()+"1."+new String(Integer.toString(year)).substring(1, 4));
		    		file.createNewFile();
		    		output=new FileWriter(file);
		    		br=new BufferedWriter(output);
		    		br.write("*----------------------------------------------------------------------*\n");
			    	br.write("* Station name   :"+stationName+"\n");
			    	br.write("* Author   :\n");
			    	br.write("* Comments   :\n");
			    	br.write("* Creation date   : "+recordDt.toString()+"\n");
			    	br.write("* * Longitude   :"+longitude+"    Latitude: "+latitude+"  Altitude: "+altitude+"\n"); 
			    	br.write("*----------------------------------------------------------------------*\n");
			    	br.write("* Column           Daily Value            Units\n");
			    	br.write("*  1               Station number\n");
			    	br.write("*  2               Year\n");
			    	br.write("*  3               Day\n");
			    	br.write("*  4               Irradiance             KJ m-2\n");
			    	br.write("*  5               Min Temperature        oC\n");
			    	br.write("		    	*  6               Max Temperature        oC\n");
			    	br.write("*  7               Early Morning VP       kPa\n");
			    	br.write("*  8               Mean Wind Speed        m s-1\n");
			    	br.write("*  9               Precipitation          mm d-1\n");
			    	br.write("*\n");
			    	br.write("*----------------------------------------------------------------------*\n");
			    	br.write(latitude+"   "+longitude+" "+altitude+" "+"0  0\n");
		    	}
		    		br. write("1  "+year+"  "+cal.get(Calendar.DAY_OF_YEAR)+"  "+
		    					solarRadiance+"  "+record.getOr("tmin","")+"  "+
		    					record.getOr("tmax","")+"  "+record.getOr("vprs","")+"  "+
		    					record.getOr("wind","")+"  "+record.getOr("rain","")+"\n");
		    		dayCount++;
		    		if(cal.isLeapYear(year)){
		    			if(dayCount==366)
		    				newYearStart=true;
		    		}else
		    			if(dayCount==365)
		    				newYearStart=true;
		    
		    		
		    }
//		    System.out.println(data.getOr("wsta_lon", new Double(-99.99)));
//		    System.out.println(data.getOr("wsta_long", new Double(-99.99)));
//		    System.out.println((adapter.exportRecord((Map) weatherRecords.get(0))).getOr("w_dae", new Date()));
//		    System.out.println((adapter.exportRecord((Map) weatherRecords.get(0))).getOr("w_date", new Date()));
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    catch(ParseException pe){
		    	pe.printStackTrace();
		    }
		    }

	@Override
	public void readFile(String arg0, WeatherData arg1) {
		// TODO Auto-generated method stub
		
	}

	
	
	public static void main(String args[]){
		//InfocropWeather ic=new InfocropWeather();
		//ic.writeFile("", null);
		System.out.println("123");
	}
};
