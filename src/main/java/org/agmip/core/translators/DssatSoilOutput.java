package org.agmip.core.translators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;

/**
 * DSSAT Soil Data I/O API Class
 * 
 * @author Meng Zhang
 * @version 1.0
 */
public class DssatSoilOutput extends DssatCommonOutput {

    /**
     * DSSAT Soil Data Output method
     * 
     * @param arg0  file name(?)
     * @param result  data holder object
     */
    @Override
    public void writeFile(String arg0, AdvancedHashMap result) {

        // Initial variables
        JSONAdapter adapter = new JSONAdapter();        // JSON Adapter
        AdvancedHashMap<String, Object> record;         // Data holder for layer data
        AdvancedHashMap<String, Object> data;           // Data holder for one record of soil data
        BufferedWriter br;                              // output object
        StringBuilder sbLyrP2 = new StringBuilder();    // output string for second part of layer data
        boolean p2Flg = false;
        String[] p2Ids = {"slpx", "slpt", "slpo", "slca", "slal", "slfe", "slmn", "slbs", "slpa", "slpb", "slke", "slmg", "slna", "slsu", "slec", "slca"};
//        File file;
//        FileWriter output;

        try {

            // Set default value for missing data
            setDefVal();

            // Initial BufferedWriter
            String fileName = result.getOr("soil_id", "").toString();
            if (fileName.equals("")) {
                fileName = "soil.SOL";
            } else {
                fileName = fileName.substring(0, 2) + ".SOL";
            }
            br = new BufferedWriter(new FileWriter(new File(fileName)));

            // Output Soil File
            // Titel Section
            br.write("*SOILS\r\n\r\n");

            // TODO Add Loop for multiple record of data
//            ArrayList siteRecords = (ArrayList) result.getOr("soil", new ArrayList());
//            for (int i = 0; i < siteRecords.size(); i++) {
            {
                // Get Data from input holder
//                data = adapter.exportRecord((Map) siteRecords.get(i));
                data = result;

                // Site Info Section
                br.write(String.format("*%1$-10s  %2$-11s %3$-5s %4$5s %5$-50s\r\n",
                        data.getOr("soil_id", defValC).toString(),
                        data.getOr("sl_source", defValC).toString(),
                        data.getOr("sltx", defValC).toString(),
                        formatNumStr(5, data.getOr("sldp", defValR).toString()),
                        data.getOr("classification", defValC).toString()));
                br.write("@SITE        COUNTRY          LAT     LONG SCS FAMILY\r\n");
                br.write(String.format(" %1$-23s %2$8s %3$8s %4$-50s\r\n",
                        data.getOr("site", defValC).toString(),
                        formatNumStr(8, data.getOr("soillat", defValR).toString()),
                        formatNumStr(8, data.getOr("soillong", defValR).toString()),
                        data.getOr("name", defValC).toString()));
                br.write("@ SCOM  SALB  SLU1  SLDR  SLRO  SLNF  SLPF  SMHB  SMPX  SMKE\r\n");
                br.write(String.format(" %1$-5s %2$5s %3$5s %4$5s %5$5s %6$5s %7$5s %8$-5s %9$-5s %10$-5s\r\n",
                        data.getOr("scom", defValC).toString(),
                        formatNumStr(5, data.getOr("salb", defValR).toString()),
                        formatNumStr(5, data.getOr("slu1", defValR).toString()),
                        formatNumStr(5, data.getOr("sldr", defValR).toString()),
                        formatNumStr(5, data.getOr("slro", defValR).toString()),
                        formatNumStr(5, data.getOr("slnf", defValR).toString()),
                        formatNumStr(5, data.getOr("slpf", defValR).toString()),
                        data.getOr("smhb", defValC).toString(),
                        data.getOr("smpx", defValC).toString(),
                        data.getOr("smke", defValC).toString()));

                // Soil Layer data section
                ArrayList soilRecords = (ArrayList) data.getOr("SoilLayer", new ArrayList());

                // part one
                br.write("@  SLB  SLMH  SLLL  SDUL  SSAT  SRGF  SSKS  SBDM  SLOC  SLCL  SLSI  SLCF  SLNI  SLHW  SLHB  SCEC  SADC\r\n");
                // part two
                for (int i = 0; i < p2Ids.length; i++) {
                    if (!data.getOr(p2Ids[i], "").toString().equals("")) {
                        p2Flg = true;
                        break;
                    }
                }
                if (p2Flg) {
                    sbLyrP2.append("@  SLB  SLPX  SLPT  SLPO CACO3  SLAL  SLFE  SLMN  SLBS  SLPA  SLPB  SLKE  SLMG  SLNA  SLSU  SLEC  SLCA\r\n");
                }

                // Loop for laryer data
                for (int j = 0; j < soilRecords.size(); j++) {

                    record = adapter.exportRecord((Map) soilRecords.get(j));
                    // part one
                    br.write(String.format(" %1$5s %2$-5s %3$5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$5s %12$5s %13$5s %14$5s %15$5s %16$5s %17$5s\r\n",
                            formatNumStr(5, record.getOr("sllb", defValR).toString()), //TODO Do I need to check if sllb is a valid value
                            record.getOr("slmh", defValC).toString(),
                            formatNumStr(5, record.getOr("slll", defValR).toString()),
                            formatNumStr(5, record.getOr("sldul", defValR).toString()),
                            formatNumStr(5, record.getOr("slsat", defValR).toString()),
                            formatNumStr(5, record.getOr("slrgf", defValR).toString()),
                            formatNumStr(5, record.getOr("sksat", defValR).toString()),
                            formatNumStr(5, record.getOr("sbdm", defValR).toString()),
                            formatNumStr(5, record.getOr("sloc", defValR).toString()),
                            formatNumStr(5, record.getOr("clay", defValR).toString()),
                            formatNumStr(5, record.getOr("silt", defValR).toString()),
                            formatNumStr(5, record.getOr("slcf", defValR).toString()),
                            formatNumStr(5, record.getOr("slni", defValR).toString()),
                            formatNumStr(5, record.getOr("slphw", defValR).toString()),
                            formatNumStr(5, record.getOr("slphb", defValR).toString()),
                            formatNumStr(5, record.getOr("slcec", defValR).toString()),
                            formatNumStr(5, record.getOr("sadc", defValR).toString())));

                    // part two
                    if (p2Flg) {
                        sbLyrP2.append(String.format(" %1$5s %2$5s %3$5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$5s %12$5s %13$5s %14$5s %15$5s %16$5s %17$5s\r\n",
                                formatNumStr(5, record.getOr("sllb", defValR).toString()),
                                formatNumStr(5, record.getOr("slpx", defValR).toString()),
                                formatNumStr(5, record.getOr("slpt", defValR).toString()),
                                formatNumStr(5, record.getOr("slpo", defValR).toString()),
                                formatNumStr(5, record.getOr("slca", defValR).toString()),
                                formatNumStr(5, record.getOr("slal", defValR).toString()),
                                formatNumStr(5, record.getOr("slfe", defValR).toString()),
                                formatNumStr(5, record.getOr("slmn", defValR).toString()),
                                formatNumStr(5, record.getOr("slbs", defValR).toString()),
                                formatNumStr(5, record.getOr("slpa", defValR).toString()),
                                formatNumStr(5, record.getOr("slpb", defValR).toString()),
                                formatNumStr(5, record.getOr("slke", defValR).toString()),
                                formatNumStr(5, record.getOr("slmg", defValR).toString()),
                                formatNumStr(5, record.getOr("slna", defValR).toString()),
                                formatNumStr(5, record.getOr("slsu", defValR).toString()),
                                formatNumStr(5, record.getOr("slec", defValR).toString()),
                                formatNumStr(5, record.getOr("slca", defValR).toString())));
                    }
                }

                // Add part two
                if (p2Flg) {
                    br.write(sbLyrP2.toString() + "\r\n");
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

        // defValD = ""; No need to set default value for Date type in soil file
        defValR = "-99";
        defValC = "";
        defValI = "-99";
    }
}
