package org.agmip.core.translators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;

/**
 * DSSAT Experiment Data I/O API Class
 * 
 * @author Meng Zhang
 * @version 1.0
 */
public class DssatXFileOutput extends DssatCommonOutput {

    // Define necessary id for the experiment data
    private static String[] necessaryData = {"pdate", "plpop,plpoe", "plrs", "cr", "cul_id", "wst_insi", "soil_id"};

    /**
     * DSSAT Experiment Data Output method
     * 
     * @param arg0  file name(?)
     * @param result  data holder object
     */
    @Override
    public void writeFile(String arg0, AdvancedHashMap result) {

        // Initial variables
        JSONAdapter adapter = new JSONAdapter();    // JSON Adapter
        AdvancedHashMap<String, Object> data;       // Data holder for section data
        BufferedWriter br;                          // output object
        StringBuilder sbData = new StringBuilder();     // construct the data info in the output
        StringBuilder eventPart2;                   // output string for second part of event data
        ArrayList eventRecords;                     // Arraylist for event data holder
        ArrayList secRecords;                       // Arraylist for section data holder
        int trmnNum;                            // total numbers of treatment in the data holder
        int cuNum;                              // total numbers of cultivars in the data holder
        int flNum;                              // total numbers of fields in the data holder
        int saNum;                              // total numbers of soil analysis in the data holder
        int icNum;                              // total numbers of initial conditions in the data holder
        int mpNum;                              // total numbers of plaintings in the data holder
        int miNum;                              // total numbers of irrigations in the data holder
        int mfNum;                              // total numbers of fertilizers in the data holder
        int mrNum;                              // total numbers of residues in the data holder
        int mcNum;                              // total numbers of chemical in the data holder
        int mtNum;                              // total numbers of tillage in the data holder
        int meNum;                              // total numbers of enveronment modification in the data holder
        int mhNum;                              // total numbers of harvest in the data holder
        int smNum;                              // total numbers of simulation controll record
        ArrayList<String> cuArr = new ArrayList<String>();   // array for cultivars record
        ArrayList<String> flArr = new ArrayList<String>();   // array for fields record
        ArrayList<String> saArr = new ArrayList<String>();   // array for soil analysis record
        ArrayList<String> icArr = new ArrayList<String>();   // array for initial conditions record
        ArrayList<String> mpArr = new ArrayList<String>();   // array for plaintings record
        ArrayList<String> miArr = new ArrayList<String>();   // array for irrigations record
        ArrayList<String> mfArr = new ArrayList<String>();   // array for fertilizers record
        ArrayList<String> mrArr = new ArrayList<String>();   // array for residues record
        ArrayList<String> mcArr = new ArrayList<String>();   // array for chemical record
        ArrayList<String> mtArr = new ArrayList<String>();   // array for tillage record
        ArrayList<String> meArr = new ArrayList<String>();   // array for enveronment modification record
        ArrayList<String> mhArr = new ArrayList<String>();   // array for harvest record
        String exName;
//        File file;
//        FileWriter output;

        try {

            // Initial missing data check for necessary fields
            for (int i = 0; i < necessaryData.length; i++) {
                String[] strs = necessaryData[i].split(",");
                for (int j = 0; j < strs.length; j++) {
                    if (!result.getOr(strs[j], "").toString().equals("")) {
                        strs = null;
                        break;
                    }
                }
                if (strs != null) {
                    //throw new Exception("Incompleted record because missing data : [" + necessaryData[i] + "]");
                    //System.out.println("Incompleted record because missing data : [" + necessaryData[i] + "]");
                    sbError.append("! Warning: Incompleted record because missing data : [" + necessaryData[i] + "]");
                    //return;
                }
            }

            // Set default value for missing data
            setDefVal(result);

            // Initial BufferedWriter
            exName = getExName(result);
            String fileName = exName;
            if (fileName.equals("")) {
                fileName = "a.tmp";
            } else {
                fileName = fileName.substring(0, fileName.length() - 2) + "." + fileName.substring(fileName.length() - 2) + "X";
            }
            br = new BufferedWriter(new FileWriter(new File(fileName)));
            data = result;

            // Output XFile
            // EXP.DETAILS Section
            sbData.append(String.format("*EXP.DETAILS: %1$-10s %2$s\r\n\r\n", exName, result.getOr("local_name", defValC).toString()));

            // GENERAL Section
            sbData.append("*GENERAL\r\n");
            // People
            sbData.append(String.format("@PEOPLE\r\n %1$s\r\n", result.getOr("people", defValC).toString()));
            // Address
            //sbData.append(String.format("@ADDRESS\r\n %1$-75s\r\n", result.getOr("address", defValC).toString())); //TODO
            sbData.append(String.format("@ADDRESS\r\n %3$s, %2$s, %1$s\r\n",
                    result.getOr("fl_loc_1", defValC).toString(),
                    result.getOr("fl_loc_2", defValC).toString(),
                    result.getOr("fl_loc_3", defValC).toString()));
            // Site
            sbData.append(String.format("@SITE\r\n %1$s\r\n", result.getOr("site", defValC).toString()));
            // Plot Info
            if (result.containsKey("plot_info")) {
                sbData.append("@ PAREA  PRNO  PLEN  PLDR  PLSP  PLAY HAREA  HRNO  HLEN  HARM.........\r\n");
                sbData.append(String.format(" %1$6s %2$5s %3$5s %4$5s %5$5s %6$-5s %7$5s %8$5s %9$5s %10$-15s\r\n",
                        formatNumStr(6, result.getOr("parea", defValR).toString()),
                        formatNumStr(5, result.getOr("prno", defValI).toString()),
                        formatNumStr(5, result.getOr("plen", defValR).toString()),
                        formatNumStr(5, result.getOr("pldr", defValI).toString()),
                        formatNumStr(5, result.getOr("plsp", defValI).toString()),
                        result.getOr("play", defValC).toString(),
                        formatNumStr(5, result.getOr("pltha", defValR).toString()),
                        formatNumStr(5, result.getOr("hrno", defValI).toString()),
                        formatNumStr(5, result.getOr("hlen", defValR).toString()),
                        result.getOr("plthm", defValC).toString()));
            }
            // Notes
            if (result.containsKey("notes")) {
                String notes = result.getOr("notes", defValC).toString();

                // If notes contain newline code, then write directly
                if (notes.indexOf("\r") >= 0 || notes.indexOf("\n") >= 0) {
                    sbData.append(String.format(" %1$-75s\r\n", result.getOr("notes", defValC).toString()));
                } // Otherwise, add newline for every 75-bits charactors
                else {
                    while (notes.length() > 75) {
                        sbData.append(" " + notes.substring(0, 75) + "\r\n");
                        notes = notes.substring(75);
                    }
                    sbData.append(" " + notes + "\r\n");
                }
            }
            sbData.append("\r\n");

            // TREATMENT Section
            sbData.append("*TREATMENTS                        -------------FACTOR LEVELS------------\r\n");
            sbData.append("@N R O C TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM\r\n");

            // Get index value for each section // TODO will be add to loop when multiple record come out
            trmnNum = 1;
            cuNum = getIdxVal(data.getOr("cul_id", "").toString(), cuArr);
            flNum = getIdxVal(data.getOr("id_field", "").toString(), flArr);
            saNum = getIdxVal(data.getOr("sadat", "").toString(), saArr);   //TODO Not sure for using this field
            icNum = getIdxVal(data.getOr("icpcr", "").toString(), icArr);
            mpNum = getIdxVal(data.getOr("pdate", "").toString(), mpArr);
            miNum = getIdxVal(data.getOr("ireff", "").toString(), miArr);   //TODO Not sure for using this field
            mfNum = getIdxVal(data.getOr("fdate", "").toString(), mfArr);   //TODO Not sure for using this field
            mrNum = getIdxVal(data.getOr("omdat", "").toString(), mrArr);   //TODO Not sure for using this field
            mcNum = getIdxVal(data.getOr("cdate", "").toString(), mcArr);   //TODO Not sure for using this field
            mtNum = getIdxVal(data.getOr("tdate", "").toString(), mtArr);   //TODO Not sure for using this field
            meNum = getIdxVal(data.getOr("emday", "").toString(), meArr);   //TODO Not sure for using this field
            mhNum = getIdxVal(data.getOr("hdate", "").toString(), mhArr);   //TODO Not sure for using this field
            smNum = 1;


//            secRecords = (ArrayList) result.getOr("treatments", new ArrayList());
//            for (int i = 0; i < secRecords.size(); i++)
            {
//                data = adapter.exportRecord((Map) secRecords.get(i));

                // treatment
                sbData.append(String.format("%1$2s %2$1s %3$1s %4$1s %5$-25s %6$2s %7$2s %8$2s %9$2s %10$2s %11$2s %12$2s %13$2s %14$2s %15$2s %16$2s %17$2s %18$2s\r\n",
                        trmnNum,
                        1, //data.getOr("sq", defValI).toString(),
                        0, //data.getOr("op", defValI).toString(),
                        0, //data.getOr("co", defValI).toString(),
                        data.getOr("tr_name", defValC).toString(),
                        cuNum, //data.getOr("cu", defValI).toString(), 
                        flNum, //data.getOr("fl", defValI).toString(), 
                        saNum, //data.getOr("sa", defValI).toString(),
                        icNum, //data.getOr("ic", defValI).toString(),
                        mpNum, //data.getOr("pl", defValI).toString(),
                        miNum, //data.getOr("ir", defValI).toString(),
                        mfNum, //data.getOr("fe", defValI).toString(),
                        mrNum, //data.getOr("om", defValI).toString(),
                        mcNum, //data.getOr("ch", defValI).toString(),
                        mtNum, //data.getOr("ti", defValI).toString(),
                        meNum, //data.getOr("em", defValI).toString(),
                        mhNum, //data.getOr("ha", defValI).toString(),
                        smNum)); // 1
                trmnNum++;
            }
            sbData.append("\r\n");

            // Check if Necessary Section exists
            if (cuArr.isEmpty()) {
                //throw new Exception ("");
                //System.out.println("Trhee is no cultivar data in the experiment.");
                sbError.append("! Warning: Trhee is no cultivar data in the experiment.");
            } else if (flArr.isEmpty()) {
                //throw new Exception ("");
                //System.out.println("Trhee is no field data in the experiment.");
                sbError.append("! Warning: Trhee is no field data in the experiment.");
            } else if (mpArr.isEmpty()) {
                //throw new Exception ("");
                //System.out.println("Trhee is no plainting data in the experiment.");
                sbError.append("! Warning: Trhee is no plainting data in the experiment.");
            }

            // CULTIVARS Section
            for (int idx = 0; idx < cuArr.size(); idx++) {
                //if (!cuArr.isEmpty()) {
                sbData.append("*CULTIVARS\r\n");
                sbData.append("@C CR INGENO CNAME\r\n");

                //            secRecords = (ArrayList) result.getOr("cultivars", new ArrayList());
                //            for (int i = 0; i < secRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) secRecords.get(i));
                    sbData.append(String.format("%1$2s %2$-2s %3$-6s %4$-16s\r\n",
                            idx + 1, //data.getOr("ge", defValI).toString(),
                            data.getOr("cr", defValC).toString(),
                            data.getOr("cul_id", defValC).toString(),
                            data.getOr("cul_name", defValC).toString()));

                }
            }
            sbData.append("\r\n");

            // FIELDS Section
            for (int idx = 0; idx < flArr.size(); idx++) {
                //if (!flArr.isEmpty()) {
                sbData.append("*FIELDS\r\n");
                sbData.append("@L ID_FIELD WSTA....  FLSA  FLOB  FLDT  FLDD  FLDS  FLST SLTX  SLDP  ID_SOIL    FLNAME\r\n");
                eventPart2 = new StringBuilder();
                eventPart2.append("@L ...........XCRD ...........YCRD .....ELEV .............AREA .SLEN .FLWR .SLAS FLHST FHDUR\r\n");

                //            secRecords = (ArrayList) result.getOr("fields", new ArrayList());
                //            for (int i = 0; i < secRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) secRecords.get(i));
                    sbData.append(String.format("%1$2s %2$-8s %3$-8s %4$-5s %5$5s %6$-5s %7$5s %8$5s %9$-5s %10$-5s %11$5s %12$-10s %13$s\r\n",
                            idx + 1, //data.getOr("fl", defValI).toString(),
                            data.getOr("id_field", defValC).toString(),
                            data.getOr("wst_insi", defValC).toString(),
                            data.getOr("flsl", defValC).toString(),
                            formatNumStr(5, data.getOr("flob", defValR).toString()),
                            data.getOr("fl_drntype", defValC).toString(),
                            formatNumStr(5, data.getOr("fldrd", defValR).toString()),
                            formatNumStr(5, data.getOr("fldrs", defValR).toString()),
                            data.getOr("flst", defValC).toString(),
                            data.getOr("sltx", defValC).toString(),
                            formatNumStr(5, data.getOr("sldp", defValR).toString()),
                            data.getOr("soil_id", defValC).toString(),
                            data.getOr("fl_name", defValC).toString()));

                    eventPart2.append(String.format("%1$2s %2$15s %3$15s %4$9s %5$17s %6$5s %7$5s %8$5s %9$-5s %10$5s\r\n",
                            idx + 1, //data.getOr("fl", defValI).toString(),
                            formatNumStr(15, data.getOr("fl_lat", defValR).toString()),
                            formatNumStr(15, data.getOr("fl_long", defValR).toString()),
                            formatNumStr(9, data.getOr("flele", defValR).toString()),
                            formatNumStr(17, data.getOr("farea", defValR).toString()),
                            formatNumStr(5, "0"), //data.getOr("slen", defValR).toString()), // TODO calculate? keep -99
                            formatNumStr(5, data.getOr("fllwr", defValR).toString()),
                            formatNumStr(5, data.getOr("flsla", defValR).toString()),
                            data.getOr("flhst", defValC).toString(),
                            formatNumStr(5, data.getOr("fhdur", defValR).toString())));
                }
                sbData.append(eventPart2.toString() + "\r\n");
            }

            // SOIL ANALYSIS Section
            for (int idx = 0; idx < saArr.size(); idx++) {
                //if (!saArr.isEmpty()) {
                sbData.append("*SOIL ANALYSIS\r\n");

                //            secRecords = (ArrayList) result.getOr("soil analysis", new ArrayList());
                //            for (int i = 0; i < secRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) secRecords.get(i));

                    sbData.append("@A SADAT  SMHB  SMPX  SMKE  SANAME\r\n");
                    sbData.append(String.format("%1$2s %2$5s %3$-5s %4$-5s %5$-5s  %6$s\r\n",
                            idx + 1, //data.getOr("sa", defValI).toString(),
                            formatDateStr(data.getOr("sadat", defValD).toString()),
                            data.getOr("samhb", defValC).toString(),
                            data.getOr("sampx", defValC).toString(),
                            data.getOr("samke", defValC).toString(),
                            data.getOr("sa_name", defValC).toString()));

                    sbData.append("@A  SABL  SADM  SAOC  SANI SAPHW SAPHB  SAPX  SAKE  SASC\r\n");
                    //                secRecords = (ArrayList) data.getOr("soil analysis levels", new ArrayList());
                    //                for (int j = 0; j < secRecords.size(); j++)
                    {
                        //                    data = adapter.exportRecord((Map) secRecords.get(j));
                        sbData.append(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s\r\n",
                                idx + 1, //data.getOr("sa", defValI).toString(),
                                formatNumStr(5, data.getOr("sabl", defValR).toString()),
                                formatNumStr(5, data.getOr("sabdm", defValR).toString()),
                                formatNumStr(5, data.getOr("saoc", defValR).toString()),
                                formatNumStr(5, data.getOr("sani", defValR).toString()),
                                formatNumStr(5, data.getOr("saphw", defValR).toString()),
                                formatNumStr(5, data.getOr("saphb", defValR).toString()),
                                formatNumStr(5, data.getOr("sapx", defValR).toString()),
                                formatNumStr(5, data.getOr("sake", defValR).toString()),
                                formatNumStr(5, data.getOr("sasc", defValR).toString())));
                    }
                }
                sbData.append("\r\n");
            }

            // INITIAL CONDITIONS Section
            for (int idx = 0; idx < icArr.size(); idx++) {
                //if (!icArr.isEmpty()) {
                sbData.append("*INITIAL CONDITIONS\r\n");

                //            eventRecords = (ArrayList) data.getOr("initial", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append("@C   PCR ICDAT  ICRT  ICND  ICRN  ICRE  ICWD ICRES ICREN ICREP ICRIP ICRID ICNAME\r\n");
                    sbData.append(String.format("%1$2s %2$-5s %3$5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$5s %12$5s %13$5s %14$s\r\n",
                            idx + 1, //data.getOr("ic", defValI).toString(),
                            data.getOr("icpcr", defValC).toString(),
                            formatDateStr(data.getOr("icdat", defValD).toString()),
                            formatNumStr(5, data.getOr("icrt", defValR).toString()),
                            formatNumStr(5, data.getOr("icnd", defValR).toString()),
                            formatNumStr(5, data.getOr("icrzno", defValR).toString()),
                            formatNumStr(5, data.getOr("icrze", defValR).toString()),
                            formatNumStr(5, data.getOr("icwt", defValR).toString()),
                            formatNumStr(5, data.getOr("icrag", defValR).toString()),
                            formatNumStr(5, data.getOr("icrn", defValR).toString()),
                            formatNumStr(5, data.getOr("icrp", defValR).toString()),
                            formatNumStr(5, data.getOr("icrip", defValR).toString()),
                            formatNumStr(5, data.getOr("icrdp", defValR).toString()),
                            data.getOr("ic_name", defValC).toString()));

                    sbData.append("@C  ICBL  SH2O  SNH4  SNO3\r\n");
                    //                secRecords = (ArrayList) data.getOr("initial levels", new ArrayList());
                    //                for (int j = 0; j < secRecords.size(); j++)
                    {
                        //                    data = adapter.exportRecord((Map) secRecords.get(j));
                        sbData.append(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s\r\n",
                                idx + 1, //data.getOr("ic", defValI).toString(),
                                formatNumStr(5, data.getOr("icbl", defValR).toString()),
                                formatNumStr(5, data.getOr("ich2o", defValR).toString()),
                                formatNumStr(5, data.getOr("icnh4", defValR).toString()),
                                formatNumStr(5, data.getOr("icno3", defValR).toString())));

                    }
                }
                sbData.append("\r\n");
            }

            // PLANTING DETAILS Section
            for (int idx = 0; idx < mpArr.size(); idx++) {
                //if (!mpArr.isEmpty()) {
                sbData.append("*PLANTING DETAILS\r\n");
                sbData.append("@P PDATE EDATE  PPOP  PPOE  PLME  PLDS  PLRS  PLRD  PLDP  PLWT  PAGE  PENV  PLPH  SPRL                        PLNAME\r\n");

                //            eventRecords = (ArrayList) data.getOr("plainting", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s     %6$-1s     %7$-1s %8$5s %9$5s %10$5s %11$5s %12$5s %13$5s %14$5s %15$5s                        %16$s\r\n",
                            idx + 1, //data.getOr("pl", defValI).toString(),
                            formatDateStr(data.getOr("pdate", defValD).toString()),
                            formatDateStr(data.getOr("pldae", defValD).toString()),
                            formatNumStr(5, data.getOr("plpop", data.getOr("plpoe", defValR)).toString()),
                            formatNumStr(5, data.getOr("plpoe", data.getOr("plpop", defValR)).toString()),
                            data.getOr("plme", defValC).toString(),
                            data.getOr("plds", defValC).toString(),
                            formatNumStr(5, data.getOr("plrs", defValR).toString()),
                            formatNumStr(5, data.getOr("plrd", defValR).toString()),
                            formatNumStr(5, data.getOr("pldp", defValR).toString()),
                            formatNumStr(5, data.getOr("plmwt", defValR).toString()),
                            formatNumStr(5, data.getOr("page", defValR).toString()),
                            formatNumStr(5, data.getOr("penv", defValR).toString()),
                            formatNumStr(5, data.getOr("plph", defValR).toString()),
                            formatNumStr(5, data.getOr("plspl", defValR).toString()),
                            data.getOr("pl_name", defValC).toString()));

                }
                sbData.append("\r\n");
            }

            // IRRIGATION AND WATER MANAGEMENT Section
            for (int idx = 0; idx < miArr.size(); idx++) {
                //if (!miArr.isEmpty()) {
                sbData.append("*IRRIGATION AND WATER MANAGEMENT\r\n");

                //            eventRecords = (ArrayList) data.getOr("irrigation", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append("@I  EFIR  IDEP  ITHR  IEPT  IOFF  IAME  IAMT IRNAME\r\n");
                    sbData.append(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s %6$-5s %7$-5s %8$5s %9$s\r\n",
                            idx + 1, //data.getOr("ir", defValI).toString(),
                            formatNumStr(5, data.getOr("ireff", defValR).toString()),
                            formatNumStr(5, data.getOr("irmdp", defValR).toString()),
                            formatNumStr(5, data.getOr("irthr", defValR).toString()),
                            formatNumStr(5, data.getOr("irept", defValR).toString()),
                            data.getOr("irstg", defValC).toString(),
                            data.getOr("iame", defValC).toString(),
                            formatNumStr(5, data.getOr("iamt", defValR).toString()),
                            data.getOr("ir_name", defValC).toString()));

                    sbData.append("@I IDATE  IROP IRVAL\r\n");
                    //                secRecords = (ArrayList) data.getOr("irrigation levels", new ArrayList());
                    //                for (int j = 0; j < secRecords.size(); j++)
                    {
                        //                    data = adapter.exportRecord((Map) secRecords.get(j));
                        sbData.append(String.format("%1$2s %2$5s %3$-5s %4$5s\r\n",
                                idx + 1, //data.getOr("ir", defValI).toString(),
                                formatDateStr(data.getOr("idate", defValD).toString()),
                                data.getOr("irop", defValC).toString(),
                                formatNumStr(5, data.getOr("irval", defValR).toString())));
                    }
                }
                sbData.append("\r\n");
            }

            // FERTILIZERS (INORGANIC) Section
            for (int idx = 0; idx < mfArr.size(); idx++) {
                //if (!mfArr.isEmpty()) {
                sbData.append("*FERTILIZERS (INORGANIC)\r\n");
                sbData.append("@F FDATE  FMCD  FACD  FDEP  FAMN  FAMP  FAMK  FAMC  FAMO  FOCD FERNAME\r\n");
                //            eventRecords = (ArrayList) data.getOr("fertilizers", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append(String.format("%1$2s %2$5s %3$-5s %4$-5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$-5s %12$s\r\n",
                            idx + 1, //data.getOr("fe", defValI).toString(),
                            formatDateStr(data.getOr("fdate", defValD).toString()),
                            data.getOr("fecd", defValC).toString(),
                            data.getOr("feacd", defValC).toString(),
                            formatNumStr(5, data.getOr("fedep", defValR).toString()),
                            formatNumStr(5, data.getOr("feamn", defValR).toString()),
                            formatNumStr(5, data.getOr("feamp", defValR).toString()),
                            formatNumStr(5, data.getOr("feamk", defValR).toString()),
                            formatNumStr(5, data.getOr("feamc", defValR).toString()),
                            formatNumStr(5, data.getOr("feamo", defValR).toString()),
                            data.getOr("feocd", defValC).toString(),
                            data.getOr("fe_name", defValC).toString()));

                }
                sbData.append("\r\n");
            }

            // RESIDUES AND ORGANIC FERTILIZER Section
            for (int idx = 0; idx < mrArr.size(); idx++) {
                //if (!mrArr.isEmpty()) {
                sbData.append("*RESIDUES AND ORGANIC FERTILIZER\r\n");
                sbData.append("@R RDATE  RCOD  RAMT  RESN  RESP  RESK  RINP  RDEP  RMET RENAME\r\n");

                //            eventRecords = (ArrayList) data.getOr("residues", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append(String.format("%1$2s %2$5s %3$-5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$s\r\n",
                            idx + 1, //data.getOr("om", defValI).toString(),
                            formatDateStr(data.getOr("omdat", defValD).toString()),
                            data.getOr("omcd", defValC).toString(),
                            formatNumStr(5, data.getOr("omamt", defValR).toString()),
                            formatNumStr(5, data.getOr("omnpct", defValR).toString()),
                            formatNumStr(5, data.getOr("omppct", defValR).toString()),
                            formatNumStr(5, data.getOr("omkpct", defValR).toString()),
                            formatNumStr(5, data.getOr("ominp", defValR).toString()),
                            formatNumStr(5, data.getOr("omdep", defValR).toString()),
                            formatNumStr(5, data.getOr("omacd", defValR).toString()),
                            data.getOr("om_name", defValC).toString()));

                }
                sbData.append("\r\n");
            }

            // CHEMICAL APPLICATIONS Section
            for (int idx = 0; idx < mcArr.size(); idx++) {
                //if (!mcArr.isEmpty()) {
                sbData.append("*CHEMICAL APPLICATIONS\r\n");
                sbData.append("@C CDATE CHCOD CHAMT  CHME CHDEP   CHT..CHNAME\r\n");

                //            eventRecords = (ArrayList) data.getOr("chemical", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append(String.format("%1$2s %2$5s %3$-5s %4$5s %5$-5s %6$-5s %7$-5s  %8$s\r\n",
                            idx + 1, //data.getOr("ch", defValI).toString(),
                            formatDateStr(data.getOr("cdate", defValD).toString()),
                            data.getOr("chcd", defValC).toString(),
                            formatNumStr(5, data.getOr("chamt", defValR).toString()),
                            data.getOr("chacd", defValC).toString(),
                            data.getOr("chdep", defValC).toString(),
                            data.getOr("ch_targets", defValC).toString(),
                            data.getOr("ch_name", defValC).toString()));

                }
                sbData.append("\r\n");
            }

            // TILLAGE Section
            for (int idx = 0; idx < mtArr.size(); idx++) {
                //if (!mtArr.isEmpty()) {
                sbData.append("*TILLAGE\r\n");
                sbData.append("@T TDATE TIMPL  TDEP TNAME\r\n");

                //            eventRecords = (ArrayList) data.getOr("tillage", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append(String.format("%1$2s %2$5s %3$-5s %4$5s %5$s\r\n",
                            idx + 1, //data.getOr("ti", defValI).toString(),
                            formatDateStr(data.getOr("tdate", defValD).toString()),
                            data.getOr("tiimp", defValC).toString(),
                            formatNumStr(5, data.getOr("tidep", defValR).toString()),
                            data.getOr("ti_name", defValC).toString()));

                }
                sbData.append("\r\n");
            }

            // ENVIRONMENT MODIFICATIONS Section
            for (int idx = 0; idx < meArr.size(); idx++) {
                //if (!meArr.isEmpty()) {
                sbData.append("*ENVIRONMENT MODIFICATIONS\r\n");
                sbData.append("@E ODATE EDAY  ERAD  EMAX  EMIN  ERAIN ECO2  EDEW  EWIND ENVNAME\r\n");

                //            eventRecords = (ArrayList) data.getOr("environment", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append(String.format("%1$2s %2$5s %3$-1s%4$4s %5$-1s%6$4s %7$-1s%8$4s %9$-1s%10$4s %11$-1s%12$4s %13$-1s%14$4s %15$-1s%16$4s %17$-1s%18$4s %19$s\r\n",
                            idx + 1, //data.getOr("em", defValI).toString(),
                            formatDateStr(data.getOr("emday", defValD).toString()),
                            data.getOr("ecdyl", defValC).toString(),
                            formatNumStr(4, data.getOr("emdyl", defValR).toString()),
                            data.getOr("ecrad", defValC).toString(),
                            formatNumStr(4, data.getOr("emrad", defValR).toString()),
                            data.getOr("ecmax", defValC).toString(),
                            formatNumStr(4, data.getOr("emmax", defValR).toString()),
                            data.getOr("ecmin", defValC).toString(),
                            formatNumStr(4, data.getOr("emmin", defValR).toString()),
                            data.getOr("ecrai", defValC).toString(),
                            formatNumStr(4, data.getOr("emrai", defValR).toString()),
                            data.getOr("ecco2", defValC).toString(),
                            formatNumStr(4, data.getOr("emco2", defValR).toString()),
                            data.getOr("ecdew", defValC).toString(),
                            formatNumStr(4, data.getOr("emdew", defValR).toString()),
                            data.getOr("ecwnd", defValC).toString(),
                            formatNumStr(4, data.getOr("emwnd", defValR).toString()),
                            data.getOr("em_name", defValC).toString()));

                }
                sbData.append("\r\n");
            }

            // HARVEST DETAILS Section
            for (int idx = 0; idx < mhArr.size(); idx++) {
                //if (!mhArr.isEmpty()) {
                sbData.append("*HARVEST DETAILS\r\n");
                sbData.append("@H HDATE  HSTG  HCOM HSIZE   HPC  HBPC HNAME\r\n");

                //            eventRecords = (ArrayList) data.getOr("harvest", new ArrayList());
                //            for (int i = 0; i < eventRecords.size(); i++)
                {
                    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    sbData.append(String.format("%1$2s %2$5s %3$-5s %4$-5s %5$-5s %6$5s %7$5s %8$s\r\n",
                            idx + 1, //data.getOr("ha", defValI).toString(),
                            formatDateStr(data.getOr("hdate", defValD).toString()),
                            data.getOr("hastg", defValC).toString(),
                            data.getOr("hacom", defValC).toString(),
                            data.getOr("hasiz", defValC).toString(),
                            formatNumStr(5, data.getOr("hapc", defValR).toString()),
                            formatNumStr(5, data.getOr("habpc", defValR).toString()),
                            data.getOr("ha_name", defValC).toString()));

                }
                sbData.append("\r\n");
            }

            // SIMULATION CONTROLS and AUTOMATIC MANAGEMENT Section
            sbData.append(createSMMAStr(0, result));

            // Output finish
            br.write(sbError.toString());
            br.write(sbData.toString());
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Create string of Simulation Control and Automatic Management Section
     * 
     * @param type  The return type of string
     *                  (0:default; 1: undefined)
     * @param result  date holder for experiment data
     * @return      date string with format of "yyddd" 
     */
    private String createSMMAStr(int type, AdvancedHashMap result) {

        StringBuilder sb = new StringBuilder();
        String nitro;
        String water;
        String sdate;

        if (!result.getOr("fdate", "").toString().equals("")) {
            nitro = "Y";
        } else if (result.getOr("fertilizer", "").toString().equals("N")) {
            nitro = "Y";
        } else {
            nitro = "N";
        }

        if (!result.getOr("ireff", "").toString().equals("")) {
            water = "Y";
        } else if (result.getOr("irrig", "").toString().equals("N")) {
            water = "Y";
        } else {
            water = "N";
        }

        sdate = result.getOr("sdat", "").toString();
        if (sdate.equals("")) {
            sdate = result.getOr("pdate", defValD).toString();
        }
        sdate = formatDateStr(sdate);

        // Check return type
        if (type == 1) {
            // Undefined, will be used for future
        } // default
        else {
            sb.append("*SIMULATION CONTROLS\r\n");
            sb.append("@N GENERAL     NYERS NREPS START SDATE RSEED SNAME....................\r\n");
            sb.append(" 1 GE              1     1     S ").append(sdate).append("  2150 DEFAULT SIMULATION CONTROL\r\n");
            sb.append("@N OPTIONS     WATER NITRO SYMBI PHOSP POTAS DISES  CHEM  TILL   CO2\r\n");
//          sb.append(" 1 OP              Y     Y     Y     N     N     N     N     Y     M\r\n");
            sb.append(" 1 OP              ").append(water).append("     ").append(nitro).append("     Y     N     N     N     N     Y     M\r\n");
            sb.append("@N METHODS     WTHER INCON LIGHT EVAPO INFIL PHOTO HYDRO NSWIT MESOM MESEV MESOL\r\n");
            sb.append(" 1 ME              M     M     E     R     S     L     R     1     G     S     2\r\n");
            sb.append("@N MANAGEMENT  PLANT IRRIG FERTI RESID HARVS\r\n");
            sb.append(" 1 MA              R     R     R     R     M\r\n");
            sb.append("@N OUTPUTS     FNAME OVVEW SUMRY FROPT GROUT CAOUT WAOUT NIOUT MIOUT DIOUT VBOSE CHOUT OPOUT\r\n");
            sb.append(" 1 OU              N     Y     Y     1     Y     Y     Y     Y     Y     N     Y     N     Y\r\n\r\n");
            sb.append("@  AUTOMATIC MANAGEMENT\r\n");
            sb.append("@N PLANTING    PFRST PLAST PH2OL PH2OU PH2OD PSTMX PSTMN\r\n");
            sb.append(" 1 PL          82050 82064    40   100    30    40    10\r\n");
            sb.append("@N IRRIGATION  IMDEP ITHRL ITHRU IROFF IMETH IRAMT IREFF\r\n");
            sb.append(" 1 IR             30    50   100 GS000 IR001    10  1.00\r\n");
            sb.append("@N NITROGEN    NMDEP NMTHR NAMNT NCODE NAOFF\r\n");
            sb.append(" 1 NI             30    50    25 FE001 GS000\r\n");
            sb.append("@N RESIDUES    RIPCN RTIME RIDEP\r\n");
            sb.append(" 1 RE            100     1    20\r\n");
            sb.append("@N HARVEST     HFRST HLAST HPCNP HPCNR\r\n");
            sb.append(" 1 HA              0 83057   100     0\r\n");
        }

        return sb.toString();
    }

    /**
     * Set default value for missing data
     * 
     * @param result  date holder for experiment data
     */
    private void setDefVal(AdvancedHashMap result) {

        if (!result.getOr("icdat", "").toString().equals("")) {
            defValD = result.getOr("icdat", "").toString();
        } else if (!result.getOr("sdat", "").toString().equals("")) {
            defValD = result.getOr("sdat", "").toString();
        } else if (!result.getOr("pdate", "").toString().equals("")) {
            defValD = result.getOr("pdate", "").toString();
        } else {
            //throw new Exception("Experiment can't be output due to unavailable date info.");
            defValD = "20110101";
        }
        defValR = "-99";
        defValC = "";
        defValI = "-99";
    }

    /**
     * Get index value of the record and set new id value in the array
     * 
     * @param idVal  id string for the record
     * @param arr    array of the id
     * @return       current index value of the id
     */
    private int getIdxVal(String idStr, ArrayList arr) {

        int ret = 0;
        if (!idStr.equals("")) {
            ret = arr.indexOf(idStr);
            if (ret == -1) {
                arr.add(idStr);
                ret = arr.size();
            }
        }

        return ret;
    }
}
