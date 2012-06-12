package org.agmip.core.translators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;

/**
 * DSSAT Weather Data I/O API Class
 * 
 * @author Meng Zhang
 * @version 1.0
 */
public class DssatWeatherOutput extends DssatCommonOutput {

    /**
     * DSSAT Weather Data Output method
     * 
     * @param arg0  file name(?)
     * @param result  data holder object
     */
    @Override
    public void writeFile(String arg0, AdvancedHashMap result) {

        // Initial variables
        JSONAdapter adapter = new JSONAdapter();    // JSON Adapter
        AdvancedHashMap<String, Object> record;     // Data holder for daily data
        AdvancedHashMap<String, Object> data;       // Data holder for whole weather data
        BufferedWriter br;                   // output object
        HashMap optDaily = new HashMap();           // Define optional daily data fields
        optDaily.put("tdew", "  DEWP");
        optDaily.put("wind", "  WIND");
        optDaily.put("pard", "   PAR");
        Set optDailyIds = optDaily.keySet();
//        File file;
//        FileWriter output;

        try {

            // Set default value for missing data
            setDefVal();

            // Get Data from input holder
            data = result;
            ArrayList weatherRecords = (ArrayList) data.getOr("WeatherDaily", new ArrayList());

            // Initial BufferedWriter
            String fileName = data.getOr("wst_insi", "").toString();
            if (fileName.equals("")) {
                fileName = "a.tmp";
            } else {
                fileName += data.getOr("w_date", "00").toString().substring(0, 2) + "01.WTH";
            }
            br = new BufferedWriter(new FileWriter(new File(fileName)));

            // Output Weather File
            // Titel Section
            br.write(String.format("*WEATHER DATA : %1$-60s\r\n\r\n", data.getOr("wst_name", defValC).toString()));

            // Weather Station Section
            br.write("@ INSI      LAT     LONG  ELEV   TAV   AMP REFHT WNDHT\r\n");
            br.write(String.format("  %1$-4s %2$8s %3$8s %4$5s %5$5s %6$5s %7$5s %8$5s\r\n",
                    data.getOr("wst_insi", defValC).toString(),
                    formatNumStr(8, data.getOr("wst_lat", defValR).toString()),
                    formatNumStr(8, data.getOr("wst_long", defValR).toString()),
                    formatNumStr(5, data.getOr("elev", defValR).toString()),
                    formatNumStr(5, data.getOr("tav", defValR).toString()),
                    formatNumStr(5, data.getOr("tamp", defValR).toString()),
                    formatNumStr(5, data.getOr("refht", defValR).toString()),
                    formatNumStr(5, data.getOr("wndht", defValR).toString())));

            // Daily weather data section
            // Fixed Title
            br.write("@DATE  SRAD  TMAX  TMIN  RAIN");

            // Optional Title
            for (Object optDailyId : optDailyIds) {
                // check which optional data is exist, if not, remove from map
                if (!data.getOr(optDailyId.toString(), "").toString().equals("")) {
                    br.write(optDaily.get(optDailyId).toString());
                } else {
                    optDaily.put(optDailyId.toString(), null);
                }
            }

            br.write("\r\n");

            for (int i = 0; i < weatherRecords.size(); i++) {

                record = adapter.exportRecord((Map) weatherRecords.get(i));
                // if date is missing, jump the record
                if (!record.getOr("w_date", "").toString().equals("")) {
                    // Fixed data part
                    br.write(String.format("%1$5s %2$5s %3$5s %4$5s %5$5s",
                            formatDateStr(record.getOr("w_date", defValD).toString()),
                            formatNumStr(5, record.getOr("srad", defValR).toString()),
                            formatNumStr(5, record.getOr("tmax", defValR).toString()),
                            formatNumStr(5, record.getOr("tmin", defValR).toString()),
                            formatNumStr(5, record.getOr("rain", defValR).toString())));

                    // Optional data part
                    for (Object optDailyId : optDailyIds) {
                        if (optDaily.get(optDailyId) != null) {
                            br.write(String.format(" %1$5s", formatNumStr(5, record.getOr(optDailyId.toString(), defValR).toString())));
                        }
                    }
                    br.write("\r\n");
                } else {
                    // TODO Throw exception here
                    System.out.println("A daily record has the missing date in it.");
                }
            }

            // Output finish
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Set default value for missing data
     * 
     */
    private void setDefVal() {

        // defValD = ""; No need to set default value for Date type in weather file
        defValR = "-99";
        defValC = "";
        defValI = "-99";
    }
}
