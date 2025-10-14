#! /usr/bin/env bash
KEY=10

mkdir ./tmp
{ echo 'public class Test1 {';
echo '    public String message() {'; 
echo '        return "Ciao mondo!";';
echo '    }'; 
echo '}'; } > tmp/Test1.java

javac tmp/Test1.java
mv tmp/Test1.class .
javac ./*.java
java Encrypt $KEY Test1
rm Test1.class
rm -rf ./tmp 
java -Djava.system.class.loader=EncryptedClassLoader Main
rm Test1.nidki ./*.class
