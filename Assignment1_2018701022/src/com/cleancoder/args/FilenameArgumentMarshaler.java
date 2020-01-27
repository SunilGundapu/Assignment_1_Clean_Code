package com.cleancoder.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

public class FilenameArgumentMarshaler implements ArgumentMarshaler {
  private String stringValue = "";

  public void set(Iterator<String> currentArgument) throws ArgsException {
    try {
      stringValue = currentArgument.next();
	  if(!stringValue.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)")) {
			throw new ArgsException(INVALID_IMAGE_NAME);
		}
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_STRING);
    }
  }

  public static String getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof FilenameArgumentMarshaler)
      return ((FilenameArgumentMarshaler) am).stringValue;
    else
      return "";
  }
}
