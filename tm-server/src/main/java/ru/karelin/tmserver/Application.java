package ru.karelin.tmserver;

import ru.karelin.tmserver.util.Bootstrap;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args)  {

        try {
            new Bootstrap().init();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
