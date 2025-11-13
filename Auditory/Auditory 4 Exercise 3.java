Да се имплементира класа 
F1Race која ќе чита од влезен тек (стандарден влез, датотека, …) податоци за времињата од последните 3 круга на неколку пилоти на Ф1 трка. 
  Податоците се во следниот формат: Driver_name lap1 lap2 lap3, притоа lap е во формат mm:ss:nnn каде mm се минути ss се секунди nnn се милисекунди (илјадити делови од секундата).

Пример: Vetel 1:55:523 1:54:987 1:56:134.

Ваша задача е да ги имплементирате методите: - F1Race() - default конструктор - void readResults(InputStream inputStream) - метод за читање на податоците 
  
  - void printSorted(OutputStream outputStream) - метод кој ги печати сите пилоти сортирани според нивното најдобро време (најкраткото време од нивните 3 последни круга) во формат 
  Driver_name best_lap со 10 места за името на возачот (порамнето од лево)
  и 10 места за времето на најдобриот круг порамнето од десно. Притоа времето е во истиот формат со времињата кои се читаат.


  package Naprendo;
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.util.List;



public class Driver implements Comparable<Driver>{
    private String name;
    private List<String >laps;



    public Driver(String name,String lap1,String lap2,String lap3){
        this.name=name;
        this.laps=new ArrayList<>();
        laps.add(lap1);
        laps.add(lap2);
        laps.add(lap3);
    }
    public static Driver craeateDriver(String line){
        String [] parts=line.split("\\s+");
        String name=parts[0];
        String lap1=parts[1];
        String lap2=parts[2];
        String lap3=parts[3];
        return new Driver(name,lap1,lap2,lap3);

    }
    public String getName(){
        return name;
    }
    private static int getLapMillis(String lap) {
        String[] laps = lap.split(":");
        return Integer.parseInt(laps[0]) * 1000 * 60 +
                Integer.parseInt(laps[1]) * 1000 +
                Integer.parseInt(laps[2]);
    }

    public String getBestLap(){
        int minlap=laps.stream().mapToInt(Driver::getLapMillis).min().orElse(0);

        return laps.stream()
                .filter(lap->getLapMillis(lap)==minlap)
                .findFirst()
                .orElseGet(()->laps.getLast());
    }

    @Override
    public int compareTo(Driver o) {
        return getLapMillis(this.getBestLap())-getLapMillis(o.getBestLap());
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s",getName(),getBestLap());
    }

    public static Object createDriver(String s) {
    }
}

package Naprendo;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class F1Race {

    private List<Driver> drivers;

    public void readResults(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        drivers = reader.lines()
                .filter(Objects::nonNull)
                .map(Driver::createDriver)
                .collect(Collectors.toList()).reversed();
    }

    public void printSorted(PrintStream out) {
        PrintWriter writer = new PrintWriter(out);
        Collections.sort(drivers);
        int c = 1;

        for (Object driver : drivers) {
            writer.println(c + ". " + driver.toString());
            c++;
        }
        writer.flush();
        writer.close();
    }

    public void printSortedWithStreams(PrintStream out) {
        PrintWriter writer = new PrintWriter(out);
        Collections.sort(drivers);
        IntStream.range(0, drivers.size())
                .forEach(i -> writer.println(i + ". " + drivers.get(i).toString()));

        writer.flush();
        writer.close();
    }
}


package Napredno;

import Naprendo.F1Race;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }
}
