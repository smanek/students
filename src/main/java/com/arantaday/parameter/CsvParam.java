package com.arantaday.parameter;

import com.google.common.base.Splitter;

import javax.ws.rs.WebApplicationException;

/**
 * Split a string on commas.
 */
public class CsvParam extends AbstractParam<Iterable<String>> {
  public CsvParam(String param) throws WebApplicationException {
    super(param);
  }

  @Override
  protected Iterable<String> parse(String param) throws Throwable {
    return Splitter.on(',').trimResults().omitEmptyStrings().split(param);
  }
}
