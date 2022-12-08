package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {
    static String directory = System.getProperty("user.dir") + "\\src\\main\\resources";
    static String fileName = "Profile.txt";
    static String path = directory + File.separator + fileName;

    public static Profile getDataFromFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String name = "";
            Integer age = 0;
            String email = "";
            Long phone = 0L;
            byte[] bytes = fileInputStream.readAllBytes();
            String string = new String(bytes);

            String[] strings = string.split("\r\n");
            for (String str : strings) {
                String[] keyValue = str.split(": ");

                switch (keyValue[0]) {
                    case "Name":
                        name = keyValue[1];
                        break;
                    case "Age":
                        age = Integer.parseInt(keyValue[1]);
                        break;
                    case "Email":
                        email = keyValue[1];
                        break;
                    case "Phone":
                        phone = Long.parseLong(keyValue[1]);
                        break;
                }
            }
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
        getDataFromFile(file);
    }
}
