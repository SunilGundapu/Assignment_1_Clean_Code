package com.cleancoder.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class TimeArgumentMarshaler implements ArgumentMarshaler {
  private String timeValue = "";

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
		timeValue = currentArgument.next();
		if(!timeValue.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
			throw new ArgsException(INVALID_TIME, timeValue);
		}
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_TIME);
    }
  }

  public static String getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof TimeArgumentMarshaler)
      return ((TimeArgumentMarshaler) am).timeValue;
    else
      return "";
  }
}

