package com.cleancoder.args;

public class ArgsMain {
  public static void main(String[] args) {
    try {
      Args arg = new Args("l,p#,d*,t@,m^,f%", args);
      boolean logging = arg.getBoolean('l');
      int port = arg.getInt('p');
      String directory = arg.getString('d');
	  String time = arg.getTime('t');
	  float memorySize = arg.getFloat('m');
	  String fileName = arg.getFile('f');
      executeApplication(logging, port, directory);
	  fileInformation(fileName, time, memorySize);
    } catch (ArgsException e) {
      System.out.printf("Argument error: %s\n", e.errorMessage());
	  System.out.println("Command Line Input Format:");
	  System.out.println("-l -d directory_name -p port_number -t 24hrs_time -m memory_in_floatingnumbers -f filename");
    } finally {
      System.out.println("Done!");
    }
  }

  private static void executeApplication(boolean logging, int port, String directory) {
    System.out.printf("logging is %s, port:%d, directory:%s\n",logging, port, directory);
  }

  //newly added method  
  private static void fileInformation(String fileName, String time, float memorySize) {
    System.out.printf("file:%s, created time:%s, file size:%sf\n", fileName, time, memorySize);
  }
}

