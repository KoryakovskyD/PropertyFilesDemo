package ru.avalon.javapp.devj120;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertyFile propertyFile = new PropertyFile();
        propertyFile.set("address", "127.0.0.1");
        propertyFile.set("port", "12345");
        propertyFile.set("login", "my_user");
        System.out.println(propertyFile.contains("login"));
        System.out.println(propertyFile.contains("password"));
        System.out.println(propertyFile.get("address"));
        propertyFile.save("test.props");
    }
}
