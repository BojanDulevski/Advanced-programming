1. Bank (наследување, полиморфизам и интерфејси)
Дадени се следниве пет класи:

Bank
Account

NonInterestCheckingAccount

InterestCheckingAccount

PlatinumCheckingAccount

како и интефејс наречен InterestBearingAccount кои се однесуваат на следниот начин:

Во Bank чува листа од сите видови сметки, вклучувајќи сметки за штедење и за трошење, некои од нив подложни на камата, а некои не. Во Bank постои метод totalAssets кој ја враќа сумата на состојбата на сите сметки. Исто така содржи метод addInterest кој го повикува методот addInterest на сите сметки кои се подложни на камата.

Account е апстрактна класа. Во секој сметка се чуваат името на сопственикот на сметката, бројот на сметката (секвенцијален број доделен автоматски), моменталната состојба. Во класата се имплементираат конструктор за иницијализација на податочните членови, методи за пристап до моменталната состојба, како и за додавање и одземање од моменталната состојба.

InterestBearingAccount интерфејсот декларира единствен метод addInterest (без параметри и не враќа ништо - void) кој ја зголемува состојбата со соодветната камата за овој вид на сметка.

InterestCheckingAccount е сметка Account која е исто така InterestBearingAccount. Повикување addInterest ја зголемува состојбата за 3%.

PlatinumCheckingAccount е InterestCheckingAccount. Повикување addInterest ја зголемува состојбата двојно од каматата за InterestCheckingAccount (колку и да е таа).

NonInterestCheckingAccount е сметка Account но не е InterestBearingAccount. Нема дополнителни функционалности надвор од основните од класата Account.

За оваа задача, потребно е да се имплментира функционалност дадена во претходниот текст:

Пет од шест класи од споменатите формираат хиерархија. За овие класи да се нацрта оваа хиерархија.

Да се имплементира Account.

Да се имплементира NonInterestCheckingAccount.

Да се напише InterestBearingAccount интерфејсот.



package Naprendo;
import java.util.Arrays;



abstract class Account{
    private String name;
    private int number;
    private double currentAmmount;

    public Account(String name,int number,double currentAmmount){
        this.name=name;
        this.number=number;
        this.currentAmmount=currentAmmount;
    }

    public double getCurrentAmmount(){
        return currentAmmount;
    }
    public void addAmount(double ammount){
        currentAmmount+=ammount;
    }
    public void withdraw(double ammount){
        currentAmmount-=ammount;
    }

}
class NonInterestCheckingAccount extends Account{
    public NonInterestCheckingAccount(String name,int number,double currentAmmount){
        super(name,number,currentAmmount);
    }
}

interface  InterestBearingAccount {
    void addInterest();
}

class InterestCheckingAccount extends  Account implements InterestBearingAccount{
    public static final double INTERREST_RATE=.03;
    public InterestCheckingAccount(String name,int number,double currentAmmount){
        super(name,number,currentAmmount);
    }
    @Override
    public void addInterest(){
        addAmount(getCurrentAmmount()*INTERREST_RATE);
    }
}
class PlatinumCheckingAccount extends Account implements  InterestBearingAccount{
    public PlatinumCheckingAccount(String name,int number,double currentAmmount){
        super(name,number,currentAmmount);
    }
    @Override
    public void addInterest(){
        addAmount(getCurrentAmmount()* InterestCheckingAccount.INTERREST_RATE*2);
    }

}

class Bank{
    private Account[] accounts;
    private int totalAcounts;
    private int max;

    public Bank(int max){
        this.totalAcounts=0;
        this.max=max;
        this.accounts=new Account[max];
    }

    public void addAccount(Account account){
        if(totalAcounts==accounts.length){
            accounts=Arrays.copyOf(accounts,max*2);
        }
        accounts[totalAcounts++]=account;
    }

    public double totalAssets(){
        double sum=0;
        for(Account account:accounts){
            sum+=account.getCurrentAmmount();
        }
        return sum;
    }
    public void addInterest(){
        for(Account account:accounts){
            if(account instanceof InterestBearingAccount){
                InterestBearingAccount iba=(InterestBearingAccount) account;
                iba.addInterest();
            }
        }
    }

}

public class Exercise1 {
    public static void main(String[] args) {

        Bank bank = new Bank(3);


        bank.addAccount(new NonInterestCheckingAccount("Petar", 1001, 1000.00));
        bank.addAccount(new InterestCheckingAccount("Ana", 1002, 2000.00));
        bank.addAccount(new PlatinumCheckingAccount("Mila", 1003, 5000.00));


        System.out.println("=== Почетна состојба во банката ===");
        System.out.println("Вкупни средства: " + bank.totalAssets() + " денари");


        bank.addInterest();


        System.out.println("=== По додавање камата ===");
        System.out.println("Вкупни средства: " + bank.totalAssets() + " денари");
    }
}


  

Да се имплементира Bank.

Да се имплементира InterestCheckingAccount.

Да се имплементира PlatinumCheckingAccount.
