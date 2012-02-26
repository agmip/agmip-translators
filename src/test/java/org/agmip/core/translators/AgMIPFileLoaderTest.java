package org.agmip.core.translators;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.agmip.core.types.AdvancedHashMap;
import org.agmip.util.JSONAdapter;
import org.junit.Before;
import org.junit.Test;


public class AgMIPFileLoaderTest {

  AgMIPFileLoader agMIPFileLoader;

  @Before
    public void setUp() throws Exception {
      agMIPFileLoader = new AgMIPFileLoader();
    }
  @Test
    public void test() throws IOException, Exception{
      JSONAdapter j = new JSONAdapter();

      AdvancedHashMap<String, Object> result =
        agMIPFileLoader.readFile("BDJE0XXX.AgMIP");
      System.out.println(result.size());
      System.out.println(j.toJSON(result));
    }

}
