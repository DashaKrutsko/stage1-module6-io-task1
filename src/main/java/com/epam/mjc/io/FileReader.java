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
                int indexName = str.indexOf("Name:");
                int indexAge = str.indexOf("Age:");
                int indexEmail = str.indexOf("Email:");
                int indexPhone = str.indexOf("Phone:");

                if (indexName >= 0) {
                    name = str.substring(indexName + 6);
                }
                if (indexAge >= 0) {
                    age = Integer.parseInt(str.substring(indexAge + 5));
                }
                if (indexEmail >= 0) {
                    email = str.substring(indexEmail + 7);
                }
                if (indexPhone >= 0) {
                    phone = Long.parseLong(str.substring(indexPhone + 7));
                }
            }

            return new Profile(name, age, email, phone);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        File file = new File(path);
        getDataFromFile(file);

    }
}
