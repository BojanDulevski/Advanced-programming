2. Priority Queue (Ред на чекање со приоритет)
Да се имплементира класа за податочна структура PriorityQueue со помош на ArrayList. PriorityQueue е податочна структура во која секој елемент е придружен заедно со неговиот приоритет (цел број). Приоритет да се дефинира така што оние елементи со најголема вредност имаат повисок приоритет. Класата треба да ги имплементира следните методи:

add(item, priority) - додава нов елемент со асоциран приоритет
remove() - го враќа елементот со најголем приоритет и го брише од редот. Ако редот е празен се враќа null.
Пример:

q.add("X", 10);
q.add("Y", 1);
q.add("Z", 3);
System.out.println(q.remove()); // Returns X
System.out.println(q.remove()); // Returns Z
System.out.println(q.remove()); // Returns Y
Тестирајте го редот со податоци со приоритет во различен редослед (пр., растечки, опаѓачки, мешан). Редот може да се имплементира со линеарно пребарување низ ArrayList.



  package Naprendo;

import java.util.ArrayList;
import java.util.Random;


public class PriorityQueue<T>{
    private ArrayList<T> queue;
    private ArrayList<Integer> priorities;

    public PriorityQueue(){
        queue = new ArrayList<>();
        priorities = new ArrayList<>();
    }
    public void add(T item, int priority){
        int i;
        for( i=0 ;i<priorities.size();i++){
            if(priorities.get(i)<priority){
                break;
            }
        }
        queue.add(item);
        priorities.add(i,priority);
    }

    public T remove(){
        if(queue.isEmpty()){
            return null;
        }
        T item= queue.get(0);
        queue.remove(0);
        priorities.remove(0);
        return item;

    }

    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<String>();
        pq.add("X", 0);
        pq.add("Y", 1);
        pq.add("Z", 3);
        System.out.println(pq.remove());
        System.out.println(pq.remove()); 
        System.out.println(pq.remove());
    }
}



  
