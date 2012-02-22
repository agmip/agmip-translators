package org.agmip.core.translators;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import org.agmip.core.types.*;
import org.agmip.core.types.weather.*;

public class SticsWeather implements WeatherFile {
  public SticsWeather() {}
  /*public AdvancedHashMap<String, Object> readFile(String fileName) {}*/
  public void readFile(String fileName) {}
  public void writeFile(String fileName, AdvancedHashMap<String, Object> data);
}