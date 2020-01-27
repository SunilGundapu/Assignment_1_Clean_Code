package com.cleancoder.args;

import static com.cleancoder.args.ArgsException.ErrorCode.*;

import java.util.*;

public class FloatArgumentMarshaler implements ArgumentMarshaler {
  private float floatValue = 0;

  public void set(Iterator<String> currentArgument) throws ArgsException {
    String parameter = null;
    try {
      parameter = currentArgument.next();
      floatValue = Float.parseFloat(parameter);
    } catch (NoSuchElementException e) {
      throw new ArgsException(MISSING_FLOAT);
    } catch (NumberFormatException e) {
      throw new ArgsException(INVALID_FLOAT, parameter);
    }
  }

  public static double getValue(ArgumentMarshaler am) {
    if (am != null && am instanceof FloatArgumentMarshaler)
      return ((FloatArgumentMarshaler) am).floatValue;
    else
      return 0.0;
  }
}
