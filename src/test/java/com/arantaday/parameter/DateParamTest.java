package com.arantaday.parameter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class DateParamTest {

  @Test
  public void testBasicDate() {
    final DateParam dateParam = new DateParam("05-28-1986");
    Assert.assertNotNull(dateParam.getValue());

    // Calendars are the preferred way to check details on dates in Java,
    // so let's convert our Date to one.
    // (actually, the preferred way is to use Joda Time - but for the sake
    // of this example, I'll include as few external dependencies as possible).
    final Calendar cal = Calendar.getInstance();
    cal.setTime(dateParam.getValue());
    Assert.assertEquals(1986, cal.get(Calendar.YEAR));
    Assert.assertEquals(Calendar.MAY, cal.get(Calendar.MONTH));
    Assert.assertEquals(28, cal.get(Calendar.DAY_OF_MONTH));
  }
}
