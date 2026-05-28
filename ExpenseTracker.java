package personalexpensetracker;

import java.util.*;
import java.io.*;

class Expense {

    String category;
    double amount;
    String date;

    Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    void display() {
        System.out.println(date + "  " + category + "  " + amount);
    }
}

public class ExpenseTracker {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        ArrayList<Expense> list = new ArrayList<>();

        System.out.print("Enter number of expenses: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter Category: ");
            String category = sc.nextLine();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter Date: ");
            String date = sc.nextLine();

            list.add(new Expense(category, amount, date));
        }

        System.out.println("\nAll Expenses");

        for (Expense e : list) {
            e.display();
        }

        double total = 0;

        HashMap<String, Double> map = new HashMap<>();

        for (Expense e : list) {

            total += e.amount;

            map.put(e.category,
                    map.getOrDefault(e.category, 0.0) + e.amount);
        }

        System.out.println("\nMonthly Expense Report");
        System.out.println("Total Expense = " + total);

        String maxCategory = "";
        double max = 0;

        for (String key : map.keySet()) {

            if (map.get(key) > max) {

                max = map.get(key);
                maxCategory = key;
            }
        }

        System.out.println("Highest Expense Category: "
                + maxCategory + " = " + max);

        FileWriter fw = new FileWriter("expenses.txt");

        for (Expense e : list) {

            fw.write(e.date + " "
                    + e.category + " "
                    + e.amount + "\n");
        }

        fw.close();

        System.out.println("\nExpense data saved to expenses.txt");

        sc.close();
    }
}
