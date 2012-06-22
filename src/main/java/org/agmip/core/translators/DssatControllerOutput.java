/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.agmip.core.translators;

import org.agmip.core.types.AdvancedHashMap;

/**
 *
 * @author Mike
 */
public class DssatControllerOutput {

    private DssatCommonOutput[] outputs = {
        new DssatXFileOutput(),
        new DssatSoilOutput(),
        new DssatWeatherOutput(),
        new DssatAFileOutput(),
        new DssatTFileOutput()};
    
    public void writeFiles(String arg0, AdvancedHashMap result) {
        
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].writeFile(arg0, result);
        }
    }
}
