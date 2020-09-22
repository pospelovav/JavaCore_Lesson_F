package ru.geekbrains.javacore.lesson_f.home;



import java.io.*;
import java.util.Scanner;

public class Main {

    // private static void openFile(String name) {
    //     // if (file == null)
    //     FileNotFoundException ex = new FileNotFoundException("No such file or directory");
    //     try {
    //         throw ex;
    //     } catch (IOException e) {
    //         System.out.println("File not found");
    //     }
    // }

    // private static void writeFile() throws IOException {
    //     throw new IOException("write failed");
    // }



    private static StringBuilder GenerateString(int LengthString){    //генерирует строку из случайных латинских букв
        char[] abcd = {'A', 'a', 'B', 'b', 'C', 'c', ' ', 'D', 'd', 'E', 'e', 'F', 'f', 'G', ' ', 'g', 'H', 'h',
                'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', ' ', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r',
                'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z', ' '};

        StringBuilder stringToWrite = new StringBuilder("");
        for (int i = 0; i < LengthString; i++) {
            stringToWrite.append(abcd[(int) (Math.random()*(abcd.length))]);
        }

        return stringToWrite;
    }

    private static void createFileTXT(String FileName, StringBuilder StringForRec){      //метод записи в файл строки

        try {

            PrintStream stringToStream = new PrintStream(new FileOutputStream(FileName, true));
            stringToStream.println(StringForRec);
            stringToStream.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    private static StringBuilder ReadFileToString (String FileName){
        StringBuilder stringFromFile = new StringBuilder("");
        try {
            FileInputStream fis = new FileInputStream(FileName);
            int b;
            while ((b = fis.read()) != -1) {
                //System.out.print((char) b);
                stringFromFile.append((char) b);
            }
            //System.out.println(fis.read());

            fis.close();
            // Scanner scanner = new Scanner(new FileInputStream(FileName));
            // while (scanner.hasNext()) {
            //     //System.out.println(scanner.next());
            //     stringFromFile.append(scanner.next());


            //     //stringFromFile.append('\n');
            // }
            // stringFromFile.append('\n');
            // scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stringFromFile;

    }

    private static void UnitTwoTextFile (String FileName, StringBuilder stringFromFile){
        stringFromFile.append("Content from " + FileName + ":\n");
        stringFromFile.append(ReadFileToString(FileName));
        stringFromFile.append('\n');
    }

    private static boolean FindInFile (String FileName, String UserWord){
        //String s = ReadFileToString(FileName).toString();

        return ReadFileToString(FileName).toString().contains(UserWord);
    }

    public static void main (String[] args){

        StringBuilder stringForUniFile = new StringBuilder("");

        createFileTXT("FistTextFile.txt", GenerateString(70).append(" Hello"));    //.append(" Hello") для поиска слова в файле (чтобы гарантированно присутствовало)
        createFileTXT("SecondTextFile.txt", GenerateString(60));

        UnitTwoTextFile("FistTextFile.txt", stringForUniFile);
        UnitTwoTextFile("SecondTextFile.txt", stringForUniFile);

        createFileTXT("UniFile.txt", stringForUniFile);

        String FileNameForFind = "FistTextFile.txt";   //файл для поиска
        String WordForFind = "Hello";               //слово для поиска

        if (FindInFile(FileNameForFind, WordForFind)){
            System.out.println("'" + WordForFind + "' is find in " + FileNameForFind);
        } else {
            System.out.println("'" + WordForFind + "' is not find in " + FileNameForFind);
        }


    }


}


