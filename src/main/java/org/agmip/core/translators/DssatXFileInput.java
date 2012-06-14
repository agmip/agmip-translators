/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.agmip.core.translators;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.agmip.core.types.AdvancedHashMap;

/**
 * DSSAT Experiment Data I/O API Class
 * 
 * @author Meng Zhang
 * @version 1.0
 */
public class DssatXFileInput extends DssatCommonInput {

    @Override
    /**
     * DSSAT Weather Data Output method
     * 
     * @param arg0  file name
     * @return result data holder object
     */
    public AdvancedHashMap readFile(String arg0) {

        AdvancedHashMap ret = new AdvancedHashMap();
        String filePath = arg0;
        String line;

        try {
            FileInputStream fstream = new FileInputStream(filePath);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                // TODO Create holders for each section and build final result holder in the last
                // Get content type of line
                judgeContentType(line);

                // Read Exp Detail
                if (flg[0].startsWith("exp.details:") && flg[2].equals("")) {
                    // TODO
                    ret.put("exname", line.substring(14, 24).trim());
                    ret.put("institutes", line.substring(14, 16));
                } // Read General Section
                else if (flg[0].startsWith("general")) {
                    if (flg[1].equals("people") && flg[2].equals("data")) {
                        //$ret[$flg[0]]["people"] = trim($line);
                    } else if (flg[1].equals("address") && flg[2].equals("data")) {
                        //$ret[$flg[0]]["address"] = trim($line);
                        String[] addr = line.split(",[ ]*");
                        ret.put("fl_loc_1", "");
                        ret.put("fl_loc_2", "");
                        ret.put("fl_loc_3", "");
                        if (!line.trim().equals("")) {
                            ret.put("institutes", line.trim());
                        }
                        switch (addr.length) {
                            case 0:
                                break;
                            case 1:
                                ret.put("fl_loc_1", addr[0]);
                                break;
                            case 2:
                                ret.put("fl_loc_1", addr[1]);
                                ret.put("fl_loc_2", addr[0]);
                                break;
                            case 3:
                                ret.put("fl_loc_1", addr[2]);
                                ret.put("fl_loc_2", addr[1]);
                                ret.put("fl_loc_3", addr[0]);
                                break;
                            default:
                                ret.put("fl_loc_1", addr[addr.length - 1]);
                                ret.put("fl_loc_2", addr[addr.length - 2]);
                                String loc3 = "";
                                for (int i = 0; i < addr.length - 2; i++) {
                                    loc3 += addr[i] + ", ";
                                }
                                ret.put("fl_loc_3", loc3.substring(0, loc3.length() - 2));
                        }
                    } else if ((flg[1].equals("site") || flg[1].equals("sites")) && flg[2].equals("data")) {
                        //$ret[$flg[0]]["site"] = trim($line);
                    } else if (flg[1].startsWith("parea") && flg[2].equals("data")) {
                    } else if (flg[1].equals("notes") && flg[2].equals("data")) {
                        //$ret[$flg[0]]["notes"] = addArray($ret[$flg[0]]["notes"], " ". trim($line), "");
                    } else {
                    }

                } // Read TREATMENTS Section
                else if (flg[0].startsWith("treatments")) {
                    //TODO
                } // Read CULTIVARS Section
                else if (flg[0].startsWith("cultivars")) {
                    // TODO
                    ret.put("cr", line.substring(3, 5).trim());
                } // Read FIELDS Section
                else if (flg[0].startsWith("fields")) {

                    if (flg[1].startsWith("l id_") && flg[2].equals("data")) {
                        //TODO
                    } else if (flg[1].startsWith("l ...") && flg[2].equals("data")) {
                        // TODO read long alt from weather file if not in XFile
                        ret.put("fl_lat", line.substring(3, 18).trim());
                        ret.put("fl_long", line.substring(19, 34).trim());
                    }

                } // Read SOIL ANALYSIS Section
                else if (flg[0].startsWith("soil")) {
                    // TODO
                } // Read INITIAL CONDITIONS Section
                else if (flg[0].startsWith("initial")) {
                    // TODO
                } // Read PLANTING DETAILS Section
                else if (flg[0].startsWith("planting")) {
                    // TODO
                    ret.put("pdate", line.substring(3, 8).trim());
                } // Read IRRIGATION AND WATER MANAGEMENT Section
                else if (flg[0].startsWith("irrigation")) {
                    // TODO
                } // Read FERTILIZERS (INORGANIC) Section
                else if (flg[0].startsWith("fertilizers")) {
                    // TODO
                } // Read RESIDUES AND OTHER ORGANIC MATERIALS Section
                else if (flg[0].startsWith("residues")) {
                    // TODO
                } // Read CHEMICAL APPLICATIONS Section
                else if (flg[0].startsWith("chemical")) {
                    // TODO
                } // Read TILLAGE Section
                else if (flg[0].startsWith("tillage")) {
                    // TODO
                } // Read ENVIRONMENT MODIFICATIONS Section
                else if (flg[0].startsWith("environment")) {
                    // TODO
                } // Read HARVEST DETAILS Section
                else if (flg[0].startsWith("harvest")) {
                    // TODO
                    ret.put("hdate", line.substring(3, 8).trim());
                } // Read SIMULATION CONTROLS Section
                else if (flg[0].startsWith("simulation")) {
                    // TODO
                } else {
                }


            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }


        return ret;
    }

    @Override
    /**
     * Set reading flgs for title lines
     * 
     * @param line  the string of reading line
     */
    protected void setTitleFlgs(String line) {
        flg[0] = line.substring(1).trim().toLowerCase();
        flg[1] = "";
        flg[2] = "";
    }
}
