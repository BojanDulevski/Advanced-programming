3.1 Калкулатор
Да се напише програма — едноставен калкулатор.
Калкулаторот чува еден број од тип double со име result, чија почетна вредност е 0.0.
Во циклус се дозволува на корисникот да:

додава (+)
одзема (-)
множи (*)
дели (/)
со втор број.
Резултатот од овие операции станува новата вредност на result.
Пресметките завршуваат кога корисникот ќе внесе R (големо или мало) за result.
Потоа корисникот може да започне нова пресметка од почеток или да ја заврши програмата (Y/N).

Ако корисникот внесе оператор различен од +, -, * или /, тогаш се фрла исклучок UnknownOperatorException и се бара повторно внес.

Пример форматот на влезните податоци:

Calculator is on.
result = 0.0
+5
result + 5.0 = 5.0
new result = 5.0
* 2.2
result * 2.2 = 11.0
updated result = 11.0
% 10
% is an unknown operation.
Reenter, your last line:
* 0.1
result * 0.1 = 1.1
updated result = 1.1
r
Final result = 1.1
Again? (y/n)
yes
result = 0.0
+10
result + 10.0 = 10.0
new result = 10.0
/2
result / 2.0 = 5.0
updated result = 5.0
r
Final result = 5.0
Again? (y/n)
N
End of Program










import java.util.*;
class Calculator {
    private double result;
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';

    public Calculator() {
        result = 0;
    }

    public String init() {
        return String.format("result = %f", result);
    }

    public double getResult() {
        return result;
    }

    public String execute(char operator, double value)
            throws UnknownOperatorException {

        if (operator == PLUS) {
            result += value;
        } else if (operator == MINUS) {
            result -= value;
        } else if (operator == MULTIPLY) {
            result *= value;
        } else if (operator == DIVIDE) {
            result /= value;
        } else {
            throw new UnknownOperatorException(operator);
        }
        return String.format("result %c %f = %f", operator, value, result);
    }

    class UnknownOperatorException extends Exception {
        public UnknownOperatorException(char operator) {
            super(String.format("%c is unknown operation", operator));
        }
    }

    @Override
    public String toString() {
        return String.format("updated result = %f", result);
    }

}

public class CalculatorTest {
    static final char RESULT = 'r';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Calculator calculator = new Calculator();
            System.out.println(calculator.init());
            while (true) {
                String line = scanner.nextLine();
                char choice = getCharLower(line);
                if (choice == RESULT) {
                    System.out.println(String.format("final result = %f", calculator.getResult()));
                    break;
                }
                String[] parts = line.split("\\s+");
                char operator = parts[0].charAt(0);
                double value = Double.parseDouble(parts[1]);
                try {
                    String result = calculator.execute(operator, value);
                    System.out.println(result);
                    System.out.println(calculator);
                } catch (Calculator.UnknownOperatorException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("(Y/N)");
            String line = scanner.nextLine();
            char choice = getCharLower(line);
            if (choice == 'n') {
                break;
            }
        }
    }

    static char getCharLower(String line) {
        if (line != null && !line.isEmpty()) {
            return Character.toLowerCase(line.charAt(0));
        }
        return '?'; 
    }


}
