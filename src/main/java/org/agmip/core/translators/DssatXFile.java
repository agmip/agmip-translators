package org.agmip.core.translators;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Calendar;
import java.util.Map;
import java.util.ArrayList;
import org.agmip.core.types.*;
import org.agmip.core.types.weather.*;
import org.agmip.util.JSONAdapter;

/**
 * DSSAT Experiment Data I/O API Class
 * 
 * @author Meng Zhang
 * @version 1.0
 */
public class DssatXFile implements WeatherFile {

    public static String jsonExample = "{\"observed\": {\"hwah\": 2929.0, \"adap\": 76.0, \"cwam\": 5532.0, \"mdap\": 129.0}, \"management\": {\"plrs\": 61.0, \"plpoe\": 7.2, \"hacom\": \"H\", \"icpcr\": \"MZ\", \"nnumm\": 3.0, \"CropMgmt\": [{\"fedep\": 10.0, \"feamn\": 27.0, \"feacd\": \"AP001\", \"feamk\": 0.0, \"fecd\": \"FE001\", \"feamp\": 0.0, \"fdate\": \"4/7/82\"}, {\"fedep\": 10.0, \"feamn\": 35.0, \"feacd\": \"AP001\", \"feamk\": 0.0, \"fecd\": \"FE001\", \"feamp\": 0.0, \"fdate\": \"4/12/82\"}, {\"fedep\": 10.0, \"feamn\": 54.0, \"feacd\": \"AP001\", \"feamk\": 0.0, \"fecd\": \"FE001\", \"feamp\": 0.0, \"fdate\": \"5/17/82\"}, {\"fedep\": \"\", \"feamn\": \"\", \"feacd\": \"\", \"feamk\": \"\", \"fecd\": \"\", \"feamp\": \"\", \"fdate\": \"\"}, {\"fedep\": \"\", \"feamn\": \"\", \"feacd\": \"\", \"feamk\": \"\", \"fecd\": \"\", \"feamp\": \"\", \"fdate\": \"\"}, {\"fedep\": \"\", \"feamn\": \"\", \"feacd\": \"\", \"feamk\": \"\", \"fecd\": \"\", \"feamp\": \"\", \"fdate\": \"\"}], \"fen_tot\": 116.0}, \"meta\": {\"cul_id\": \"IB0035\", \"people\": \" BENNET,J.M. ZUR,B. HAMMOND,L.C. JONES,J.W.\", \"fl_long\": -82.37, \"cr\": \"MZ\", \"institutes\": \"University of Florida\", \"main_factor\": \"NxW\", \"num_years\": 1.0, \"wst_long\": -82.37, \"wst_source\": \"Measured\", \"data_source\": \"DSSAT\", \"exname\": \"UFGA8201.MZX\", \"flele\": 40.0, \"fertilizer\": \"P\", \"wst_insi\": \"UFGA\", \"wst_dist\": 0.0, \"fl_loc_1\": \"USA\", \"fl_loc_2\": \"FLA\", \"fl_loc_3\": \"Gainesville\", \"soil_id\": \"IBMZ910014\", \"hdate\": \"7/4/82\", \"fl_name\": \"Irrigation Park\", \"pdate\": \"2/25/82\", \"wst_name\": \"Gainesville, FL\", \"exp_factors\": \"N; W\", \"id_field\": \"UFGA0002\", \"sltx\": \"LS\", \"eid\": 1.0, \"local_name\": \"Gainesville, FL\", \"irrig\": \"N\", \"distribution\": \"None\", \"wst_lat\": 29.63, \"fl_lat\": 29.63, \"sl_source\": \"SCS\"}, \"weather\": {\"tav\": 20.9, \"wndht\": 3.0, \"wst_name\": \"Gainesville, FL\", \"refht\": 2.0, \"elev\": 10.0, \"wst_insi\": \"UFGA\", \"WeatherDaily\": [{\"w_date\": \"19820101\", \"srad\": 5.9, \"tmax\": 24.4, \"tmin\": 15.6, \"rain\": 19.0}, {\"w_date\": \"19820102\", \"srad\": 7.0, \"tmax\": 22.2, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"1/3/82\", \"srad\": 9.0, \"tmax\": 27.8, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"1/4/82\", \"srad\": 3.1, \"tmax\": 26.1, \"tmin\": 15.0, \"rain\": 9.4}, {\"w_date\": \"1/5/82\", \"srad\": 12.9, \"tmax\": 17.2, \"tmin\": 2.2, \"rain\": 0.0}, {\"w_date\": \"1/6/82\", \"srad\": 11.4, \"tmax\": 25.0, \"tmin\": 4.4, \"rain\": 0.0}, {\"w_date\": \"1/7/82\", \"srad\": 8.5, \"tmax\": 26.7, \"tmin\": 11.7, \"rain\": 0.0}, {\"w_date\": \"1/8/82\", \"srad\": 3.0, \"tmax\": 22.8, \"tmin\": 14.4, \"rain\": 5.3}, {\"w_date\": \"1/9/82\", \"srad\": 14.4, \"tmax\": 16.7, \"tmin\": 8.9, \"rain\": 0.0}, {\"w_date\": \"1/10/82\", \"srad\": 12.0, \"tmax\": 14.4, \"tmin\": 1.1, \"rain\": 0.0}, {\"w_date\": \"1/11/82\", \"srad\": 15.0, \"tmax\": 14.4, \"tmin\": -6.7, \"rain\": 0.0}, {\"w_date\": \"1/12/82\", \"srad\": 10.8, \"tmax\": 10.0, \"tmin\": -7.8, \"rain\": 0.0}, {\"w_date\": \"1/13/82\", \"srad\": 4.8, \"tmax\": 20.0, \"tmin\": 3.9, \"rain\": 19.8}, {\"w_date\": \"1/14/82\", \"srad\": 6.2, \"tmax\": 18.9, \"tmin\": 6.1, \"rain\": 81.0}, {\"w_date\": \"1/15/82\", \"srad\": 14.4, \"tmax\": 12.2, \"tmin\": -3.3, \"rain\": 0.0}, {\"w_date\": \"1/16/82\", \"srad\": 12.6, \"tmax\": 19.4, \"tmin\": -1.7, \"rain\": 0.0}, {\"w_date\": \"1/17/82\", \"srad\": 14.6, \"tmax\": 19.4, \"tmin\": 5.6, \"rain\": 2.8}, {\"w_date\": \"1/18/82\", \"srad\": 11.2, \"tmax\": 20.6, \"tmin\": 1.1, \"rain\": 0.0}, {\"w_date\": \"1/19/82\", \"srad\": 13.3, \"tmax\": 25.6, \"tmin\": 8.9, \"rain\": 0.0}, {\"w_date\": \"1/20/82\", \"srad\": 13.4, \"tmax\": 27.8, \"tmin\": 8.3, \"rain\": 0.0}, {\"w_date\": \"1/21/82\", \"srad\": 11.6, \"tmax\": 27.8, \"tmin\": 10.0, \"rain\": 0.0}, {\"w_date\": \"1/22/82\", \"srad\": 10.7, \"tmax\": 25.6, \"tmin\": 12.2, \"rain\": 0.0}, {\"w_date\": \"1/23/82\", \"srad\": 10.1, \"tmax\": 26.1, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"1/24/82\", \"srad\": 12.9, \"tmax\": 23.9, \"tmin\": 10.0, \"rain\": 0.0}, {\"w_date\": \"1/25/82\", \"srad\": 14.1, \"tmax\": 21.1, \"tmin\": 0.0, \"rain\": 0.0}, {\"w_date\": \"1/26/82\", \"srad\": 14.2, \"tmax\": 20.0, \"tmin\": 4.4, \"rain\": 0.0}, {\"w_date\": \"1/27/82\", \"srad\": 13.9, \"tmax\": 15.6, \"tmin\": -1.1, \"rain\": 0.0}, {\"w_date\": \"1/28/82\", \"srad\": 13.8, \"tmax\": 21.7, \"tmin\": 2.2, \"rain\": 0.0}, {\"w_date\": \"1/29/82\", \"srad\": 13.3, \"tmax\": 22.2, \"tmin\": 3.3, \"rain\": 0.0}, {\"w_date\": \"1/30/82\", \"srad\": 12.5, \"tmax\": 25.6, \"tmin\": 8.9, \"rain\": 0.0}, {\"w_date\": \"1/31/82\", \"srad\": 12.6, \"tmax\": 28.9, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"2/1/82\", \"srad\": 14.0, \"tmax\": 26.1, \"tmin\": 12.2, \"rain\": 0.3}, {\"w_date\": \"2/2/82\", \"srad\": 7.6, \"tmax\": 26.7, \"tmin\": 12.8, \"rain\": 2.0}, {\"w_date\": \"2/3/82\", \"srad\": 3.3, \"tmax\": 27.2, \"tmin\": 15.6, \"rain\": 25.9}, {\"w_date\": \"2/4/82\", \"srad\": 12.5, \"tmax\": 24.4, \"tmin\": 14.4, \"rain\": 1.0}, {\"w_date\": \"2/5/82\", \"srad\": 8.5, \"tmax\": 27.8, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"2/6/82\", \"srad\": 6.3, \"tmax\": 26.7, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"2/7/82\", \"srad\": 8.0, \"tmax\": 26.7, \"tmin\": 10.6, \"rain\": 0.0}, {\"w_date\": \"2/8/82\", \"srad\": 12.5, \"tmax\": 25.0, \"tmin\": 10.0, \"rain\": 0.0}, {\"w_date\": \"2/9/82\", \"srad\": 7.9, \"tmax\": 26.7, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"2/10/82\", \"srad\": 4.0, \"tmax\": 25.6, \"tmin\": 14.4, \"rain\": 0.8}, {\"w_date\": \"2/11/82\", \"srad\": 6.5, \"tmax\": 18.9, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"2/12/82\", \"srad\": 7.4, \"tmax\": 25.0, \"tmin\": 10.6, \"rain\": 0.8}, {\"w_date\": \"2/13/82\", \"srad\": 13.3, \"tmax\": 25.6, \"tmin\": 10.6, \"rain\": 18.0}, {\"w_date\": \"2/14/82\", \"srad\": 16.1, \"tmax\": 26.1, \"tmin\": 5.6, \"rain\": 0.0}, {\"w_date\": \"2/15/82\", \"srad\": 9.6, \"tmax\": 26.7, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"2/16/82\", \"srad\": 10.8, \"tmax\": 27.8, \"tmin\": 16.1, \"rain\": 67.3}, {\"w_date\": \"2/17/82\", \"srad\": 13.4, \"tmax\": 27.2, \"tmin\": 14.4, \"rain\": 10.9}, {\"w_date\": \"2/18/82\", \"srad\": 17.0, \"tmax\": 26.1, \"tmin\": 11.1, \"rain\": 0.0}, {\"w_date\": \"2/19/82\", \"srad\": 15.5, \"tmax\": 25.6, \"tmin\": 12.2, \"rain\": 0.0}, {\"w_date\": \"2/20/82\", \"srad\": 16.9, \"tmax\": 25.6, \"tmin\": 10.0, \"rain\": 0.0}, {\"w_date\": \"2/21/82\", \"srad\": 16.6, \"tmax\": 24.4, \"tmin\": 9.4, \"rain\": 0.0}, {\"w_date\": \"2/22/82\", \"srad\": 17.0, \"tmax\": 21.1, \"tmin\": 7.8, \"rain\": 0.0}, {\"w_date\": \"2/23/82\", \"srad\": 17.7, \"tmax\": 23.9, \"tmin\": 1.1, \"rain\": 0.0}, {\"w_date\": \"2/24/82\", \"srad\": 17.5, \"tmax\": 26.7, \"tmin\": 3.3, \"rain\": 0.0}, {\"w_date\": \"2/25/82\", \"srad\": 14.8, \"tmax\": 27.2, \"tmin\": 10.6, \"rain\": 0.0}, {\"w_date\": \"2/26/82\", \"srad\": 3.4, \"tmax\": 26.7, \"tmin\": 11.1, \"rain\": 0.0}, {\"w_date\": \"2/27/82\", \"srad\": 8.9, \"tmax\": 25.6, \"tmin\": 8.9, \"rain\": 0.0}, {\"w_date\": \"2/28/82\", \"srad\": 9.3, \"tmax\": 24.4, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"3/1/82\", \"srad\": 10.9, \"tmax\": 17.8, \"tmin\": 8.3, \"rain\": 0.0}, {\"w_date\": \"3/2/82\", \"srad\": 18.4, \"tmax\": 22.8, \"tmin\": 1.7, \"rain\": 0.0}, {\"w_date\": \"3/3/82\", \"srad\": 17.6, \"tmax\": 25.0, \"tmin\": 6.1, \"rain\": 0.0}, {\"w_date\": \"3/4/82\", \"srad\": 16.2, \"tmax\": 28.3, \"tmin\": 9.4, \"rain\": 0.0}, {\"w_date\": \"3/5/82\", \"srad\": 6.3, \"tmax\": 27.8, \"tmin\": 16.1, \"rain\": 8.4}, {\"w_date\": \"3/6/82\", \"srad\": 10.3, \"tmax\": 25.0, \"tmin\": 16.1, \"rain\": 35.1}, {\"w_date\": \"3/7/82\", \"srad\": 9.0, \"tmax\": 24.4, \"tmin\": 15.0, \"rain\": 14.2}, {\"w_date\": \"3/8/82\", \"srad\": 19.8, \"tmax\": 16.7, \"tmin\": 1.1, \"rain\": 0.0}, {\"w_date\": \"3/9/82\", \"srad\": 19.6, \"tmax\": 21.7, \"tmin\": 3.9, \"rain\": 0.0}, {\"w_date\": \"3/10/82\", \"srad\": 16.1, \"tmax\": 25.6, \"tmin\": 10.6, \"rain\": 0.0}, {\"w_date\": \"3/11/82\", \"srad\": 17.7, \"tmax\": 27.8, \"tmin\": 9.4, \"rain\": 0.0}, {\"w_date\": \"3/12/82\", \"srad\": 20.0, \"tmax\": 28.9, \"tmin\": 11.1, \"rain\": 0.0}, {\"w_date\": \"3/13/82\", \"srad\": 19.0, \"tmax\": 30.6, \"tmin\": 10.0, \"rain\": 0.0}, {\"w_date\": \"3/14/82\", \"srad\": 18.4, \"tmax\": 31.7, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"3/15/82\", \"srad\": 17.5, \"tmax\": 30.0, \"tmin\": 16.7, \"rain\": 0.0}, {\"w_date\": \"3/16/82\", \"srad\": 15.9, \"tmax\": 30.0, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"3/17/82\", \"srad\": 19.5, \"tmax\": 30.6, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"3/18/82\", \"srad\": 17.4, \"tmax\": 31.7, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"3/19/82\", \"srad\": 16.7, \"tmax\": 30.6, \"tmin\": 18.3, \"rain\": 1.8}, {\"w_date\": \"3/20/82\", \"srad\": 17.4, \"tmax\": 30.6, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"3/21/82\", \"srad\": 13.6, \"tmax\": 30.0, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"3/22/82\", \"srad\": 10.0, \"tmax\": 28.3, \"tmin\": 17.2, \"rain\": 0.3}, {\"w_date\": \"3/23/82\", \"srad\": 13.2, \"tmax\": 26.1, \"tmin\": 16.1, \"rain\": 9.4}, {\"w_date\": \"3/24/82\", \"srad\": 4.3, \"tmax\": 26.1, \"tmin\": 16.1, \"rain\": 4.3}, {\"w_date\": \"3/25/82\", \"srad\": 17.0, \"tmax\": 27.8, \"tmin\": 16.1, \"rain\": 26.2}, {\"w_date\": \"3/26/82\", \"srad\": 18.8, \"tmax\": 26.7, \"tmin\": 14.4, \"rain\": 2.8}, {\"w_date\": \"3/27/82\", \"srad\": 12.9, \"tmax\": 22.2, \"tmin\": 5.6, \"rain\": 0.0}, {\"w_date\": \"3/28/82\", \"srad\": 3.8, \"tmax\": 15.6, \"tmin\": 8.9, \"rain\": 9.7}, {\"w_date\": \"3/29/82\", \"srad\": 9.9, \"tmax\": 21.1, \"tmin\": 10.0, \"rain\": 25.7}, {\"w_date\": \"3/30/82\", \"srad\": 21.5, \"tmax\": 26.1, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"3/31/82\", \"srad\": 16.1, \"tmax\": 26.7, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"4/1/82\", \"srad\": 21.0, \"tmax\": 30.6, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"4/2/82\", \"srad\": 22.1, \"tmax\": 29.4, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"4/3/82\", \"srad\": 11.4, \"tmax\": 28.9, \"tmin\": 17.8, \"rain\": 0.0}, {\"w_date\": \"4/4/82\", \"srad\": 23.8, \"tmax\": 28.3, \"tmin\": 9.4, \"rain\": 0.0}, {\"w_date\": \"4/5/82\", \"srad\": 18.9, \"tmax\": 29.4, \"tmin\": 11.1, \"rain\": 0.0}, {\"w_date\": \"4/6/82\", \"srad\": 24.1, \"tmax\": 25.6, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"4/7/82\", \"srad\": 25.1, \"tmax\": 22.2, \"tmin\": 6.7, \"rain\": 0.0}, {\"w_date\": \"4/8/82\", \"srad\": 0.8, \"tmax\": 21.1, \"tmin\": 11.1, \"rain\": 48.0}, {\"w_date\": \"4/9/82\", \"srad\": 8.8, \"tmax\": 23.3, \"tmin\": 17.2, \"rain\": 98.8}, {\"w_date\": \"4/10/82\", \"srad\": 3.8, \"tmax\": 23.9, \"tmin\": 10.6, \"rain\": 3.6}, {\"w_date\": \"4/11/82\", \"srad\": 23.0, \"tmax\": 20.6, \"tmin\": 8.9, \"rain\": 42.7}, {\"w_date\": \"4/12/82\", \"srad\": 25.4, \"tmax\": 25.0, \"tmin\": 6.1, \"rain\": 0.0}, {\"w_date\": \"4/13/82\", \"srad\": 21.6, \"tmax\": 28.3, \"tmin\": 11.7, \"rain\": 0.0}, {\"w_date\": \"4/14/82\", \"srad\": 21.6, \"tmax\": 27.2, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"4/15/82\", \"srad\": 16.6, \"tmax\": 28.3, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"4/16/82\", \"srad\": 20.7, \"tmax\": 30.6, \"tmin\": 17.8, \"rain\": 0.0}, {\"w_date\": \"4/17/82\", \"srad\": 21.0, \"tmax\": 31.1, \"tmin\": 19.4, \"rain\": 0.5}, {\"w_date\": \"4/18/82\", \"srad\": 18.9, \"tmax\": 30.0, \"tmin\": 18.9, \"rain\": 0.5}, {\"w_date\": \"4/19/82\", \"srad\": 21.6, \"tmax\": 30.0, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"4/20/82\", \"srad\": 20.7, \"tmax\": 31.1, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"4/21/82\", \"srad\": 24.3, \"tmax\": 32.2, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"4/22/82\", \"srad\": 23.7, \"tmax\": 31.7, \"tmin\": 17.8, \"rain\": 0.0}, {\"w_date\": \"4/23/82\", \"srad\": 8.6, \"tmax\": 31.1, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"4/24/82\", \"srad\": 7.5, \"tmax\": 22.2, \"tmin\": 13.9, \"rain\": 0.0}, {\"w_date\": \"4/25/82\", \"srad\": 7.7, \"tmax\": 25.6, \"tmin\": 16.7, \"rain\": 4.3}, {\"w_date\": \"4/26/82\", \"srad\": 10.7, \"tmax\": 25.6, \"tmin\": 19.4, \"rain\": 23.1}, {\"w_date\": \"4/27/82\", \"srad\": 23.4, \"tmax\": 29.4, \"tmin\": 15.6, \"rain\": 0.3}, {\"w_date\": \"4/28/82\", \"srad\": 23.7, \"tmax\": 28.9, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"4/29/82\", \"srad\": 18.5, \"tmax\": 27.2, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"4/30/82\", \"srad\": 20.7, \"tmax\": 26.7, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"5/1/82\", \"srad\": 18.2, \"tmax\": 26.7, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"5/2/82\", \"srad\": 19.9, \"tmax\": 27.8, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"5/3/82\", \"srad\": 21.8, \"tmax\": 28.9, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"5/4/82\", \"srad\": 23.6, \"tmax\": 28.9, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"5/5/82\", \"srad\": 24.4, \"tmax\": 28.3, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"5/6/82\", \"srad\": 21.7, \"tmax\": 27.8, \"tmin\": 12.2, \"rain\": 0.0}, {\"w_date\": \"5/7/82\", \"srad\": 23.6, \"tmax\": 28.9, \"tmin\": 11.7, \"rain\": 0.0}, {\"w_date\": \"5/8/82\", \"srad\": 18.2, \"tmax\": 29.4, \"tmin\": 17.8, \"rain\": 1.0}, {\"w_date\": \"5/9/82\", \"srad\": 26.3, \"tmax\": 29.4, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"5/10/82\", \"srad\": 26.3, \"tmax\": 29.4, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"5/11/82\", \"srad\": 24.9, \"tmax\": 30.0, \"tmin\": 11.1, \"rain\": 0.0}, {\"w_date\": \"5/12/82\", \"srad\": 23.5, \"tmax\": 31.1, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"5/13/82\", \"srad\": 24.2, \"tmax\": 30.6, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"5/14/82\", \"srad\": 26.7, \"tmax\": 31.1, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"5/15/82\", \"srad\": 23.9, \"tmax\": 32.2, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"5/16/82\", \"srad\": 22.8, \"tmax\": 33.3, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"5/17/82\", \"srad\": 22.7, \"tmax\": 32.8, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"5/18/82\", \"srad\": 21.5, \"tmax\": 31.1, \"tmin\": 16.7, \"rain\": 0.0}, {\"w_date\": \"5/19/82\", \"srad\": 22.4, \"tmax\": 30.6, \"tmin\": 16.7, \"rain\": 0.0}, {\"w_date\": \"5/20/82\", \"srad\": 22.5, \"tmax\": 31.7, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"5/21/82\", \"srad\": 22.6, \"tmax\": 32.2, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"5/22/82\", \"srad\": 17.4, \"tmax\": 32.8, \"tmin\": 16.7, \"rain\": 2.0}, {\"w_date\": \"5/23/82\", \"srad\": 13.2, \"tmax\": 33.3, \"tmin\": 20.0, \"rain\": 35.1}, {\"w_date\": \"5/24/82\", \"srad\": 21.5, \"tmax\": 32.8, \"tmin\": 18.9, \"rain\": 3.6}, {\"w_date\": \"5/25/82\", \"srad\": 20.3, \"tmax\": 32.8, \"tmin\": 20.0, \"rain\": 2.5}, {\"w_date\": \"5/26/82\", \"srad\": 15.0, \"tmax\": 31.7, \"tmin\": 20.6, \"rain\": 1.0}, {\"w_date\": \"5/27/82\", \"srad\": 18.2, \"tmax\": 32.2, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"5/28/82\", \"srad\": 22.1, \"tmax\": 33.3, \"tmin\": 20.6, \"rain\": 4.3}, {\"w_date\": \"5/29/82\", \"srad\": 19.3, \"tmax\": 34.4, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"5/30/82\", \"srad\": 7.0, \"tmax\": 32.2, \"tmin\": 22.8, \"rain\": 5.6}, {\"w_date\": \"5/31/82\", \"srad\": 11.8, \"tmax\": 30.0, \"tmin\": 20.6, \"rain\": 23.9}, {\"w_date\": \"6/1/82\", \"srad\": 20.0, \"tmax\": 31.1, \"tmin\": 21.1, \"rain\": 15.2}, {\"w_date\": \"6/2/82\", \"srad\": 24.9, \"tmax\": 33.3, \"tmin\": 20.0, \"rain\": 0.0}, {\"w_date\": \"6/3/82\", \"srad\": 15.8, \"tmax\": 33.3, \"tmin\": 22.8, \"rain\": 1.8}, {\"w_date\": \"6/4/82\", \"srad\": 21.6, \"tmax\": 33.3, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"6/5/82\", \"srad\": 23.8, \"tmax\": 33.9, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"6/6/82\", \"srad\": 26.5, \"tmax\": 34.4, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"6/7/82\", \"srad\": 26.9, \"tmax\": 33.9, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"6/8/82\", \"srad\": 26.3, \"tmax\": 34.4, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"6/9/82\", \"srad\": 24.4, \"tmax\": 34.4, \"tmin\": 19.4, \"rain\": 0.0}, {\"w_date\": \"6/10/82\", \"srad\": 25.5, \"tmax\": 34.4, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"6/11/82\", \"srad\": 23.6, \"tmax\": 35.0, \"tmin\": 19.4, \"rain\": 0.0}, {\"w_date\": \"6/12/82\", \"srad\": 15.2, \"tmax\": 31.1, \"tmin\": 20.0, \"rain\": 50.8}, {\"w_date\": \"6/13/82\", \"srad\": 21.1, \"tmax\": 33.3, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"6/14/82\", \"srad\": 25.5, \"tmax\": 33.3, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"6/15/82\", \"srad\": 23.2, \"tmax\": 33.9, \"tmin\": 20.6, \"rain\": 0.0}, {\"w_date\": \"6/16/82\", \"srad\": 25.5, \"tmax\": 33.9, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"6/17/82\", \"srad\": 6.0, \"tmax\": 31.1, \"tmin\": 21.1, \"rain\": 8.1}, {\"w_date\": \"6/18/82\", \"srad\": 13.5, \"tmax\": 27.8, \"tmin\": 21.1, \"rain\": 70.1}, {\"w_date\": \"6/19/82\", \"srad\": 24.3, \"tmax\": 33.3, \"tmin\": 20.0, \"rain\": 0.0}, {\"w_date\": \"6/20/82\", \"srad\": 25.9, \"tmax\": 33.9, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"6/21/82\", \"srad\": 20.9, \"tmax\": 32.8, \"tmin\": 20.0, \"rain\": 3.3}, {\"w_date\": \"6/22/82\", \"srad\": 18.5, \"tmax\": 28.3, \"tmin\": 23.3, \"rain\": 2.0}, {\"w_date\": \"6/23/82\", \"srad\": 11.7, \"tmax\": 27.8, \"tmin\": 21.7, \"rain\": 26.9}, {\"w_date\": \"6/24/82\", \"srad\": 18.6, \"tmax\": 30.6, \"tmin\": 22.2, \"rain\": 6.4}, {\"w_date\": \"6/25/82\", \"srad\": 22.9, \"tmax\": 32.2, \"tmin\": 19.4, \"rain\": 10.9}, {\"w_date\": \"6/26/82\", \"srad\": 10.5, \"tmax\": 31.7, \"tmin\": 21.7, \"rain\": 23.4}, {\"w_date\": \"6/27/82\", \"srad\": 24.6, \"tmax\": 32.2, \"tmin\": 21.1, \"rain\": 1.8}, {\"w_date\": \"6/28/82\", \"srad\": 24.6, \"tmax\": 32.8, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"6/29/82\", \"srad\": 24.8, \"tmax\": 33.3, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"6/30/82\", \"srad\": 16.0, \"tmax\": 32.8, \"tmin\": 22.8, \"rain\": 1.3}, {\"w_date\": \"7/1/82\", \"srad\": 23.7, \"tmax\": 33.9, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"7/2/82\", \"srad\": 18.7, \"tmax\": 33.9, \"tmin\": 23.9, \"rain\": 0.0}, {\"w_date\": \"7/3/82\", \"srad\": 24.6, \"tmax\": 35.0, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"7/4/82\", \"srad\": 21.1, \"tmax\": 35.0, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"7/5/82\", \"srad\": 23.7, \"tmax\": 34.4, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"7/6/82\", \"srad\": 19.0, \"tmax\": 32.2, \"tmin\": 20.6, \"rain\": 4.1}, {\"w_date\": \"7/7/82\", \"srad\": 16.0, \"tmax\": 31.7, \"tmin\": 21.1, \"rain\": 4.8}, {\"w_date\": \"7/8/82\", \"srad\": 19.8, \"tmax\": 32.2, \"tmin\": 21.1, \"rain\": 1.3}, {\"w_date\": \"7/9/82\", \"srad\": 13.7, \"tmax\": 32.2, \"tmin\": 22.2, \"rain\": 7.1}, {\"w_date\": \"7/10/82\", \"srad\": 17.4, \"tmax\": 32.8, \"tmin\": 21.7, \"rain\": 1.3}, {\"w_date\": \"7/11/82\", \"srad\": 18.3, \"tmax\": 32.8, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"7/12/82\", \"srad\": 21.0, \"tmax\": 32.8, \"tmin\": 21.1, \"rain\": 2.3}, {\"w_date\": \"7/13/82\", \"srad\": 23.2, \"tmax\": 33.9, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"7/14/82\", \"srad\": 25.4, \"tmax\": 34.4, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"7/15/82\", \"srad\": 18.3, \"tmax\": 33.9, \"tmin\": 23.3, \"rain\": 55.9}, {\"w_date\": \"7/16/82\", \"srad\": 14.9, \"tmax\": 32.8, \"tmin\": 22.8, \"rain\": 1.0}, {\"w_date\": \"7/17/82\", \"srad\": 13.6, \"tmax\": 31.1, \"tmin\": 22.8, \"rain\": 0.0}, {\"w_date\": \"7/18/82\", \"srad\": 14.3, \"tmax\": 32.2, \"tmin\": 22.8, \"rain\": 2.5}, {\"w_date\": \"7/19/82\", \"srad\": 17.0, \"tmax\": 32.2, \"tmin\": 22.2, \"rain\": 30.0}, {\"w_date\": \"7/20/82\", \"srad\": 10.9, \"tmax\": 31.1, \"tmin\": 21.7, \"rain\": 20.8}, {\"w_date\": \"7/21/82\", \"srad\": 13.2, \"tmax\": 31.1, \"tmin\": 21.1, \"rain\": 1.5}, {\"w_date\": \"7/22/82\", \"srad\": 17.9, \"tmax\": 31.1, \"tmin\": 22.2, \"rain\": 0.3}, {\"w_date\": \"7/23/82\", \"srad\": 16.1, \"tmax\": 32.8, \"tmin\": 22.8, \"rain\": 16.0}, {\"w_date\": \"7/24/82\", \"srad\": 18.4, \"tmax\": 32.2, \"tmin\": 21.7, \"rain\": 4.1}, {\"w_date\": \"7/25/82\", \"srad\": 19.7, \"tmax\": 33.3, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"7/26/82\", \"srad\": 19.4, \"tmax\": 33.9, \"tmin\": 22.2, \"rain\": 0.8}, {\"w_date\": \"7/27/82\", \"srad\": 5.2, \"tmax\": 34.4, \"tmin\": 23.3, \"rain\": 10.7}, {\"w_date\": \"7/28/82\", \"srad\": 9.5, \"tmax\": 27.8, \"tmin\": 22.8, \"rain\": 7.4}, {\"w_date\": \"7/29/82\", \"srad\": 14.9, \"tmax\": 31.7, \"tmin\": 22.8, \"rain\": 0.0}, {\"w_date\": \"7/30/82\", \"srad\": 19.4, \"tmax\": 32.8, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"7/31/82\", \"srad\": 20.1, \"tmax\": 33.9, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"8/1/82\", \"srad\": 23.5, \"tmax\": 33.9, \"tmin\": 23.3, \"rain\": 0.0}, {\"w_date\": \"8/2/82\", \"srad\": 9.6, \"tmax\": 32.8, \"tmin\": 22.8, \"rain\": 13.0}, {\"w_date\": \"8/3/82\", \"srad\": 22.5, \"tmax\": 32.2, \"tmin\": 22.8, \"rain\": 20.1}, {\"w_date\": \"8/4/82\", \"srad\": 21.0, \"tmax\": 32.2, \"tmin\": 20.6, \"rain\": 0.0}, {\"w_date\": \"8/5/82\", \"srad\": 14.7, \"tmax\": 32.2, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"8/6/82\", \"srad\": 18.5, \"tmax\": 32.2, \"tmin\": 21.1, \"rain\": 4.1}, {\"w_date\": \"8/7/82\", \"srad\": 23.0, \"tmax\": 33.9, \"tmin\": 21.7, \"rain\": 0.3}, {\"w_date\": \"8/8/82\", \"srad\": 21.0, \"tmax\": 33.9, \"tmin\": 26.1, \"rain\": 0.0}, {\"w_date\": \"8/9/82\", \"srad\": 17.1, \"tmax\": 32.8, \"tmin\": 22.8, \"rain\": 1.3}, {\"w_date\": \"8/10/82\", \"srad\": 16.6, \"tmax\": 33.3, \"tmin\": 23.3, \"rain\": 0.0}, {\"w_date\": \"8/11/82\", \"srad\": 25.6, \"tmax\": 33.3, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"8/12/82\", \"srad\": 19.0, \"tmax\": 33.3, \"tmin\": 20.6, \"rain\": 0.3}, {\"w_date\": \"8/13/82\", \"srad\": 16.1, \"tmax\": 32.2, \"tmin\": 20.6, \"rain\": 0.8}, {\"w_date\": \"8/14/82\", \"srad\": 16.4, \"tmax\": 31.7, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"8/15/82\", \"srad\": 20.3, \"tmax\": 35.0, \"tmin\": 20.6, \"rain\": 0.8}, {\"w_date\": \"8/16/82\", \"srad\": 20.3, \"tmax\": 33.9, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"8/17/82\", \"srad\": 19.0, \"tmax\": 32.2, \"tmin\": 22.2, \"rain\": 13.7}, {\"w_date\": \"8/18/82\", \"srad\": 16.6, \"tmax\": 26.7, \"tmin\": 20.6, \"rain\": 3.3}, {\"w_date\": \"8/19/82\", \"srad\": 7.9, \"tmax\": 28.3, \"tmin\": 21.7, \"rain\": 20.3}, {\"w_date\": \"8/20/82\", \"srad\": 20.4, \"tmax\": 32.2, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"8/21/82\", \"srad\": 11.8, \"tmax\": 32.2, \"tmin\": 21.7, \"rain\": 5.1}, {\"w_date\": \"8/22/82\", \"srad\": 16.6, \"tmax\": 32.2, \"tmin\": 22.2, \"rain\": 1.3}, {\"w_date\": \"8/23/82\", \"srad\": 15.2, \"tmax\": 31.7, \"tmin\": 22.2, \"rain\": 64.5}, {\"w_date\": \"8/24/82\", \"srad\": 18.9, \"tmax\": 33.3, \"tmin\": 22.8, \"rain\": 1.3}, {\"w_date\": \"8/25/82\", \"srad\": 18.8, \"tmax\": 33.3, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"8/26/82\", \"srad\": 21.4, \"tmax\": 35.6, \"tmin\": 23.9, \"rain\": 0.0}, {\"w_date\": \"8/27/82\", \"srad\": 11.0, \"tmax\": 35.6, \"tmin\": 22.8, \"rain\": 3.6}, {\"w_date\": \"8/28/82\", \"srad\": 18.6, \"tmax\": 35.0, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"8/29/82\", \"srad\": 15.8, \"tmax\": 35.0, \"tmin\": 22.2, \"rain\": 3.6}, {\"w_date\": \"8/30/82\", \"srad\": 22.5, \"tmax\": 31.1, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"8/31/82\", \"srad\": 21.1, \"tmax\": 32.2, \"tmin\": 20.6, \"rain\": 0.0}, {\"w_date\": \"9/1/82\", \"srad\": 13.2, \"tmax\": 32.2, \"tmin\": 20.6, \"rain\": 0.0}, {\"w_date\": \"9/2/82\", \"srad\": 15.6, \"tmax\": 33.3, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"9/3/82\", \"srad\": 16.9, \"tmax\": 34.4, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"9/4/82\", \"srad\": 17.0, \"tmax\": 34.4, \"tmin\": 22.2, \"rain\": 0.0}, {\"w_date\": \"9/5/82\", \"srad\": 12.2, \"tmax\": 33.9, \"tmin\": 21.7, \"rain\": 11.9}, {\"w_date\": \"9/6/82\", \"srad\": 9.0, \"tmax\": 34.4, \"tmin\": 21.1, \"rain\": 2.0}, {\"w_date\": \"9/7/82\", \"srad\": 11.1, \"tmax\": 30.0, \"tmin\": 20.0, \"rain\": 0.0}, {\"w_date\": \"9/8/82\", \"srad\": 9.6, \"tmax\": 30.6, \"tmin\": 20.6, \"rain\": 19.0}, {\"w_date\": \"9/9/82\", \"srad\": 7.1, \"tmax\": 30.0, \"tmin\": 21.1, \"rain\": 39.9}, {\"w_date\": \"9/10/82\", \"srad\": 5.5, \"tmax\": 30.0, \"tmin\": 21.1, \"rain\": 24.1}, {\"w_date\": \"9/11/82\", \"srad\": 13.4, \"tmax\": 32.8, \"tmin\": 21.7, \"rain\": 7.6}, {\"w_date\": \"9/12/82\", \"srad\": 14.6, \"tmax\": 33.3, \"tmin\": 22.8, \"rain\": 0.0}, {\"w_date\": \"9/13/82\", \"srad\": 14.5, \"tmax\": 33.3, \"tmin\": 22.8, \"rain\": 2.5}, {\"w_date\": \"9/14/82\", \"srad\": 12.3, \"tmax\": 32.8, \"tmin\": 22.2, \"rain\": 1.8}, {\"w_date\": \"9/15/82\", \"srad\": 13.0, \"tmax\": 32.2, \"tmin\": 19.4, \"rain\": 0.0}, {\"w_date\": \"9/16/82\", \"srad\": 12.6, \"tmax\": 32.8, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"9/17/82\", \"srad\": 13.9, \"tmax\": 32.8, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"9/18/82\", \"srad\": 12.2, \"tmax\": 32.8, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"9/19/82\", \"srad\": 12.2, \"tmax\": 32.8, \"tmin\": 21.1, \"rain\": 15.7}, {\"w_date\": \"9/20/82\", \"srad\": 11.1, \"tmax\": 31.7, \"tmin\": 21.1, \"rain\": 7.4}, {\"w_date\": \"9/21/82\", \"srad\": 5.2, \"tmax\": 29.4, \"tmin\": 21.1, \"rain\": 12.7}, {\"w_date\": \"9/22/82\", \"srad\": 11.4, \"tmax\": 27.8, \"tmin\": 19.4, \"rain\": 0.0}, {\"w_date\": \"9/23/82\", \"srad\": 8.9, \"tmax\": 27.2, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"9/24/82\", \"srad\": 11.2, \"tmax\": 30.0, \"tmin\": 17.8, \"rain\": 0.0}, {\"w_date\": \"9/25/82\", \"srad\": 5.0, \"tmax\": 28.9, \"tmin\": 20.0, \"rain\": 8.9}, {\"w_date\": \"9/26/82\", \"srad\": 7.6, \"tmax\": 25.0, \"tmin\": 16.7, \"rain\": 24.1}, {\"w_date\": \"9/27/82\", \"srad\": 15.6, \"tmax\": 28.9, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"9/28/82\", \"srad\": 13.9, \"tmax\": 29.4, \"tmin\": 16.7, \"rain\": 0.0}, {\"w_date\": \"9/29/82\", \"srad\": 12.1, \"tmax\": 29.4, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"9/30/82\", \"srad\": 6.7, \"tmax\": 28.9, \"tmin\": 21.1, \"rain\": 2.5}, {\"w_date\": \"10/1/82\", \"srad\": 8.0, \"tmax\": 26.7, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"10/2/82\", \"srad\": 10.0, \"tmax\": 28.3, \"tmin\": 19.4, \"rain\": 0.0}, {\"w_date\": \"10/3/82\", \"srad\": 19.0, \"tmax\": 30.0, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"10/4/82\", \"srad\": 16.0, \"tmax\": 31.7, \"tmin\": 20.6, \"rain\": 0.0}, {\"w_date\": \"10/5/82\", \"srad\": 9.0, \"tmax\": 31.1, \"tmin\": 22.8, \"rain\": 0.0}, {\"w_date\": \"10/6/82\", \"srad\": 16.0, \"tmax\": 31.1, \"tmin\": 21.7, \"rain\": 13.0}, {\"w_date\": \"10/7/82\", \"srad\": 13.0, \"tmax\": 30.0, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"10/8/82\", \"srad\": 17.0, \"tmax\": 31.7, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"10/9/82\", \"srad\": 15.0, \"tmax\": 31.7, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"10/10/82\", \"srad\": 15.0, \"tmax\": 32.8, \"tmin\": 20.0, \"rain\": 0.0}, {\"w_date\": \"10/11/82\", \"srad\": 13.0, \"tmax\": 32.2, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"10/12/82\", \"srad\": 11.0, \"tmax\": 29.4, \"tmin\": 21.7, \"rain\": 0.0}, {\"w_date\": \"10/13/82\", \"srad\": 14.0, \"tmax\": 30.6, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"10/14/82\", \"srad\": 10.0, \"tmax\": 28.9, \"tmin\": 16.7, \"rain\": 0.0}, {\"w_date\": \"10/15/82\", \"srad\": 15.0, \"tmax\": 27.8, \"tmin\": 11.7, \"rain\": 0.0}, {\"w_date\": \"10/16/82\", \"srad\": 9.0, \"tmax\": 26.1, \"tmin\": 10.0, \"rain\": 0.0}, {\"w_date\": \"10/17/82\", \"srad\": 14.0, \"tmax\": 26.7, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"10/18/82\", \"srad\": 6.0, \"tmax\": 25.6, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"10/19/82\", \"srad\": 14.0, \"tmax\": 28.3, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"10/20/82\", \"srad\": 13.0, \"tmax\": 30.0, \"tmin\": 16.7, \"rain\": 0.0}, {\"w_date\": \"10/21/82\", \"srad\": 14.0, \"tmax\": 30.0, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"10/22/82\", \"srad\": 11.0, \"tmax\": 29.4, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"10/23/82\", \"srad\": 2.0, \"tmax\": 27.8, \"tmin\": 14.4, \"rain\": 16.5}, {\"w_date\": \"10/24/82\", \"srad\": 14.0, \"tmax\": 18.9, \"tmin\": 8.9, \"rain\": 1.3}, {\"w_date\": \"10/25/82\", \"srad\": 16.0, \"tmax\": 20.6, \"tmin\": 5.6, \"rain\": 0.0}, {\"w_date\": \"10/26/82\", \"srad\": 15.0, \"tmax\": 22.8, \"tmin\": 6.1, \"rain\": 0.0}, {\"w_date\": \"10/27/82\", \"srad\": 15.0, \"tmax\": 23.9, \"tmin\": 7.2, \"rain\": 0.0}, {\"w_date\": \"10/28/82\", \"srad\": 14.0, \"tmax\": 25.6, \"tmin\": 11.7, \"rain\": 0.0}, {\"w_date\": \"10/29/82\", \"srad\": 14.0, \"tmax\": 27.8, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"10/30/82\", \"srad\": 9.0, \"tmax\": 27.8, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"10/31/82\", \"srad\": 7.0, \"tmax\": 27.8, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"11/1/82\", \"srad\": 11.0, \"tmax\": 29.4, \"tmin\": 20.0, \"rain\": 0.3}, {\"w_date\": \"11/2/82\", \"srad\": 9.0, \"tmax\": 29.4, \"tmin\": 20.6, \"rain\": 0.0}, {\"w_date\": \"11/3/82\", \"srad\": 9.0, \"tmax\": 28.9, \"tmin\": 19.4, \"rain\": 21.6}, {\"w_date\": \"11/4/82\", \"srad\": 9.0, \"tmax\": 26.7, \"tmin\": 17.8, \"rain\": 16.5}, {\"w_date\": \"11/5/82\", \"srad\": 10.0, \"tmax\": 18.3, \"tmin\": 7.2, \"rain\": 0.0}, {\"w_date\": \"11/6/82\", \"srad\": 12.0, \"tmax\": 18.9, \"tmin\": 2.2, \"rain\": 0.0}, {\"w_date\": \"11/7/82\", \"srad\": 12.0, \"tmax\": 21.1, \"tmin\": 7.8, \"rain\": 0.0}, {\"w_date\": \"11/8/82\", \"srad\": 13.0, \"tmax\": 24.4, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"11/9/82\", \"srad\": 11.0, \"tmax\": 25.0, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"11/10/82\", \"srad\": 12.0, \"tmax\": 25.6, \"tmin\": 10.6, \"rain\": 0.0}, {\"w_date\": \"11/11/82\", \"srad\": 8.0, \"tmax\": 26.7, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"11/12/82\", \"srad\": 7.0, \"tmax\": 27.8, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"11/13/82\", \"srad\": 11.0, \"tmax\": 31.1, \"tmin\": 13.3, \"rain\": 3.6}, {\"w_date\": \"11/14/82\", \"srad\": 12.0, \"tmax\": 25.0, \"tmin\": 6.7, \"rain\": 0.0}, {\"w_date\": \"11/15/82\", \"srad\": 10.0, \"tmax\": 23.9, \"tmin\": 15.0, \"rain\": 0.0}, {\"w_date\": \"11/16/82\", \"srad\": 11.0, \"tmax\": 23.9, \"tmin\": 9.4, \"rain\": 0.0}, {\"w_date\": \"11/17/82\", \"srad\": 13.0, \"tmax\": 26.7, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"11/18/82\", \"srad\": 13.0, \"tmax\": 28.3, \"tmin\": 17.8, \"rain\": 0.0}, {\"w_date\": \"11/19/82\", \"srad\": 11.0, \"tmax\": 26.7, \"tmin\": 17.8, \"rain\": 0.0}, {\"w_date\": \"11/20/82\", \"srad\": 12.0, \"tmax\": 25.6, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"11/21/82\", \"srad\": 14.0, \"tmax\": 27.2, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"11/22/82\", \"srad\": 13.0, \"tmax\": 26.1, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"11/23/82\", \"srad\": 13.0, \"tmax\": 27.2, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"11/24/82\", \"srad\": 13.0, \"tmax\": 27.8, \"tmin\": 12.2, \"rain\": 0.0}, {\"w_date\": \"11/25/82\", \"srad\": 15.0, \"tmax\": 27.2, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"11/26/82\", \"srad\": 12.0, \"tmax\": 26.7, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"11/27/82\", \"srad\": 3.0, \"tmax\": 27.8, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"11/28/82\", \"srad\": 10.0, \"tmax\": 28.9, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"11/29/82\", \"srad\": 10.0, \"tmax\": 27.2, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"11/30/82\", \"srad\": 9.0, \"tmax\": 29.4, \"tmin\": 19.4, \"rain\": 0.0}, {\"w_date\": \"12/1/82\", \"srad\": 8.0, \"tmax\": 28.9, \"tmin\": 20.6, \"rain\": 0.0}, {\"w_date\": \"12/2/82\", \"srad\": 10.0, \"tmax\": 29.4, \"tmin\": 21.1, \"rain\": 0.0}, {\"w_date\": \"12/3/82\", \"srad\": 11.0, \"tmax\": 29.4, \"tmin\": 18.9, \"rain\": 0.0}, {\"w_date\": \"12/4/82\", \"srad\": 11.0, \"tmax\": 28.9, \"tmin\": 18.3, \"rain\": 0.0}, {\"w_date\": \"12/5/82\", \"srad\": 8.0, \"tmax\": 28.9, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"12/6/82\", \"srad\": 2.0, \"tmax\": 27.2, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"12/7/82\", \"srad\": 5.0, \"tmax\": 21.7, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"12/8/82\", \"srad\": 2.0, \"tmax\": 20.0, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"12/9/82\", \"srad\": 7.0, \"tmax\": 23.3, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"12/10/82\", \"srad\": 2.0, \"tmax\": 22.2, \"tmin\": 15.6, \"rain\": 3.3}, {\"w_date\": \"12/11/82\", \"srad\": 5.0, \"tmax\": 26.1, \"tmin\": 17.2, \"rain\": 0.0}, {\"w_date\": \"12/12/82\", \"srad\": 2.0, \"tmax\": 23.9, \"tmin\": 10.0, \"rain\": 19.0}, {\"w_date\": \"12/13/82\", \"srad\": 11.0, \"tmax\": 12.8, \"tmin\": 2.8, \"rain\": 0.0}, {\"w_date\": \"12/14/82\", \"srad\": 6.0, \"tmax\": 18.9, \"tmin\": 3.3, \"rain\": 0.0}, {\"w_date\": \"12/15/82\", \"srad\": 8.0, \"tmax\": 25.0, \"tmin\": 12.8, \"rain\": 0.0}, {\"w_date\": \"12/16/82\", \"srad\": 6.0, \"tmax\": 23.9, \"tmin\": 15.6, \"rain\": 13.7}, {\"w_date\": \"12/17/82\", \"srad\": 11.0, \"tmax\": 20.0, \"tmin\": 2.8, \"rain\": 0.0}, {\"w_date\": \"12/18/82\", \"srad\": 11.0, \"tmax\": 15.6, \"tmin\": -1.1, \"rain\": 0.0}, {\"w_date\": \"12/19/82\", \"srad\": 9.0, \"tmax\": 20.0, \"tmin\": -1.1, \"rain\": 0.0}, {\"w_date\": \"12/20/82\", \"srad\": 11.0, \"tmax\": 18.9, \"tmin\": 1.1, \"rain\": 0.0}, {\"w_date\": \"12/21/82\", \"srad\": 11.0, \"tmax\": 19.4, \"tmin\": 0.6, \"rain\": 0.0}, {\"w_date\": \"12/22/82\", \"srad\": 11.0, \"tmax\": 19.4, \"tmin\": 0.6, \"rain\": 0.0}, {\"w_date\": \"12/23/82\", \"srad\": 9.0, \"tmax\": 23.3, \"tmin\": 4.4, \"rain\": 0.0}, {\"w_date\": \"12/24/82\", \"srad\": 9.0, \"tmax\": 26.7, \"tmin\": 13.3, \"rain\": 0.0}, {\"w_date\": \"12/25/82\", \"srad\": 11.0, \"tmax\": 27.2, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"12/26/82\", \"srad\": 10.0, \"tmax\": 27.2, \"tmin\": 15.6, \"rain\": 0.0}, {\"w_date\": \"12/27/82\", \"srad\": 6.0, \"tmax\": 25.0, \"tmin\": 16.7, \"rain\": 0.0}, {\"w_date\": \"12/28/82\", \"srad\": 8.0, \"tmax\": 26.7, \"tmin\": 16.1, \"rain\": 0.0}, {\"w_date\": \"12/29/82\", \"srad\": 7.0, \"tmax\": 26.7, \"tmin\": 14.4, \"rain\": 0.0}, {\"w_date\": \"12/30/82\", \"srad\": 2.0, \"tmax\": 23.3, \"tmin\": 13.9, \"rain\": 0.5}, {\"w_date\": \"12/31/82\", \"srad\": 2.0, \"tmax\": 16.7, \"tmin\": 12.8, \"rain\": 0.8}], \"tamp\": 7.5}, \"soil\": {\"soil_id\": \"IBMZ910014\", \"sl_system\": \"SCS\", \"soillong\": -82.37, \"name\": \"Millhopper Fine Sand\", \"SoilLayer\": [{\"silt\": 11.8, \"sldul\": 0.086, \"slll\": 0.02, \"clay\": 0.9, \"slbdm\": 7.4, \"slcf\": 2.0, \"sloc\": 5.3, \"slphw\": 1.36, \"slsat\": 0.23, \"sllb\": 5.0}, {\"silt\": 11.8, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 0.9, \"slbdm\": 7.4, \"slcf\": 1.0, \"sloc\": 5.4, \"slphw\": 1.4, \"slsat\": 0.23, \"sllb\": 15.0}, {\"silt\": 6.4, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 4.6, \"slbdm\": 15.8, \"slcf\": 1.0, \"sloc\": 5.7, \"slphw\": 1.46, \"slsat\": 0.23, \"sllb\": 30.0}, {\"silt\": 5.4, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 5.8, \"slbdm\": 28.0, \"slcf\": 0.5, \"sloc\": 5.8, \"slphw\": 1.46, \"slsat\": 0.23, \"sllb\": 45.0}, {\"silt\": 5.4, \"sldul\": 0.086, \"slll\": 0.023, \"clay\": 5.8, \"slbdm\": 28.0, \"slcf\": 0.5, \"sloc\": 5.8, \"slphw\": 1.47, \"slsat\": 0.23, \"sllb\": 60.0}, {\"silt\": 4.2, \"sldul\": 0.076, \"slll\": 0.021, \"clay\": 9.6, \"slbdm\": 27.6, \"slcf\": 0.1, \"sloc\": 5.9, \"slphw\": 1.43, \"slsat\": 0.23, \"sllb\": 90.0}, {\"silt\": 4.2, \"sldul\": 0.076, \"slll\": 0.02, \"clay\": 9.6, \"slbdm\": 17.5, \"slcf\": 0.1, \"sloc\": 5.9, \"slphw\": 1.48, \"slsat\": 0.23, \"sllb\": 120.0}, {\"silt\": 3.6, \"sldul\": 0.13, \"slll\": 0.027, \"clay\": 8.3, \"slbdm\": 0.3, \"slcf\": 0.04, \"sloc\": 5.9, \"slphw\": 1.57, \"slsat\": 0.23, \"sllb\": 150.0}, {\"silt\": 3.6, \"sldul\": 0.258, \"slll\": 0.07, \"clay\": 8.3, \"slbdm\": 0.1, \"slcf\": 0.24, \"sloc\": 5.9, \"slphw\": 1.79, \"slsat\": 0.36, \"sllb\": 180.0}, {\"silt\": \"\", \"sldul\": \"\", \"slll\": \"\", \"clay\": \"\", \"slbdm\": \"\", \"slcf\": \"\", \"sloc\": \"\", \"slphw\": \"\", \"slsat\": \"\", \"sllb\": \"\"}], \"sldr\": 0.65, \"salb\": 0.18, \"slnf\": 1.0, \"soillat\": 29.63, \"classifcation\": \"LS\"}}";
    
    // Default value for each type of value (R: real number; C: String; I: Integer; D: Date)
    private static String defValR = "0.00";
    private static String defValC = "";
    private static String defValI = "0";
    private static String defValD = "20110101";
    
    // Define necessary id for the experiment data
    private static String[] necessaryData = {"pdate", "plpop,plpoe", "plrs", "cr", "cul_id", "wst_insi", "soil_id"};

    /**
     * DSSAT Experiment Data Input method
     * 
     * @author Meng Zhang
     * @version 0.1
     * @param arg0  file name(?)
     * @return data holder object
     */
    @Override
    public AdvancedHashMap<String, Object> readFile(String arg0) {
        return null;
    }

    /**
     * DSSAT Experiment Data Output method
     * 
     * @author Meng Zhang
     * @version 1.0
     * @param arg0  file name(?)
     * @param result  data holder object
     */
    @Override
    public void writeFile(String arg0, AdvancedHashMap result) {

        // Initial variables
        JSONAdapter adapter = new JSONAdapter();    // JSON Adapter
        AdvancedHashMap<String, Object> data;       // Data holder for section data
        BufferedWriter br;                          // output object
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
                    System.out.println("Incompleted record because missing data : [" + necessaryData[i] + "]");
                    return;
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
                fileName = fileName.substring(0, fileName.length()-2) + "." + fileName.substring(fileName.length()-2) + "X";
            }
            br = new BufferedWriter(new FileWriter(new File(fileName)));
            data = result;

            // Output XFile
            // EXP.DETAILS Section
            br.write(String.format("*EXP.DETAILS: %1$-10s %2$-60s\r\n", exName, result.getOr("local_name", defValC).toString()));

            // GENERAL Section
            br.write("*GENERAL\r\n");
            // People
            br.write(String.format("@PEOPLE\r\n %1$-75s\r\n", result.getOr("people", defValC).toString()));
            // Address
            br.write(String.format("@ADDRESS\r\n %1$-75s\r\n", result.getOr("address", defValC).toString()));
            // Site
            br.write(String.format("@SITE\r\n %1$-75s\r\n", result.getOr("site", defValC).toString()));
            // Plot Info
            if (result.containsKey("plot_info")) {
                br.write("@ PAREA  PRNO  PLEN  PLDR  PLSP  PLAY HAREA  HRNO  HLEN  HARM.........\r\n");
                br.write(String.format(" %1$6s %2$5s %3$5s %4$5s %5$5s %6$-5s %7$5s %8$5s %9$5s %10$-15s\r\n",
                        result.getOr("parea", defValR).toString(),
                        result.getOr("prno", defValI).toString(),
                        result.getOr("plen", defValR).toString(),
                        result.getOr("pldr", defValI).toString(),
                        result.getOr("plsp", defValI).toString(),
                        result.getOr("play", defValC).toString(),
                        result.getOr("pltha", defValR).toString(),
                        result.getOr("hrno", defValI).toString(),
                        result.getOr("hlen", defValR).toString(),
                        result.getOr("plthm", defValC).toString()));
            }
            // Notes
            if (result.containsKey("notes")) {
                String notes = result.getOr("notes", defValC).toString();
                
                // If notes contain newline code, then write directly
                if (notes.indexOf("\r") >= 0 || notes.indexOf("\n") >= 0 ) {
                    br.write(String.format(" %1$-75s\r\n", result.getOr("notes", defValC).toString()));
                }
                // Otherwise, add newline for every 75-bits charactors
                else {
                    while (notes.length() > 75) {
                        br.write(" " + notes.substring(0,75) + "\r\n");
                        notes = notes.substring(75);
                    }
                    br.write(" " + notes + "\r\n");
                }
            }
            br.write("\r\n");

            // TREATMENT Section
            br.write("*TREATMENTS                        -------------FACTOR LEVELS------------\r\n");
            br.write("@N R O C TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM\r\n");
            
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
                br.write(String.format("%1$2s %2$1s %3$1s %4$1s %5$-25s %6$2s %7$2s %8$2s %9$2s %10$2s %11$2s %12$2s %13$2s %14$2s %15$2s %16$2s %17$2s %18$2s\r\n",
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
            br.write("\r\n");
            
            // Check if Necessary Section exists
            if (cuArr.isEmpty()) {
                //throw new Exception ("");
                System.out.println("Trhee is no cultivar data in the experiment.");
            } else if (flArr.isEmpty()) {
                //throw new Exception ("");
                System.out.println("Trhee is no field data in the experiment.");
            } else if (mpArr.isEmpty()) {
                //throw new Exception ("");
                System.out.println("Trhee is no plainting data in the experiment.");
            }

            // CULTIVARS Section
            for (int idx = 0; idx < cuArr.size(); idx++) {  
            //if (!cuArr.isEmpty()) {
                br.write("*CULTIVARS\r\n");
                br.write("@C CR INGENO CNAME\r\n");

    //            secRecords = (ArrayList) result.getOr("cultivars", new ArrayList());
    //            for (int i = 0; i < secRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) secRecords.get(i));
                    br.write(String.format("%1$2s %2$-2s %3$-6s %4$-16s\r\n",
                            idx+1, //data.getOr("ge", defValI).toString(),
                            data.getOr("cr", defValC).toString(),
                            data.getOr("cul_id", defValC).toString(),
                            data.getOr("cul_name", defValC).toString()));

                }
            }
            br.write("\r\n");
            
            // FIELDS Section
            for (int idx = 0; idx < flArr.size(); idx++) {  
            //if (!flArr.isEmpty()) {
                br.write("*FIELDS\r\n");
                br.write("@L ID_FIELD WSTA....  FLSA  FLOB  FLDT  FLDD  FLDS  FLST SLTX  SLDP  ID_SOIL    FLNAME\r\n");
                eventPart2 = new StringBuilder();
                eventPart2.append("@L ...........XCRD ...........YCRD .....ELEV .............AREA .SLEN .FLWR .SLAS FLHST FHDUR\r\n");

    //            secRecords = (ArrayList) result.getOr("fields", new ArrayList());
    //            for (int i = 0; i < secRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) secRecords.get(i));
                    br.write(String.format("%1$2s %2$-8s %3$-8s %4$-5s %5$5s %6$-5s %7$5s %8$5s %9$-5s %10$-5s %11$5s %12$-10s %13$s\r\n",
                            idx+1, //data.getOr("fl", defValI).toString(),
                            data.getOr("id_field", defValC).toString(),
                            data.getOr("wst_insi", defValC).toString(),
                            data.getOr("flsl", defValC).toString(),
                            data.getOr("flob", defValR).toString(),
                            data.getOr("fl_drntype", defValC).toString(),
                            data.getOr("fldrd", defValR).toString(),
                            data.getOr("fldrs", defValR).toString(),
                            data.getOr("flst", defValC).toString(),
                            data.getOr("sltx", defValC).toString(),
                            data.getOr("sldp", defValR).toString(),
                            data.getOr("soil_id", defValC).toString(),
                            data.getOr("fl_name", defValC).toString()));

                    eventPart2.append(String.format("%1$2s %2$15s %3$15s %4$9s %5$17s %6$5s %7$5s %8$5s %9$-5s %10$5s\r\n",
                            idx+1, //data.getOr("fl", defValI).toString(),
                            data.getOr("fl_lat", defValR).toString(),
                            data.getOr("fl_long", defValR).toString(),
                            data.getOr("flele", defValR).toString(),
                            data.getOr("farea", defValR).toString(),
                            data.getOr("", defValR).toString(),
                            data.getOr("fllwr", defValR).toString(),
                            data.getOr("flsla", defValR).toString(),
                            data.getOr("flhst", defValC).toString(),
                            data.getOr("fhdur", defValR).toString()));
                }
                br.write(eventPart2.toString() + "\r\n");
            }
            
            // SOIL ANALYSIS Section
            for (int idx = 0; idx < saArr.size(); idx++) {  
            //if (!saArr.isEmpty()) {
                br.write("*SOIL ANALYSIS\r\n");

    //            secRecords = (ArrayList) result.getOr("soil analysis", new ArrayList());
    //            for (int i = 0; i < secRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) secRecords.get(i));

                    br.write("@A SADAT  SMHB  SMPX  SMKE  SANAME\r\n");
                    br.write(String.format("%1$2s %2$5s %3$-5s %4$-5s %5$-5s  %6$s\r\n",
                            idx+1, //data.getOr("sa", defValI).toString(),
                            formatDateStr(data.getOr("sadat", defValD).toString()),
                            data.getOr("samhb", defValC).toString(),
                            data.getOr("sampx", defValC).toString(),
                            data.getOr("samke", defValC).toString(),
                            data.getOr("sa_name", defValC).toString()));

                    br.write("@A  SABL  SADM  SAOC  SANI SAPHW SAPHB  SAPX  SAKE  SASC\r\n");
    //                secRecords = (ArrayList) data.getOr("soil analysis levels", new ArrayList());
    //                for (int j = 0; j < secRecords.size(); j++)
                    {
    //                    data = adapter.exportRecord((Map) secRecords.get(j));
                        br.write(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s\r\n",
                                idx+1, //data.getOr("sa", defValI).toString(),
                                data.getOr("sabl", defValR).toString(),
                                data.getOr("sabdm", defValR).toString(),
                                data.getOr("saoc", defValR).toString(),
                                data.getOr("sani", defValR).toString(),
                                data.getOr("saphw", defValR).toString(),
                                data.getOr("saphb", defValR).toString(),
                                data.getOr("sapx", defValR).toString(),
                                data.getOr("sake", defValR).toString(),
                                data.getOr("sasc", defValR).toString()));
                    }
                }
                br.write("\r\n");
            }
            
            // INITIAL CONDITIONS Section
            for (int idx = 0; idx < icArr.size(); idx++) {  
            //if (!icArr.isEmpty()) {
                br.write("*INITIAL CONDITIONS\r\n");

    //            eventRecords = (ArrayList) data.getOr("initial", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write("@C   PCR ICDAT  ICRT  ICND  ICRN  ICRE  ICWD ICRES ICREN ICREP ICRIP ICRID ICNAME\r\n");
                    br.write(String.format("%1$2s %2$-5s %3$5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$5s %12$5s %13$5s %14$s\r\n",
                            idx+1, //data.getOr("ic", defValI).toString(),
                            data.getOr("icpcr", defValC).toString(),
                            formatDateStr(data.getOr("icdat", defValD).toString()),
                            data.getOr("icrt", defValR).toString(),
                            data.getOr("icnd", defValR).toString(),
                            data.getOr("icrzno", defValR).toString(),
                            data.getOr("icrze", defValR).toString(),
                            data.getOr("icwt", defValR).toString(),
                            data.getOr("icrag", defValR).toString(),
                            data.getOr("icrn", defValR).toString(),
                            data.getOr("icrp", defValR).toString(),
                            data.getOr("icrip", defValR).toString(),
                            data.getOr("icrdp", defValR).toString(), 
                            data.getOr("ic_name", defValC).toString()));

                    br.write("@C  ICBL  SH2O  SNH4  SNO3\r\n");
    //                secRecords = (ArrayList) data.getOr("initial levels", new ArrayList());
    //                for (int j = 0; j < secRecords.size(); j++)
                    {
    //                    data = adapter.exportRecord((Map) secRecords.get(j));
                        br.write(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s\r\n",
                                idx+1, //data.getOr("ic", defValI).toString(),
                                data.getOr("icbl", defValR).toString(),
                                data.getOr("ich2o", defValR).toString(),
                                data.getOr("icnh4", defValR).toString(),
                                data.getOr("icno3", defValR).toString()));

                    }
                }
                br.write("\r\n");
            }
            
            // PLANTING DETAILS Section
            for (int idx = 0; idx < mpArr.size(); idx++) {  
            //if (!mpArr.isEmpty()) {
                br.write("*PLANTING DETAILS\r\n");
                br.write("@P PDATE EDATE  PPOP  PPOE  PLME  PLDS  PLRS  PLRD  PLDP  PLWT  PAGE  PENV  PLPH  SPRL                        PLNAME\r\n");

    //            eventRecords = (ArrayList) data.getOr("plainting", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s     %6$-1s     %7$-1s %8$5s %9$5s %10$5s %11$5s %12$5s %13$5s %14$5s %15$5s                        %16$s\r\n",
                            idx+1, //data.getOr("pl", defValI).toString(),
                            formatDateStr(data.getOr("pdate", defValD).toString()),
                            formatDateStr(data.getOr("pldae", defValD).toString()),
                            data.getOr("plpop", data.getOr("plpoe", defValR)).toString(),
                            data.getOr("plpoe", data.getOr("plpop", defValR)).toString(),
                            data.getOr("plme", defValC).toString(),
                            data.getOr("plds", defValC).toString(),
                            data.getOr("plrs", defValR).toString(),
                            data.getOr("plrd", defValR).toString(),
                            data.getOr("pldp", defValR).toString(),
                            data.getOr("plmwt", defValR).toString(),
                            data.getOr("page", defValR).toString(),
                            data.getOr("penv", defValR).toString(),
                            data.getOr("plph", defValR).toString(),
                            data.getOr("plspl", defValR).toString(),
                            data.getOr("pl_name", defValC).toString()));

                }
                br.write("\r\n");
            }
            
            // IRRIGATION AND WATER MANAGEMENT Section
            for (int idx = 0; idx < miArr.size(); idx++) {  
            //if (!miArr.isEmpty()) {
                br.write("*IRRIGATION AND WATER MANAGEMENT\r\n");

    //            eventRecords = (ArrayList) data.getOr("irrigation", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write("@I  EFIR  IDEP  ITHR  IEPT  IOFF  IAME  IAMT IRNAME\r\n");
                    br.write(String.format("%1$2s %2$5s %3$5s %4$5s %5$5s %6$-5s %7$-5s %8$5s %9$s\r\n",
                            idx+1, //data.getOr("ir", defValI).toString(),
                            data.getOr("ireff", defValR).toString(),
                            data.getOr("irmdp", defValR).toString(),
                            data.getOr("irthr", defValR).toString(),
                            data.getOr("irept", defValR).toString(),
                            data.getOr("irstg", defValC).toString(),
                            data.getOr("iame", defValC).toString(),
                            data.getOr("iamt", defValR).toString(),
                            data.getOr("ir_name", defValC).toString()));

                    br.write("@I IDATE  IROP IRVAL\r\n");
    //                secRecords = (ArrayList) data.getOr("irrigation levels", new ArrayList());
    //                for (int j = 0; j < secRecords.size(); j++)
                    {
    //                    data = adapter.exportRecord((Map) secRecords.get(j));
                        br.write(String.format("%1$2s %2$5s %3$-5s %4$5s\r\n",
                                idx+1, //data.getOr("ir", defValI).toString(),
                                formatDateStr(data.getOr("idate", defValD).toString()),
                                data.getOr("irop", defValC).toString(),
                                data.getOr("irval", defValR).toString()));
                    }
                }
                br.write("\r\n");
            }
            
            // FERTILIZERS (INORGANIC) Section
            for (int idx = 0; idx < mfArr.size(); idx++) {  
            //if (!mfArr.isEmpty()) {
                br.write("*FERTILIZERS (INORGANIC)\r\n");
                br.write("@F FDATE  FMCD  FACD  FDEP  FAMN  FAMP  FAMK  FAMC  FAMO  FOCD FERNAME\r\n");
    //            eventRecords = (ArrayList) data.getOr("fertilizers", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write(String.format("%1$2s %2$5s %3$-5s %4$-5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$-5s %12$s\r\n",
                            idx+1, //data.getOr("fe", defValI).toString(),
                            formatDateStr(data.getOr("fdate", defValD).toString()),
                            data.getOr("fecd", defValC).toString(),
                            data.getOr("feacd", defValC).toString(),
                            data.getOr("fedep", defValR).toString(),
                            data.getOr("feamn", defValR).toString(),
                            data.getOr("feamp", defValR).toString(),
                            data.getOr("feamk", defValR).toString(),
                            data.getOr("feamc", defValR).toString(),
                            data.getOr("feamo", defValR).toString(),
                            data.getOr("feocd", defValC).toString(),
                            data.getOr("fe_name", defValC).toString()));

                }
                br.write("\r\n");
            }
            
            // RESIDUES AND ORGANIC FERTILIZER Section
            for (int idx = 0; idx < mrArr.size(); idx++) {  
            //if (!mrArr.isEmpty()) {
                br.write("*RESIDUES AND ORGANIC FERTILIZER\r\n");
                br.write("@R RDATE  RCOD  RAMT  RESN  RESP  RESK  RINP  RDEP  RMET RENAME\r\n");

    //            eventRecords = (ArrayList) data.getOr("residues", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write(String.format("%1$2s %2$5s %3$-5s %4$5s %5$5s %6$5s %7$5s %8$5s %9$5s %10$5s %11$s\r\n",
                            idx+1, //data.getOr("om", defValI).toString(),
                            formatDateStr(data.getOr("omdat", defValD).toString()),
                            data.getOr("omcd", defValC).toString(),
                            data.getOr("omamt", defValR).toString(),
                            data.getOr("omnpct", defValR).toString(),
                            data.getOr("omppct", defValR).toString(),
                            data.getOr("omkpct", defValR).toString(),
                            data.getOr("ominp", defValR).toString(),
                            data.getOr("omdep", defValR).toString(),
                            data.getOr("omacd", defValR).toString(),
                            data.getOr("om_name", defValC).toString()));

                }
                br.write("\r\n");
            }
            
            // CHEMICAL APPLICATIONS Section
            for (int idx = 0; idx < mcArr.size(); idx++) {  
            //if (!mcArr.isEmpty()) {
                br.write("*CHEMICAL APPLICATIONS\r\n");
                br.write("@C CDATE CHCOD CHAMT  CHME CHDEP   CHT..CHNAME\r\n");

    //            eventRecords = (ArrayList) data.getOr("chemical", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write(String.format("%1$2s %2$5s %3$-5s %4$5s %5$-5s %6$-5s %7$-5s  %8$s\r\n",
                            idx+1, //data.getOr("ch", defValI).toString(),
                            formatDateStr(data.getOr("cdate", defValD).toString()),
                            data.getOr("chcd", defValC).toString(),
                            data.getOr("chamt", defValR).toString(),
                            data.getOr("chacd", defValC).toString(),
                            data.getOr("chdep", defValC).toString(),
                            data.getOr("ch_targets", defValC).toString(),
                            data.getOr("ch_name", defValC).toString()));

                }
                br.write("\r\n");
            }
            
            // TILLAGE Section
            for (int idx = 0; idx < mtArr.size(); idx++) {  
            //if (!mtArr.isEmpty()) {
                br.write("*TILLAGE\r\n");
                br.write("@T TDATE TIMPL  TDEP TNAME\r\n");

    //            eventRecords = (ArrayList) data.getOr("tillage", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write(String.format("%1$2s %2$5s %3$-5s %4$5s %5$s\r\n",
                            idx+1, //data.getOr("ti", defValI).toString(),
                            formatDateStr(data.getOr("tdate", defValD).toString()),
                            data.getOr("tiimp", defValC).toString(),
                            data.getOr("tidep", defValR).toString(),
                            data.getOr("ti_name", defValC).toString()));

                }
                br.write("\r\n");
            }
            
            // ENVIRONMENT MODIFICATIONS Section
            for (int idx = 0; idx < meArr.size(); idx++) {  
            //if (!meArr.isEmpty()) {
                br.write("*ENVIRONMENT MODIFICATIONS\r\n");
                br.write("@E ODATE EDAY  ERAD  EMAX  EMIN  ERAIN ECO2  EDEW  EWIND ENVNAME\r\n");

    //            eventRecords = (ArrayList) data.getOr("environment", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write(String.format("%1$2s %2$5s %3$-1s%4$4s %5$-1s%6$4s %7$-1s%8$4s %9$-1s%10$4s %11$-1s%12$4s %13$-1s%14$4s %15$-1s%16$4s %17$-1s%18$4s %19$s\r\n",
                            idx+1, //data.getOr("em", defValI).toString(),
                            formatDateStr(data.getOr("emday", defValD).toString()),
                            data.getOr("ecdyl", defValC).toString(),
                            data.getOr("emdyl", defValR).toString(),
                            data.getOr("ecrad", defValC).toString(),
                            data.getOr("emrad", defValR).toString(),
                            data.getOr("ecmax", defValC).toString(),
                            data.getOr("emmax", defValR).toString(),
                            data.getOr("ecmin", defValC).toString(),
                            data.getOr("emmin", defValR).toString(),
                            data.getOr("ecrai", defValC).toString(),
                            data.getOr("emrai", defValR).toString(),
                            data.getOr("ecco2", defValC).toString(),
                            data.getOr("emco2", defValR).toString(),
                            data.getOr("ecdew", defValC).toString(),
                            data.getOr("emdew", defValR).toString(),
                            data.getOr("ecwnd", defValC).toString(),
                            data.getOr("emwnd", defValR).toString(),
                            data.getOr("em_name", defValC).toString()));

                }
                br.write("\r\n");
            }
            
            // HARVEST DETAILS Section
            for (int idx = 0; idx < mhArr.size(); idx++) {  
            //if (!mhArr.isEmpty()) {
                br.write("*HARVEST DETAILS\r\n");
                br.write("@H HDATE  HSTG  HCOM HSIZE   HPC  HBPC HNAME\r\n");

    //            eventRecords = (ArrayList) data.getOr("harvest", new ArrayList());
    //            for (int i = 0; i < eventRecords.size(); i++)
                {
    //                data = adapter.exportRecord((Map) eventRecords.get(i));
                    br.write(String.format("%1$2s %2$5s %3$-5s %4$-5s %5$-5s %6$5s %7$5s %8$s\r\n",
                            idx+1, //data.getOr("ha", defValI).toString(),
                            formatDateStr(data.getOr("hdate", defValD).toString()),
                            data.getOr("hastg", defValC).toString(),
                            data.getOr("hacom", defValC).toString(),
                            data.getOr("hasiz", defValC).toString(),
                            data.getOr("hapc", defValR).toString(),
                            data.getOr("habpc", defValR).toString(),
                            data.getOr("ha_name", defValC).toString()));

                }
                br.write("\r\n");
            }
            
            // SIMULATION CONTROLS and AUTOMATIC MANAGEMENT Section
            br.write(createSMMAStr(0, result));
            
            // Output finish
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
     /**
     * Create string of Simulation Control and Automatic Management Section
     * 
     * @author Meng Zhang
     * @version 1.0
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
        } 
        // default
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
     * Translate data str from "yyyymmdd" to "yyddd"
     * 
     * 2012/3/19    change input format from "yy/mm/dd" to "yyyymmdd"
     * 
     * @author Meng Zhang
     * @version 1.1
     * @param str  date string with format of "yyyymmdd"
     * @return result date string with format of "yyddd" 
     */
    private String formatDateStr(String str) {

        // Initial Calendar object
        Calendar cal = Calendar.getInstance();
        str = str.replaceAll("/", "");
        try {
            // Set date with input value
            cal.set(Integer.valueOf(str.substring(0,4)), Integer.valueOf(str.substring(4,6))-1, Integer.valueOf(str.substring(6)));
            // translatet to yyddd format
            return String.format("%1$02d%2$03d", cal.get(Calendar.YEAR)%100, cal.get(Calendar.DAY_OF_YEAR));
        } catch (Exception e) {
            // if tranlate failed, then use default value for date
            return formatDateStr(defValD);
        }
    }
    
    /**
     * Set default value for missing data
     * 
     * @author Meng Zhang
     * @version 1.0
     * @param result  date holder for experiment data
     */
    private void setDefVal(AdvancedHashMap result) {

        if (!result.getOr("icdat", "").toString().equals("")) {
            defValD = result.getOr("icdat", "").toString();
        } else if (!result.getOr("sdat", "").toString().equals("")) {
            defValD = result.getOr("sdat", "").toString();
        } else  if (!result.getOr("pdate", "").toString().equals("")) {
            defValD = result.getOr("pdate", "").toString();
        } else {
            //throw new Exception("Experiment can't be output due to unavailable date info.");
            defValD = "20110101";
        }
        defValR = "-99.0";
        defValC = "";
        defValI = "-99";
    }
    
    /**
     * Get index value of the record and set new id value in the array
     * 
     * @author Meng Zhang
     * @version 1.0
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
    
    /**
     * Get exname with normal format
     * 
     * @author Meng Zhang
     * @version 1.0
     * @param result  date holder for experiment data
     * @return       exname
     */
    private String getExName(AdvancedHashMap result) {

        String ret = result.getOr("exname", "").toString();
        if (ret.contains(".")) {
            ret = ret.substring(0, ret.length()-1).replace(".", "");
        }
        
        return ret;
    }
}
