1. Book
Да се напише класа за книга Book во која се чува: наслов, категорија и цена.

Да се имплементира конструктор со следните аргументи Book(String title, String category, float price).

Потоа да се напише класа BookCollection во која се чува колекција од книги. Во оваа класа треба да се имплментираат следните методи: - 
  public void addBook(Book book) - додавање книга во колекцијата - public void printByCategory(String category) - 
  ги печати сите книги од проследената категорија (се споредува стрингот без разлика на мали и големи букви), сортирани според насловот на
  книгата (ако насловот е ист, се сортираат според цената). - 
  public List getCheapestN(int n) - враќа листа на најевтините N книги (ако има помалку од N книги во колекцијата, ги враќа сите).


  package Naprendo;

import java.util.Comparator;

public class Book {
    String title;
    String category;
    float price;

    Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %.2f", title, category, price);
    }
}

package Naprendo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.*;
class BookTitleAndPriceComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getTitle().compareToIgnoreCase(o2.getTitle()) == 0) {
            return Float.compare(o1.getPrice(), o2.getPrice());
        } else
            return o1.getTitle().compareToIgnoreCase(o2.getTitle());
    }
}

class BookPriceAndTitleComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        if (Float.compare(o1.getPrice(), o2.getPrice()) == 0) {
            return o1.getTitle().compareToIgnoreCase(o2.getTitle());
        } else
            return Float.compare(o1.getPrice(), o2.getPrice());
    }
}


public class BookCollection {

    private List<Book> books;

    public BookCollection() {
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printByCategory(String category) {
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getCategory().compareToIgnoreCase(category) == 0) {
                filteredBooks.add(book);
            }
        }

        filteredBooks.sort(new BookTitleAndPriceComparator());

        for (Book book : filteredBooks) {
            System.out.println(book);
        }
    }

    public List getCheapestN(int n) {
        List<Book> cheapestBooks = new ArrayList<>();
        books.sort(new BookPriceAndTitleComparator());

        for(int i=0;i<n;i++){
            cheapestBooks.add(books.get(i));
        }
        return  cheapestBooks;

    }

}

package Naprendo;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(
            Scanner scanner,
            BookCollection collection
    ) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}




