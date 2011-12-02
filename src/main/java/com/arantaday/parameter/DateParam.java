package com.arantaday.parameter;

import javax.ws.rs.WebApplicationException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parses a date out of a string like 'MM-DD-YYYY' (e.g, '05-28-1086' => May 28th, 1986).
 */
public class DateParam extends AbstractParam<Date> {

  private static final SimpleDateFormat PARSER = new SimpleDateFormat("MM-dd-yyyy");

  public DateParam(String param) throws WebApplicationException {
    super(param);
  }

  @Override
  protected Date parse(String param) throws Throwable {
    return PARSER.parse(param);
  }
}
