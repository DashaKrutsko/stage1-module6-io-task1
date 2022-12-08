package com.epam.mjc.io;

import java.io.*;


public class FileReader {
    static String directory = System.getProperty("user.dir") + "\\src\\main\\resources";
    static String fileName = "Profile.txt";
    static String path = directory + File.separator + fileName;


    public static Profile getDataFromFile(File file) {
        //    Profile profile = new Profile();
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(path))) {
            String name = "";
            Integer age = 0;
            String email = "";
            Long phone = 0L;

            String line;
            while ((line = bufferedReader.readLine()) != null) {

             //  System.out.println(line);
                String[] keyValue = line.split(": ");
                String value = keyValue[1].trim();
                System.out.println(keyValue[0]);
                System.out.println(value);
                switch (keyValue[0].trim()) {
                    case "Name":
                        name = value;
                        break;
                    case "Age":
                        age = Integer.parseInt(value);
                        break;
                    case "Email":
                        email = value;
                        break;
                    case "Phone":
                        phone = Long.parseLong(value);
                        break;
                    default:
                        break;
                }

            }
            bufferedReader.close();
            return new Profile(name, age, email, phone);
        } catch (FileNotFoundException e) {
            System.out.println("No file");
            return null;
        } catch (IOException e) {
            System.out.println("IO exception");
            return null;
        }
    }

    public static void main(String[] args) {
        File file = new File(path);
        Profile p = getDataFromFile(file);
      //  System.out.println(p);
    }
}
