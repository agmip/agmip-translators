package org.agmip.core.translators;

import java.io.IOException;

import org.agmip.core.types.AdvancedHashMap;
import org.agmip.core.types.TranslatorOutput;
import org.agmip.util.JSONAdapter;

public class TestClass {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      SentinelFileLoader sfl=new SentinelFileLoader();
      AdvancedHashMap<String, Object> result;
      String json;
      JSONAdapter j = new JSONAdapter();
      AgMIPFileLoader agMIPFileLoader=new AgMIPFileLoader();;
      result =agMIPFileLoader.readFile("BDJE0XXX.AgMIP");
      InfocropWeather inc= new InfocropWeather();
      json=j.toJSON(result);
      inc.writeFile(json, result);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
