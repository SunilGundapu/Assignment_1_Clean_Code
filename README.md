## Assignment - 1: Clean Code

#### Implemented features:
I have implemented three more ArgumentMarshaler. Those are **TimeArgumentMarshaler**, **FloatArgumentMarshaler** and **FilenameArgumentMarshaler**.

The **Args** class with two parameters. The first parameter is the format, or **schema**, string: "l,p#,d*,t@,m^,f%" and The second parameter to the **args** constructor is simply the array of command-line argument passed into main.

Schema defines three already mentioned command-line arguments (l, p, d) and three new arguments (f, t, m), totally six command line arguments. f, t are string arguments and m is a **float argument**.

1. **t** argument takes time in 24hours format (HH:MM)
2. **m** argument takes memory in float point numbers (eg., 3.01f)
3. **f** argument takes image file name with all extensions (jpg,png,bpm..etc)

TimeArgumentMashler will takes 24hrs format time string and checks whether the given input string satisfying the time format or not. If the string doesn't tally with time regex then it will throws **INVALID_TIME_FORMAT** exception.

FloatArgumentMarshaler will takes large arrays of floating point numbers. The float data type should never be used for precise values and it's default value is 0.0F.

FilenameArgumentMarshaler will takes image file names with all extensions like (.jpg|.png|.gif). If the given input string doesn't match with image file name regex then it will throws **INVALID_IMAGE_FILENAME** exception.

I'm passing above three arguments t, m and f to a method called **fileInformation**. This method gives the information like file name (f), memory size of file (m), file created time (t).

##### Example 
    > fileInformation(String fileName, String time, float memorySize)
    > output: file name: cat.jpg, created time:12:34, file size:15.04f
  
Wrote the exception cases for directory names and port numbers. Those are **INVALID_DIRECTORY_NAME** and **INVALID_PORT_NUMBER**.

In Linux/Windows/Mac, we have rules for directory names. Based on those rules wrote a regex for directory names. If the given directory name doesn't satisfy the regex then it will throws **INVALID_DIRECTORY_NAME** exception.

According to TCP/IP port numbers are mostly in between 1024 - 65525, so if any port number not in this range then it will throws **INVALID_PORT_NUMBER** exception.


## Instructions for execution

* For compilation: Go to Assignment1_2018701022 folder > then go to src folder > then run below command *
  **javac com/cleancoder/args/*.java**
* For runtime: *
  **java com.cleancoder.args.ArgsMain -l -p 8080 -d sofwareengg -t 12:34 -f cat.jpg -m 15.04**

