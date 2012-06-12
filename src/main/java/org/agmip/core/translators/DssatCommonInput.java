package org.agmip.core.translators;

import java.util.Calendar;
import java.util.LinkedHashMap;
import org.agmip.core.types.AdvancedHashMap;
import org.agmip.core.types.TranslatorInput;

/**
 * DSSAT Experiment Data I/O API Class
 *
 * @author Meng Zhang
 * @version 1.0
 */
public abstract class DssatCommonInput implements TranslatorInput {

    /**
     * Translate data str from "yyddd" to "yyyymmdd"
     *
     * @param str date string with format of "yyddd"
     * @return result date string with format of "yyyymmdd"
     */
    protected String translateDateStr(String str) {

        return translateDateStr(str, "0");
    }
    
    /**
     * Translate data str from "yyddd" to "yyyymmdd" plus days you want
     *
     * @param startDate date string with format of "yyydd"
     * @param strDays the number of days need to be added on
     * @return result date string with format of "yyyymmdd"
     */
    protected String translateDateStr(String startDate, String strDays) {

        // Initial Calendar object
        Calendar cal = Calendar.getInstance();
        int days;
        int year;
        if (startDate.length() > 5 || startDate.length() < 4 ) {
            //throw new Exception("");
            return "-99"; //defValD;
        }
        try {
            startDate = String.format("%05d", Integer.parseInt(startDate));
            days = Double.valueOf(strDays).intValue();
            // Set date with input value
            year = Integer.parseInt(startDate.substring(0, 2));
            year += year <= 30 ? 2000 : 1900; // TODO Need confirm that which year is the begining of DSSAT 
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.DAY_OF_YEAR, Integer.parseInt(startDate.substring(2)));
            cal.add(Calendar.DATE, days);
            // translatet to yyddd format
            return String.format("%1$04d%2$02d%3$02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            // if tranlate failed, then use default value for date
            // sbError.append("! Waring: There is a invalid date [").append(startDate).append("]");
            return "-99"; //formatDateStr(defValD);
        }

    }
    
    /**
     * Divide the data in the line into a map
     *
     * @param line The string of line read from data file
     * @param formats The defination of lenght for each data field (String itemName : Integer length)
     * @return the map contains divided data with keys from original string
     */
    protected AdvancedHashMap readLine(String line, LinkedHashMap<String, Integer> formats) {
        
        AdvancedHashMap ret = new AdvancedHashMap();
        int length;
         
        for (String key : formats.keySet()) {
            length = (Integer) formats.get(key);
            if (length <= line.length()) {
                ret.put(key, line.substring(0, length));
                line = line.substring(length);
            } else {
                ret.put(key, line);
                line = "";
            }
        }
        
        return ret;
    }

    /**
     * Get exname with normal format
     *
     * @return exname
     */
    protected String getExName() {

        String ret = "";
        
        return ret;
    }
}
