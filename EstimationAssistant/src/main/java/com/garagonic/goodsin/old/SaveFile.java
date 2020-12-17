package com.garagonic.estimationAssistant.old;

import com.garagonic.estimationAssistant.repository.Goods;

import java.io.*;
import java.util.List;

public class SaveFile {

    private static String FILE_NAME = "StockRoom.txt";

    public static StockRoom loadStockroom() {
        FileInputStream fi;
        StockRoom stockRoom = new StockRoom();
        try {
            fi = new FileInputStream(new File(FILE_NAME));

            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            stockRoom.setGoodsList((List<Goods>) oi.readObject() );

            System.out.println("Stockroom loaded successfully!");

            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found. This is expected for the first start. \nNew Stockroom created.");
            stockRoom = new StockRoom();
        } catch (IOException e) {
            e.printStackTrace();
            stockRoom = new StockRoom();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            stockRoom = new StockRoom();
        }
        return stockRoom;
    }

    public static void saveStockRoom(StockRoom stockRoom) {
        FileOutputStream f;
        try {
            f = new FileOutputStream(new File(FILE_NAME));

            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write object to file
            o.writeObject(stockRoom.getGoodsList());
            System.out.println("Stockroom saved successfully!");

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
