package org.agmip.core.translators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;

/**
 * DSSAT Observation Data I/O API Class
 * 
 * @author Meng Zhang
 * @version 1.0
 */
public class DssatAFileOutput extends DssatCommonOutput {

    /**
     * DSSAT Observation Data Output method
     *
     * @param arg0 file name(?)
     * @param result data holder object
     */
    @Override
    public void writeFile(String arg0, AdvancedHashMap result) {

        // Initial variables
        JSONAdapter adapter = new JSONAdapter();    // JSON Adapter
        AdvancedHashMap<String, Object> record;     // Data holder for daily data
        AdvancedHashMap<String, Object> data;       // Data holder for whole Observation data
        BufferedWriter br;                   // output object
        StringBuilder sbData = new StringBuilder();     // construct the data info in the output
        HashMap titleList = new HashMap();          // Define necessary observation data fields
        titleList.put("hwam", "  HWAM");    //TODO
        titleList.put("mdat", "  MDAT");    //TODO
        titleList.put("adat", "  ADAT");    //TODO
        titleList.put("cwam", "  CWAM");
        HashMap altTitleList = new HashMap();        // Define alternative fields for the necessary observation data fields
        altTitleList.put("hwam", "hwah");
        altTitleList.put("mdat", "mdap");
        altTitleList.put("adat", "adap");
        HashMap optTitleList = new HashMap();        // Define optional observation data fields
        HashMap titleOutput = new HashMap();         // contain output data field id
        optTitleList.put("etcm", "  ETCM");
        optTitleList.put("prcm", "  PRCM");
        optTitleList.put("hdate", " HDATE");
        optTitleList.put("adap", "  ADAP");
        optTitleList.put("cwams", " CWAMS");
        optTitleList.put("hmah", "  HMAH");
        optTitleList.put("hwah", "  HWAH");
        optTitleList.put("hwahs", " HWAHS");
        optTitleList.put("hyah", "  HYAH");
        optTitleList.put("hyahs", " HYAHS");
        optTitleList.put("mdap", "  MDAP");
        optTitleList.put("r8aps", " R8APS");
        optTitleList.put("adaps", " ADAPS");
        optTitleList.put("chtds", " CHTDS");
        optTitleList.put("hmahs", " HMAHS");
        optTitleList.put("adoy", "  ADOY");
        optTitleList.put("ap1d", "  AP1D");
        optTitleList.put("br1d", "  BR1D");
        optTitleList.put("br2d", "  BR2D");
        optTitleList.put("br3d", "  BR3D");
        optTitleList.put("br4d", "  BR4D");
        optTitleList.put("bwah", "  BWAH");
        optTitleList.put("bwam", "  BWAM");
        optTitleList.put("cdwa", "  CDWA");
        optTitleList.put("cfah", "  CFAH");
        optTitleList.put("chta", "  CHTA");
        optTitleList.put("chwa", "  CHWA");
        optTitleList.put("cnaa", "  CNAA");
        optTitleList.put("cnam", "  CNAM");
        optTitleList.put("cpaa", "  CPAA");
        optTitleList.put("cpam", "  CPAM");
        optTitleList.put("cwaa", "  CWAA");
        optTitleList.put("drcm", "  DRCM");
        optTitleList.put("drid", "  DRID");
        optTitleList.put("dwap", "  DWAP");
        optTitleList.put("e#am", "  E#AM");
        optTitleList.put("e#um", "  E#UM");
        optTitleList.put("eemd", "  EEMD");
        optTitleList.put("eoaa", "  EOAA");
        optTitleList.put("epac", "  EPAC");
        optTitleList.put("epcm", "  EPCM");
        optTitleList.put("escm", "  ESCM");
        optTitleList.put("ewum", "  EWUM");
        optTitleList.put("fdat", "  FDAT");
        optTitleList.put("fwah", "  FWAH");
        optTitleList.put("gl%m", "  GL%M");
        optTitleList.put("gn%m", "  GN%M");
        optTitleList.put("gnam", "  GNAM");
        optTitleList.put("gp%m", "  GP%M");
        optTitleList.put("gpam", "  GPAM");
        optTitleList.put("gw%m", "  GW%M");
        optTitleList.put("gwam", "  GWAM");
        optTitleList.put("gwgm", "  GWGM");
        optTitleList.put("gwpm", "  GWPM");
        optTitleList.put("gyam", "  GYAM");
        optTitleList.put("gypm", "  GYPM");
        optTitleList.put("gyvm", "  GYVM");
        optTitleList.put("h#am", "  H#AM");
        optTitleList.put("h#gm", "  H#GM");
        optTitleList.put("h#um", "  H#UM");
        optTitleList.put("hastg", " HASTG");
        optTitleList.put("hdap", "  HDAP");
        optTitleList.put("hiam", "  HIAM");
        optTitleList.put("hipm", "  HIPM");
        optTitleList.put("hprh", "  HPRH");
        optTitleList.put("hwac", "  HWAC");
        optTitleList.put("hwum", "  HWUM");
        optTitleList.put("hyam", "  HYAM");
        optTitleList.put("icsw", "  ICSW");
        optTitleList.put("idap", "  IDAP");
        optTitleList.put("idat", "  IDAT");
        optTitleList.put("ir#m", "  IR#M");
        optTitleList.put("ircm", "  IRCM");
        optTitleList.put("l#sm", "  L#SM");
        optTitleList.put("l#sx", "  L#SX");
        optTitleList.put("laix", "  LAIX");
        optTitleList.put("lf3d", "  LF3D");
        optTitleList.put("lf5d", "  LF5D");
        optTitleList.put("liwam", " LIWAM");
        optTitleList.put("llfd", "  LLFD");
        optTitleList.put("lwam", "  LWAM");
        optTitleList.put("mdat2", " MDAT2");
        optTitleList.put("mdoy", "  MDOY");
        optTitleList.put("niam", "  NIAM");
        optTitleList.put("nlcm", "  NLCM");
        optTitleList.put("nucm", "  NUCM");
//        optTitleList.put("obs_trt_id", " OBS_TRT_ID"); // TRNO TODO Kept for furture using
        optTitleList.put("ocam", "  OCAM");
        optTitleList.put("oid", "   OID");
        optTitleList.put("oid", "   OID");
        optTitleList.put("oid", "   OID");
        optTitleList.put("onam", "  ONAM");
        optTitleList.put("p#am", "  P#AM");
        optTitleList.put("pd1p", "  PD1P");
        optTitleList.put("pd1t", "  PD1T");
        optTitleList.put("pdfp", "  PDFP");
        optTitleList.put("pdft", "  PDFT");
        optTitleList.put("pwam", "  PWAM");
        optTitleList.put("r1at", "  R1AT");
        optTitleList.put("r2at", "  R2AT");
        optTitleList.put("r3at", "  R3AT");
        optTitleList.put("r4at", "  R4AT");
        optTitleList.put("r5at", "  R5AT");
        optTitleList.put("r6at", "  R6AT");
        optTitleList.put("r7at", "  R7AT");
        optTitleList.put("r8ap", "  R8AP");
        optTitleList.put("r8at", "  R8AT");
        optTitleList.put("r9at", "  R9AT");
        optTitleList.put("rnah", "  RNAH");
        optTitleList.put("rocm", "  ROCM");
        optTitleList.put("rpah", "  RPAH");
        optTitleList.put("snam", "  SNAM");
        optTitleList.put("spam", "  SPAM");
        optTitleList.put("sqdat", " SQDAT");
        optTitleList.put("sraa", "  SRAA");
        optTitleList.put("swxm", "  SWXM");
        optTitleList.put("t#am", "  T#AM");
        optTitleList.put("tdap", "  TDAP");
        optTitleList.put("tdat", "  TDAT");
        optTitleList.put("tham", "  THAM");
        optTitleList.put("tl#c", "  TL#C");
        optTitleList.put("tnah", "  TNAH");
        optTitleList.put("tnim", "  TNIM");
        optTitleList.put("tspd", "  TSPD");
        optTitleList.put("twah", "  TWAH");
        optTitleList.put("twam", "  TWAM");
        optTitleList.put("un%h", "  UN%H");
        optTitleList.put("unam", "  UNAM");
        optTitleList.put("upam", "  UPAM");
        optTitleList.put("uwah", "  UWAH");
        optTitleList.put("uyah", "  UYAH");
        optTitleList.put("vwam", "  VWAM");
        optTitleList.put("z21d", "  Z21D");
        optTitleList.put("z30d", "  Z30D");
        optTitleList.put("z31d", "  Z31D");
        optTitleList.put("z37d", "  Z37D");
        optTitleList.put("z39d", "  Z39D");
        Set titleListId = titleList.keySet();
        Set optTitleListId = optTitleList.keySet();
        int trno = 1;
//        File file;
//        FileWriter output;

        try {

            // Set default value for missing data
            setDefVal();

            // Get Data from input holder
            ArrayList observeRecords;
            try {
                observeRecords = (ArrayList) result.getOr("observed", new ArrayList());
            } catch (ClassCastException e) {
                observeRecords = new ArrayList();
                observeRecords.add(result.getOr("observed", new LinkedHashMap()));
            }

            // Initial BufferedWriter
            String exName = getExName(result);
            String fileName = exName;
            if (fileName.equals("")) {
                fileName = "a.tmp";
            } else {
                fileName = fileName.substring(0, fileName.length() - 2) + "." + fileName.substring(fileName.length() - 2) + "A";
            }
            br = new BufferedWriter(new FileWriter(new File(fileName)));

            // Output Observation File
            // Titel Section
            sbData.append(String.format("*EXP. DATA (A): %1$-10s %2$s\r\n\r\n", exName, result.getOr("local_name", defValC).toString()));

            // Check if all necessary field is available
            //Map obvTemp = new LinkedHashMap();
            //obvTemp.put("observed", observeRecords);
            //AdvancedHashMap obvData = adapter.exportRecord(obvTemp);
            AdvancedHashMap obvData = adapter.exportRecord((Map) result.getOr("observed", new AdvancedHashMap())); // TODO 
            for (Object title : titleListId) {
                // check which optional data is exist, if not, remove from map
                if (!obvData.getOr(title.toString(), "").toString().equals("")) {
                    titleOutput.put(title, titleList.get(title));
                } else if (altTitleList.containsKey(title) && !obvData.getOr(altTitleList.get(title).toString(), "").toString().equals("")) {
                    titleOutput.put(altTitleList.get(title), titleList.get(title));
                } else {
                    // TODO throw new Exception("Incompleted record because missing data : [" + title + "]");
                    sbError.append("! Waring: Incompleted record because missing data : [").append(title).append("]\r\n");
                }
            }

            // Check if which optional field is available
            for (Object title : optTitleListId) {
                // check which optional data is exist, if not, remove from map
                if (!obvData.getOr(title.toString(), "").toString().equals("")) {
                    if (!titleOutput.containsKey(title)) {
                        titleOutput.put(title, optTitleList.get(title));
                    }

                }
            }

            // Observation Data Section
            Object[] titleOutputId = titleOutput.keySet().toArray();
            for (int i = 0; i < (titleOutputId.length / 40 + titleOutputId.length % 40 == 0 ? 0 : 1); i++) {

                sbData.append("@TRNO ");
                int limit = Math.min(titleOutputId.length, (i + 1) * 40);
                for (int j = i * 40; j < limit; j++) {
                    sbData.append(titleOutput.get(titleOutputId[j]).toString());
                }
                sbData.append("\r\n");

                for (int j = 0; j < observeRecords.size(); j++) {

                    record = adapter.exportRecord((Map) observeRecords.get(j));
                    sbData.append(String.format(" %1$5d", trno));
                    for (int k = i * 40; k < limit; k++) {
                        if ((titleOutputId[k].toString().equals("adap") && titleOutput.get(titleOutputId[k]).toString().trim().equals("ADAT"))
                                || (titleOutputId[k].toString().equals("mdap") && titleOutput.get(titleOutputId[k]).toString().trim().equals("MDAT"))) {
                            sbData.append(String.format("%1$6s", formatDateStr(result.getOr("pdate", defValI).toString(), record.getOr(titleOutputId[k].toString(), defValI).toString())));
                        } else {
                            sbData.append(" ").append(formatNumStr(5, record.getOr(titleOutputId[k].toString(), defValI).toString()));
                        }
                    }
                    sbData.append("\r\n");
                }

            }

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
     * Set default value for missing data
     *
     * @version 1.0
     */
    private void setDefVal() {

        // defValD = ""; No need to set default value for Date type in Observation file
        defValR = "-99";
        defValC = "";
        defValI = "-99";
    }
}
