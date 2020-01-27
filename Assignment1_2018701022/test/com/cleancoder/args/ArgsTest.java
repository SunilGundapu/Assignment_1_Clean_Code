package com.cleancoder.args;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Map;

import static com.cleancoder.args.ArgsException.ErrorCode.*;
import static org.junit.Assert.*;



public class ArgsTest {

  public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ArgsTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
  }

  @Test
  public void testCreateWithNoSchemaOrArguments() throws Exception {

    Args args = new Args("", new String[0]);
    assertEquals(0, args.nextArgument());
  }


  @Test
  public void testWithNoSchemaButWithOneArgument() throws Exception {
    try {

      new Args("", new String[]{"-x"});
      fail();
    } catch (ArgsException e) {
	  assertTrue(UNEXPECTED_ARGUMENT.equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testWithNoSchemaButWithMultipleArguments() throws Exception {
    try {
      new Args("", new String[]{"-x", "-y"});
      fail();
    } catch (ArgsException e) {
	  assertTrue(UNEXPECTED_ARGUMENT.equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testNonLetterSchema() throws Exception {
    try {
      new Args("*", new String[]{});
      fail("Args constructor should have thrown exception");
    } catch (ArgsException e) {
      assertTrue(INVALID_ARGUMENT_NAME.equals(e.getErrorCode()) && ('*')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testInvalidArgumentFormat() throws Exception {
    try {
      new Args("f~", new String[]{});
      fail("Args constructor should have throws exception");
    } catch (ArgsException e) {
      assertTrue(INVALID_ARGUMENT_FORMAT.equals(e.getErrorCode()) && ('f')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testSimpleBooleanPresent() throws Exception {
    Args args = new Args("x", new String[]{"-x"});
    assertTrue(true==(args.getBoolean('x')) && (1)==(args.nextArgument()));
  }

  @Test
  public void testSimpleStringPresent() throws Exception {
    Args args = new Args("x*", new String[]{"-x", "param"});
    assertTrue(args.has('x') && ("param").equals(args.getString('x')) && (2)==(args.nextArgument()));
  }

  @Test
  public void testMissingStringArgument() throws Exception {
    try {
      new Args("x*", new String[]{"-x"});
      fail();
    } catch (ArgsException e) {
      assertTrue(MISSING_STRING.equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testSpacesInFormat() throws Exception {
    Args args = new Args("x, y", new String[]{"-xy"});
    assertTrue(args.has('x') && args.has('y') && (1)==(args.nextArgument()));
  }

  @Test
  public void testSimpleIntPresent() throws Exception {
    Args args = new Args("x#", new String[]{"-x", "42"});
    assertTrue(args.has('x') && (42)==(args.getInt('x')) && (2)==(args.nextArgument()));
  }

  @Test
  public void testInvalidInteger() throws Exception {
    try {
      new Args("x#", new String[]{"-x", "Forty two"});
      fail();
    } catch (ArgsException e) {
     assertTrue(INVALID_INTEGER.equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()) && ("Forty two")==(e.getErrorParameter()));
    }

  }

  @Test
  public void testMissingInteger() throws Exception {
    try {
      new Args("x#", new String[]{"-x"});
      fail();
    } catch (ArgsException e) {
      assertTrue((MISSING_INTEGER).equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testSimpleDoublePresent() throws Exception {
    Args args = new Args("x##", new String[]{"-x", "42.3"});
    assertTrue(args.has('x') && (42.3)==(args.getDouble('x')));
  }

  @Test
  public void testInvalidDouble() throws Exception {
    try {
      new Args("x##", new String[]{"-x", "Forty two"});
      fail();
    } catch (ArgsException e) {
      assertTrue(INVALID_DOUBLE.equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()) && "Forty two"==(e.getErrorParameter()));
    }
  }

  @Test
  public void testMissingDouble() throws Exception {
    try {
      new Args("x##", new String[]{"-x"});
      fail();
    } catch (ArgsException e) {
      assertTrue(MISSING_DOUBLE.equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testStringArray() throws Exception {
    Args args = new Args("x[*]", new String[]{"-x", "alpha"});
    assertTrue(args.has('x'));
    String[] result = args.getStringArray('x');
    assertTrue((1)==(result.length) && ("alpha")==(result[0]));
  }

  @Test
  public void testMissingStringArrayElement() throws Exception {
    try {
      new Args("x[*]", new String[] {"-x"});
      fail();
    } catch (ArgsException e) {
      assertTrue(MISSING_STRING.equals(e.getErrorCode()) && ('x')==(e.getErrorArgumentId()));
    }
  }

  @Test
  public void testExtraArgumentsThatLookLikeFlags() throws Exception {
    Args args = new Args("x,y", new String[]{"-x", "alpha", "-y", "beta"});
    assertTrue(args.has('x') && args.getBoolean('x') && (1)==(args.nextArgument()));
    assertFalse(args.has('y') && args.getBoolean('y'));
  }

  @Test
  public void manyStringArrayElements() throws Exception {
    Args args = new Args("x[*]", new String[]{"-x", "alpha", "-x", "beta", "-x", "gamma"});
    assertTrue(args.has('x'));
    String[] result = args.getStringArray('x');
    assertTrue((3)==(result.length) && ("alpha")==(result[0]) && ("beta")==(result[1]) && ("gamma")==(result[2]));
  }

  @Test
  public void MapArgument() throws Exception {
    Args args = new Args("f&", new String[] {"-f", "key1:val1,key2:val2"});
    Map<String, String> map = args.getMap('f');
    assertTrue(args.has('f') && "val1".equals(map.get("key1")) && "val2".equals(map.get("key2")));
  }

  @Test(expected=ArgsException.class)
  public void malFormedMapArgument() throws Exception {
    Args args = new Args("f&", new String[] {"-f", "key1:val1,key2"});
  }

  @Test
  public void oneMapArgument() throws Exception {
    Args args = new Args("f&", new String[] {"-f", "key1:val1"});
    Map<String, String> map = args.getMap('f');
	assertTrue(args.has('f') && ("val1").equals(map.get("key1")));
  }

  @Test
  public void testExtraArguments() throws Exception {
    Args args = new Args("x,y*", new String[]{"-x", "-y", "alpha", "beta"});
    assertTrue(args.getBoolean('x') && ("alpha").equals(args.getString('y')) && (3)==(args.nextArgument()));
  }
}

