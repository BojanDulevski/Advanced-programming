Да се напише класа за автомобил Car во која се чува: производител, модел, цена и моќност. Да се имплементира конструктор со следните аргументи Car(String manufacturer, String model, int price, float power).

Потоа да се напише класа CarCollection во која се чува колекција од автомобили. Во оваа класа треба да се имплментираат следните методи: - 
  public void addCar(Car car) - додавање автомобил во колекцијата - public void sortByPrice(boolean ascending) - подредување на колекцијата по цената на автомобилот 
  (во растечки редослед ако аргументот ascending е true, во спротивно, во опаѓачки редослед). Ако цената на автомобилите е иста, сортирањето да се направи според нивната моќноста. 
  - public List filterByManufacturer(String manufacturer) - враќа листа со автомобили од одреден производител (споредбата е според името на производителот без да се води сметка за
  големи и мали букви во името). 
  Автомобилите во оваа листата треба да бидат подредени според моделот во растечки редослед. - public List getList() - ја враќа листата со автомобили од колекцијата.


  package Naprendo;

public class Car {
    String owner;
    String model;
    int price;
    double power;

    public Car(String owner, String model, int price, float power){
        this.owner = owner;
        this.model = model;
        this.price = price;
        this.power = power;
    }

    public String getOwner() {
        return owner;
    }
    public String getModel() {
        return model;
    }
    public int getPrice() {
        return price;
    }
    public double getPower() {
        return power;
    }

  package Naprendo;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class CarCollectionPriceAndPowerComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        if (o1.getPrice() == o2.getPrice())
            return Float.compare(o1.getPrice(), o2.getPrice());
        return Integer.compare(o1.getPrice(), o2.getPrice());
    }
}


    class CarCollectionModelComparator implements Comparator<Car> {
        @Override
        public int compare(Car o1, Car o2) {
            return o1.getModel().compareTo(o2.getModel());
        }
    }

    public class CarCollection{
    private List<Car> carList;


        public CarCollection() {
        this.carList = new ArrayList<>();
    }

    void addCar(Car car){
        carList.add(car);
    }

        public void sortByPrice(boolean ascending){
        if(ascending)
            carList = carList.stream().sorted(new CarCollectionPriceAndPowerComparator()).collect(Collectors.toList());
        else
            carList=carList.stream().sorted(new CarCollectionModelComparator().reversed()).collect(Collectors.toList());
        }

        public List filterByManufacturer(String manufacturer){
        return carList.stream()
                .filter(car -> car.getOwner().equalsIgnoreCase(car.owner))
                .sorted(new CarCollectionModelComparator())
                .collect(Collectors.toList());

        }

        public List<Car> getList() {
            return carList;
        }
    }

}


package Naprendo;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");

            if (parts.length == 1)
                return line;

            String manufacturer = parts[0];
            String model = parts[1];
            int price = Integer.parseInt(parts[2]);
            float power = Float.parseFloat(parts[3]);
            cc.addCar(new Car(manufacturer, model, price, power));
        }
        return "";
    }
}
