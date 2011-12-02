package com.arantaday.json;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Department {
  COMPUTER_SCIENCE("CS"),
  ENGLISH("ENG"),
  MATH("MATH");

  private static final Map<String, Department> SHORT_NAME_TO_DEPARTMENT;

  static {
    Map<String, Department> nameToDept = new HashMap<String, Department>();
    for (Department d : Department.values()) {
      assert d.getShortName() != null && d.getShortName().length() > 0;
      assert !nameToDept.containsKey(d.getShortName());
      nameToDept.put(d.getShortName(), d);
    }
    SHORT_NAME_TO_DEPARTMENT = Collections.unmodifiableMap(nameToDept);
  }

  private final String shortName;

  Department(String shortName) {
    assert !shortName.contains("-");
    this.shortName = shortName;
  }

  public String getShortName() {
    return shortName;
  }

  public static Department fromShortName(String shortName) {
    return SHORT_NAME_TO_DEPARTMENT.get(shortName);
  }
}
