package com.garagonic.estimationAssistant.old;

import com.garagonic.estimationAssistant.repository.Goods;
import com.garagonic.estimationAssistant.tools.Fn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private String in;
    private StockRoom stockRoom;

    ConsoleUI() {
        scanner = new Scanner(System.in);
        this.stockRoom = SaveFile.loadStockroom();
    }

    private String read() {
        in = scanner.nextLine();
        return in;
    }

    public void start() {
        try {
            startMainMenu();
        } catch (Exception e) {
            System.out.println("Fatal error occurred!! OMG! ");
            if (stockRoom != null) {
                SaveFile.saveStockRoom(stockRoom);
            }
        }
    }

    public void startMainMenu() {
        boolean run = true;

        while (run) {

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("MENU");
            System.out.println("1 - Add Goods");
            System.out.println("2 - Show All Goods");
            System.out.println("3 - Find goods");
            System.out.println("4 - Remove goods");
            System.out.println("9 - Exit");
            System.out.println();

            read();
            if ("1".equals(in)) {
                addGoodsMenu();
            } else if ("2".equals(in)) {
                showAllGoods();
            } else if ("3".equals(in)) {
                findGoods();
            } else if ("4".equals(in)) {
                removeGoods();
            } else if ("9".equals(in)) {
                SaveFile.saveStockRoom(stockRoom);
                run = false;
            } else {
                System.out.println("invalid input please try again");
            }

        }
    }

    private void removeGoods() {
        int po;
        int wo;
        int so;
        String location;
        //TODO Add date

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Enter PO");
        po = readInt();
        System.out.println("Enter WO");
        wo = readInt();
        System.out.println("Enter SO");
        so = readInt();
        System.out.println("Enter Location (a-b-1)");
        location = read();

        if (po == 0 && wo == 0 && so == 0 && Fn.isStringEmpty(location)) {
            System.out.println("No Valid Search Parameters Provided");
            System.out.println();
        } else {
            List<Goods> searchResults = stockRoom.findGoods(po, wo, so, null, null, location, true);
            if (searchResults.isEmpty()) {
                System.out.println("No Results Found. Stockroom is empty.");
            } else {
                System.out.println();
                showGoodsList(searchResults);
                System.out.println();

                boolean continueRemovingGoods = true;
                while (continueRemovingGoods) {
                    System.out.println("Enter goods Location that you want to remove (a-b-1):");
                    read();
                    if (!stockRoom.findCurrentGoodsByLocation(in, searchResults).isEmpty()) {
                        String tempLocation = in;
                        System.out.println("Are you sure? (Y/N)");
                        read();
                        if (in.equalsIgnoreCase("Y")) {
                            boolean removedSuccessfuly = stockRoom.removeGoodsByLocation(tempLocation, searchResults);
                            if (removedSuccessfuly)
                                System.out.println("Removed Goods successfully");
                        } else {
                            System.out.println();
                            System.out.println("Goods were not removed.");
                        }
                    } else {
                        System.out.println();
                        System.out.println("No goods were found.");
                    }
                    System.out.println();
                    System.out.println("Do you want to continue removing goods? (Y/N)");
                    read();
                    if (!in.equalsIgnoreCase("Y")) {
                        continueRemovingGoods = false;
                    }

                }

            }
        }
    }


    private void addGoodsMenu() {
        Goods newGoods = new Goods();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("ADD GOODS");

        System.out.println("Enter Title");
        newGoods.setTitle(read());
        System.out.println("Enter PO (number)");
        newGoods.setPo(readInt());
        System.out.println("Enter WO (number)");
        newGoods.setWo(readInt());
        System.out.println("Enter SO (number)");
        newGoods.setSo(readInt());
        System.out.println("Enter Ile (one character)");
        newGoods.setRack(read());
        System.out.println("Enter Shelf (one character)");
        newGoods.setShelf(read());
        System.out.println("Enter Shelf Position (number)");
        newGoods.setShelfPosition(readInt());
        System.out.println("Enter Customer name");
        newGoods.setCustomer(read());
        System.out.println("Enter Comment");
        newGoods.setComment(read());

        stockRoom.addGoods(newGoods);
        System.out.println("Goods added successfully!");
        System.out.println();
        showGoodsList(newGoods);
    }

    private void showGoodsList(Goods goods) {
        List<Goods> list = new ArrayList<>();
        list.add(goods);
        showGoodsList(list);
    }

    private void showGoodsList(List<Goods> list) {
        showNamesBar();
        for (Goods goods : list) {
            showGoods(goods);
        }
    }

    private void showNamesBar() {
        System.out.println(String.format("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-40s %-40s", "Location", "Title", "PO"
                , "WO", "SO", "Customer", "Comments", "ID", "In Date", "Out Date"));
    }

    private void showGoods(Goods goods) {
        System.out.println(String.format("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-40s %-40s", goods.getLocation(),
                goods.getTitle(), goods.getPo(), goods.getWo(), goods.getSo(), goods.getCustomer(),
                goods.getComment(), goods.getId(), goods.getInDate(), goods.getOutDate()));
    }

    private void showAllGoods() {
        List<Goods> goodsList = stockRoom.getGoodsList();

        if (goodsList.isEmpty()) {
            System.out.println("No goods available");
        } else {
            System.out.println();
            showGoodsList(goodsList);
            System.out.println();
        }
    }


    private void findGoods() {

        int po;
        int wo;
        int so;
        String customer;
        String title;
        //TODO Add date

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Enter PO");
        po = readInt();
        System.out.println("Enter WO");
        wo = readInt();
        System.out.println("Enter SO");
        so = readInt();
        System.out.println("Enter Customer");
        customer = read();
        System.out.println("Enter Title");
        title = read();

        if (po == 0 && wo == 0 && so == 0 && Fn.isStringEmpty(customer) && Fn.isStringEmpty(title)) {
            System.out.println("No Valid Search Parameters Provided");
            System.out.println();
        } else {
            List<Goods> searchResults = stockRoom.findGoods(po, wo, so, customer, title, null, false);
            if (searchResults.isEmpty()) {
                System.out.println("No Results Found");
            } else {
                System.out.println();
                showGoodsList(searchResults);
                System.out.println();
            }
        }
    }

    private int readInt() {
        String intString = read();
        //TODO rework to use proper validation
        try {
            Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            intString = "";
            System.out.println("!!!Invalid value entered for number, setting value to ZERO!!!");
        }
        return "".equals(intString) ? 0 : Integer.parseInt(intString);
    }


}
