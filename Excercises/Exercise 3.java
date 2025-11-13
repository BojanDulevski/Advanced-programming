Да се имплементира систем за управување со студентите на еден предмет преку имплементација на следните класи и методи:

1. Класа Student

Претставува еден студент со основни информации: индекс, име, оцена и присуство.
Индексот треба да биде деклариран како final.
Имплементирај метод toString кој ќе го прикажува студентот во читлив формат (име, индекс, оцена и присуство).
Осигурај дека при промена на оцената, вредноста секогаш останува во опсег од 5 до 10.
2. Класа Course

Претставува универзитетски курс кој управува со низa од студенти.
Курсот треба да има наслов, низа на студенти и бројач кој означува колку студенти моментално се запишани.
Имплементирај ги следните методи:
enroll(Supplier<Student> supplier)
Додава нов студент во курсот, користејќи го Supplier<Student>, доколку има слободно место.
forEach(Consumer<Student> action)
Применува зададена акција (Consumer) врз секој студент во низата (на пример, печатење).
count(Predicate<Student> condition)
Го враќа бројот на студенти кои го исполнуваат дадениот услов.
findFirst(Predicate<Student> condition)
Го враќа првиот студент кој го исполнува условот или null ако нема таков.
filter(Predicate<Student> condition)
Враќа нова низа која ги содржи само студентите кои го исполнуваат условот.
mapToLabels(Function<Student, String> mapper)
Враќа низа од текстуални описи, добиени со трансформирање на секој студент со дадената функција.
mutate(Consumer<Student> mutator)
Применува промена на сите студенти (на пример, зголемување на оцената).
conditionalMutate(Predicate<Student> condition, Consumer<Student> mutator)
Ја применува промената само на студентите кои го исполнуваат дадениот услов.
toString()
Враќа текстуален опис на курсот, кој ги содржи насловот, бројот на запишани студенти и списокот на сите студенти.
3. Класа CourseDemo

Содржи main метод во кој се демонстрира целата функционалност.
Отвори Scanner и прочитај цел број n што го означува бројот на студенти кои ќе се внесат.
Креирај Supplier<Student> кој чита податоци за еден студент од конзолата (индекс, име, оцена, присуство) и враќа нов објект Student.
Запиши n студенти во курсот користејќи го методот enroll.
Користи Consumer<Student> заедно со forEach за да ги испечатиш сите запишани студенти.
Дефинирај Predicate<Student> за студенти кои:
имаат оцена поголема или еднаква на 6
имаат присуство најмалку 70%
Комбинирај ги двете состојби со .and() и користи го методот filter за да ги прикажеш само тие студенти.
Користи findFirst за да го пронајдеш и прикажеш првиот студент со оцена поголема или еднаква на 9.
Користи mutate за да ја зголемиш оцената на сите студенти за 1.
Користи conditionalMutate за да ја зголемиш оцената за 1 само на студентите со присуство поголемо или еднакво на 90%.
Користи mapToLabels за да ги трансформираш сите студенти во текстуални описи и испечати ги.
На крај, испечати ги сите информации за курсот со користење на методот toString.

  
//package Napredno;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

class Student {
    private final String index;
    private String name;
    private int attendance;
    private int grade;
 public Student(String index, String name, int attendance, int grade) {
        this.index = index;
        this.name = name;
        this.attendance = attendance;
        this.grade = grade;
 }
 public String getIndex() {
     return  index;
 }
 public String getName() {
     return  name;
 }
 public int getAttendance() {
     return  attendance;
 }
 public int getGrade() {
     return  grade;
 }
 public void setGrade(int grade) {
     if(grade>10){
         grade=10;
     }
     if(grade<5){
         grade=5;
     }
     this.grade = grade;
 }

    @Override
    public String toString() {
        return "Student{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", attendance=" + attendance +
                ", grade=" + grade +
                '}';
    }

}
class Course{
    private  final Student[] students;
    private final String title;
    private int size=0;
    public Course(String title, int capacity) {
        this.students =new Student[capacity];
        this.title = title;
        this.size=students.length;
    }

    public Student[] getStudents() {
        return students;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public boolean enroll(Supplier<Student> supplier){
        if(size==students.length){
            return false;
        }
        students[size++]=supplier.get();
        return true;
    }
    public void forEach(Consumer<Student> consumer){
        for(int i=0;i<size;i++){
            consumer.accept(students[i]);
        }
    }
    public int count(Predicate<Student> predicate){
        int c=0;
        for(int i=0;i<size;i++){
            if(predicate.test(students[i])){
                c++;
            }
        }
        return c;
    }

    public Student findFirst(Predicate<Student> predicate){
        for(int i=0;i<size;i++){
            if(predicate.test(students[i])){
                return students[i];
            }
        }
        return null;
    }
    public Student[] filter(Predicate<Student> predicate){
        int matches=count(predicate);
        Student[]out=new Student[matches];
        int j=0;
        for(int i=0;i<size;i++){
            if(predicate.test(students[i])){
                out[j++]=students[i];
            }
        }
        return out;
    }
    public String[] maptoLabels(Function<Student,String>mapper){
        String[] out=new String[size];
        for(int i=0;i<size;i++){
            out[i]=mapper.apply(students[i]);

        }
        return out;
    }
    public void mutate(Consumer<Student> mutator) {
        for (int i = 0; i < size; i++) {
            mutator.accept(students[i]);
        }
    }

    public void conditionalMutate(Predicate<Student> condition, Consumer<Student> mutator) {
        for (int i = 0; i < size; i++) {
            if (condition.test(students[i])) {
                mutator.accept(students[i]);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course: " + title + " (" + size + "/" + students.length + " students)");
        for (Student student : students) {
            sb.append(student.toString()).append("\n");
        }
        return sb.toString();

    }
}


public class CourseDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Course se = new Course("Software Engineering", 10);

        int n = sc.nextInt();

      
        Supplier<Student> studentFromInput = () -> {
            System.out.print("Enter student (index name grade attendance): ");
            String index = sc.next();
            String name = sc.next();
            int grade = sc.nextInt();
            int attendance = sc.nextInt();
            sc.nextLine(); 
            return new Student(index, name, grade, attendance);
        };

        
        for (int i = 0; i < n; i++) {
            se.enroll(studentFromInput);
        }

        sc.close(); // close scanner after done
        System.out.println("\nEnrolled students:");
        se.forEach(System.out::println);

        
        System.out.println("\n=== All Students ===");
        Consumer<Student> printer = System.out::println;
        se.forEach(printer);

      
        Predicate<Student> isPassing = s -> s.getGrade() >= 6;
        Predicate<Student> goodAttendance = s -> s.getAttendance() >= 70;
        Predicate<Student> passingAndPresent = isPassing.and(goodAttendance);

        System.out.println("\n=== Students with passing grade and good attendance ===");
        Student[] passing = se.filter(passingAndPresent);
        for (Student s : passing) System.out.println(s);

        
        System.out.println("\n=== First honor student (grade >= 9) ===");
        Student honor = se.findFirst(s -> s.getGrade() >= 9);
        System.out.println(honor != null ? honor : "None found");

        
        System.out.println("\n=== Curving all grades by +1 (max 10) ===");
        Consumer<Student> curve = s -> s.setGrade(s.getGrade() + 1);
        se.mutate(curve);
        se.forEach(printer);

        
        System.out.println("\n=== Curving high attendance students' grades by +1 ===");

        se.conditionalMutate(
                s -> s.getAttendance()>=90,
                s -> s.setGrade(s.getGrade()+1)
        );
    }
}
