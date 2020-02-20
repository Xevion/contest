import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

class Budget {
    private ArrayList<Item> list;
    private int budget;

    Budget(ArrayList<Item> list, int budget) {
        this.list = (ArrayList<Item>) list.clone();
        this.budget = budget;
    }

    // Output costliest affordable item
    private Item q1() {
        Item item = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).price <= this.budget && list.get(i).price > item.price)
                item = list.get(i);
        }
        return item;
    }

    // Count affordable prizes
    private int q2() {
        int i = 0;
        for (Item item : list)
            if (item.price <= budget)
                i++;
        return i;
    }

    // Buy most prizes
    private int q3() {
        int n = 0;
        int curbudget = this.budget;
        for (Item item : list) {
            if (curbudget >= item.price) {
                curbudget -= item.price;
                n++;
                // out.println(String.format(">>> %s - %s (~$%s)", item.name, item.price, curbudget));
            }
        }
        return n;
    }

    public static ArrayList<Item[]> combinations(ArrayList<Item> list, int length) {
        ArrayList<Item[]> combos = new ArrayList<Item[]>();
        Item[] curcombo = new Item[length];
        Budget.combos(list, combos, curcombo, 0, length, 0, list.size() - 1);
        return combos;
    }

    private static void combos(ArrayList<Item> originalList, ArrayList<Item[]> combosList, Item[] curcombo,
            int curIndex, int length, int start, int end) {
        if (curIndex == length) {
            combosList.add(curcombo);
        } else {
            for (int i = start; i <= end && end - i + 1 >= length - curIndex; i++) {
                curcombo[curIndex] = originalList.get(i);
                Budget.combos(originalList, combosList, curcombo, curIndex + 1, length, start + 1, end);
            }
        }
    }

    // Spend most money with best combo
    private int q4() {
        return 0;
    }

    public void evaluate() {
        Item a = q1();
        out.println(String.format("%s $%s", a.name, a.price));
        out.println(q2());
        out.println(q3());
        out.println(String.format("$%s", q4()));
    }
}

class Item implements Comparable<Item> {
    String name;
    int price;

    Item(String line) {
        int index = line.lastIndexOf(" ");
        this.name = line.substring(0, index);
        this.price = Integer.parseInt(line.substring(index + 1 + 1, line.length()));
    }

    public String toString() {
        return String.format("Item(%s, $%s)", this.name, this.price);
    }

    public int compareTo(Item other) {
        return this.price - ((Item) other).price;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("spending.dat"));
        int lines = s.nextInt();
        s.nextLine();

        // Aggregate all items
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < lines; i++) {
            items.add(new Item(s.nextLine()));
        }

        Collections.sort(items);
        Collections.reverse(items);

        for (Item[] sub : Budget.combinations(items, 3))
            out.println(Arrays.toString(sub));

        // Build all budgets, evaluate 4 questions
        ArrayList<Budget> budgets = new ArrayList<Budget>();
        while (s.hasNextInt()) {
            Budget budget = new Budget(items, s.nextInt());
            budget.evaluate();
        }
    }
}