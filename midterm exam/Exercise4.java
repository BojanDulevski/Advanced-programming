Имплементирајте систем за кино - MovieTheater. Киното ќе води листа од филмови кои ќе може да се сортираат по наслов, година и рејтинзи.

Класа Movie

title: String - наслов
genre: String - жанр
year: int - година на издавање
avgRating: double - процечна оцена
Да се имплементира toString() кој ќе го печати филмот во следниот формат:
Наслов, Жанр, Година, Оцена

Класа MovieTheater

movies: ArrayList<Movie> - листа на филмови во Киното
Методи:

readMovies(InputStream is) - метод за читање од InputStream кој ќе ги додава филмовите директно во листата со BufferedReader
printByGenreAndTitle() - ги прикажува филмовите сортирани според жанр, па според наслов
printByYearAndTitle() - ги прикажува филмовите сортирани според година, па според наслов
printByRatingAndTitle() - ги прикажува филмовите сортирани според оцена, па според наслов



package Naprendo;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Comparator;


class Movie{
    String title;
    String genre;
    int year;
    double avgReating;
    public Movie(String title, String genre, int year, double avgReating){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.avgReating = avgReating;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public int getYear() {
        return year;
    }
    public double getAvgReating() {
        return avgReating;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", title, genre, year, avgReating);
    }
}

class MovieTheater {
    ArrayList<Movie> movies;

    public MovieTheater() {
        movies = new ArrayList<>();
    }


    public void readMovies(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String title = br.readLine();
            String genre = br.readLine();
            int year = Integer.parseInt(br.readLine());

            String[] ratingsStr = br.readLine().split(" ");
            double sum = 0;
            for (String r : ratingsStr) {
                sum += Integer.parseInt(r);
            }
            double avg = sum / ratingsStr.length;

            movies.add(new Movie(title, genre, year, avg));
        }
    }

    public void printByGenreAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getGenre)
                        .thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }


    public void printByYearAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getYear)
                        .thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }

    public void printByRatingAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getAvgReating).reversed()
                        .thenComparing(Movie::getTitle))
                .forEach(System.out::println);


    }
}


    public class MovieTheaterTester {
        public static void main(String[] args) {
            MovieTheater mt = new MovieTheater();
            try {
                mt.readMovies(System.in);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("SORTING BY RATING");
            mt.printByRatingAndTitle();
            System.out.println("\nSORTING BY GENRE");
            mt.printByGenreAndTitle();
            System.out.println("\nSORTING BY YEAR");
            mt.printByYearAndTitle();
        }
    }




