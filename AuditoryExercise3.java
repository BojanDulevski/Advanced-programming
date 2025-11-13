1. Кутија
Да се напише генеричка класа кој симулира исцртување на случаен п
  редмет од некоја кутија. Оваа класа треба да се користи за случајно исцртување. На пример, класата може да содржи листа со имиња и избира едно случајно име,
  или пак листа со броеви за лотарија и избира случајно број. Креирајте метод add за додавање објект од соодветниот тип и метод isEmpty кој проверува дали кутијата е празна. 
    На крај, имплементирајте метод drawItem кој случајно избира објект од кутијата и го враќа како резултат. Ако се обидеме да цртаме со празна кутија се враќа null.

Да се напише main метод кој ја тестира класата.


    import java.util.ArrayList;
import java.util.Random;

public static class Box<T>{
    private ArrayList<T> items;
    public Box() {
        items = new ArrayList<>();
    }

    public void add(T item){
        items.add(item);
    }
    public boolean isEmpty(){
        return items.size()==0;
    }
    public T drawItem(){
        if(isEmpty()){
            return null;
        }
        Random random=new Random();
        return items.get(random.nextInt(items.size()));
    }
}
public static void main(String[] args) {
    Box<String> stringBox = new Box<>();
    stringBox.add("Dexter");
    stringBox.add("Seinfeld");
    stringBox.add("Barney");
    stringBox.add("Sheldon");
    stringBox.add("Costanza");
    stringBox.add("Hank");
    System.out.println(stringBox.drawItem());
    Box<Integer> intBox = new Box<>();
    intBox.add(23);
    intBox.add(15);
    intBox.add(19);
    intBox.add(3);
    intBox.add(92);
    System.out.println(intBox.drawItem());
}
