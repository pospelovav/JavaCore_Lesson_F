package ru.geekbrains.javacore.lesson_f.home;

import java.io.*;
import java.util.Scanner;

public class Main {

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
        }
    }
    
    private static StringBuilder ReadFileToString (String FileName){    //создает строку из содержимого файла
        StringBuilder stringFromFile = new StringBuilder("");
        try {
            FileInputStream fis = new FileInputStream(FileName);
            int b;
            while ((b = fis.read()) != -1) {
                stringFromFile.append((char) b);
            }
            fis.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stringFromFile;
        
    }
    
    private static void AddToStringTextFile (String FileName, StringBuilder stringFromFile){  //добавляет содержимое файла в существующую строку
        stringFromFile.append("Content from " + FileName + ":\n");
        stringFromFile.append(ReadFileToString(FileName));
        stringFromFile.append('\n');
    }
    
    private static boolean FindInFile (String FileName, String UserWord){   
        return ReadFileToString(FileName).toString().contains(UserWord);
    }
    
    private static File[] FindInFolder (String directoryFiles, String typeFile){
        File[] files = new File[0];
        try {
            
            File f = new File(directoryFiles);
            
            FileFilter filter = new FileFilter() {
                public boolean accept(File f)
                {
                    return f.getName().endsWith(typeFile);
                }
            };
            //File[] files = f.listFiles(filter);
            files = f.listFiles(filter);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return files;
    }
    
    public static void main (String[] args){
        
        StringBuilder stringForUniFile = new StringBuilder("");

        createFileTXT("FistTextFile.txt", GenerateString(70).append(" Hello"));    //.append(" Hello") для поиска слова в файле (чтобы гарантированно присутствовало)
        createFileTXT("SecondTextFile.txt", GenerateString(60));
        
        AddToStringTextFile("FistTextFile.txt", stringForUniFile);
        AddToStringTextFile("SecondTextFile.txt", stringForUniFile);
        
        createFileTXT("UniFile.txt", stringForUniFile);
        
        String FileNameForFind = "FistTextFile.txt";   //файл для поиска
        //String WordForFind = "Hello";               //слово для поиска
        System.out.println("Enter a search word, for example 'Hello', in " + FileNameForFind); //ввод слова для поиска в файле
        Scanner scanner = new Scanner(System.in);
        String WordForFind = scanner.next();
        
        System.out.println("Search in single file:");
        
        if (FindInFile(FileNameForFind, WordForFind)){
            System.out.println("'" + WordForFind + "' is find in " + FileNameForFind);
        } else {
            System.out.println("'" + WordForFind + "' is not find in " + FileNameForFind);
        }
        
        System.out.println();
        System.out.println("Search in folder:");
        File[] files = FindInFolder("./", "txt");  //поиск файлов txt в директории ./
        
        //WordForFind = "Hello";               //слово для поиска в папке
        System.out.println("Enter a search word, for example 'Hello', in directory"); //ввод слова для поиска в каталоге
        Scanner scanner2 = new Scanner(System.in);
        WordForFind = scanner2.next();
        
        int colFound = 0;
        for (int i=0; i < files.length; i++){
            if (FindInFile(files[i].getName(), WordForFind)){
                System.out.println("'" + WordForFind + "' is find in " + files[i].getName());
                colFound++;
            } 
        }
        if (colFound == 0){
            System.out.println("'" + WordForFind + "' not found");
        }
    
    }

}
