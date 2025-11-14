Прв колоквиум

Да се имплемнтира генеричка класа MinMax од два споредливи објекти (минимум/максимум). За класата да се имплементираат:

MinMax()-default конструктор
void update(T element) - метод за ажурирање на тековните минимум/максимум.
T max() - го враќа најголемиот елемент
T min() - го враќа најмалиот елемент
да се преоптовари методот toString() кој враќа стринг составен од минималниот и максималниот елемент и бројот на елементи обработени во методот update кои се различни од тековниот минимум/максимум, разделени со празно место.
Во класата не смеат да се чуваат елементите кои се обработуваат во методот update, освен тековниот минимум/максимум.

For example:










package Naprendo;

import java.util.Scanner;

class MinMax<T extends Comparable<T>> {

    private T min;
    private T max;
    private int countDifferent;

    public MinMax() {
        min = null;
        max = null;
        countDifferent = 0;
    }

    public void update(T element) {
        if (min == null && max == null) {
            // прв елемент
            min = element;
            max = element;
            return;
        }

        if (element.compareTo(min) < 0) {

            min = element;
        } else if (element.compareTo(max) > 0) {

            max = element;
        } else {

            countDifferent++;
        }
    }

    public T min() {
        return min;
    }

    public T max() {
        return max;
    }

    @Override
    public String toString() {

        return min + " " + max + " " + countDifferent;
    }
}



public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}
