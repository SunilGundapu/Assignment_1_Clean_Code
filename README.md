**Assignment - 1: Clean Code**

Implemented features:
I have implemented three more ArgumentMarshaler. Those are TimeArgumentMarshaler, FloatArgumentMarshaler and FilenameArgumentMarshaler.

The Args class with two parameters. The first parameter is the format, or schema, string: "l,p#,d*,t@,m^,f%" and The second parameter to the Args constructor is simply the array of command-line argument passed into main.

Schema defines three already mentioned command-line arguments (l, p, d) and three new arguments (f, t, m), totally five command line arguments. f, t are string arguments and m is a float argument.

--> t argument takes time in 24hours format (HH:MM)
--> m argument takes memory in float point numbers (eg., 3.01f)
--> f argument takes image file name with all extensions (jpg,png,bpm..etc)
