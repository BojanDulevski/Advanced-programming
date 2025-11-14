Квалификациска за прв колоквиум

Да се дефинира класа ShapesApplication во која се чуваат податоци за повеќе прозорци на кои се исцртуваат геометриски слики во форма на квадрат.

За класата да се дефинира:

ShapesApplication() - конструктор
int readCanvases (InputStream inputStream) - метод којшто од влезен поток на податоци ќе прочита информации за повеќе прозорци на кои се исцртуваат квадрати. Во секој ред од потокот е дадена информација за еден прозорец во формат: canvas_id size_1 size_2 size_3 …. size_n, каде што canvas_id е ИД-то на прозорецот, а после него следуваат големините на страните на квадратите што се исцртуваат во прозорецот. Методот треба да врати цел број што означува колку квадрати за сите прозорци се успешно прочитани.
void printLargestCanvasTo (OutputStream outputStream) - метод којшто на излезен поток ќе го испечати прозорецот чии квадрати имаат најголем периметар. Печатењето да се изврши во форматот canvas_id squares_count total_squares_perimeter.

import java.io.*;
import java.util.*;

class ShapesApplication {


    private Map<String, List<Integer>> canvases;

    public ShapesApplication() {
        canvases = new LinkedHashMap<>();
    }

    public int readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int totalSquares = 0;
        try {
            while ((line = br.readLine()) != null && !line.isEmpty()) {

                String[] parts = line.trim().split("\\s+");

                String canvasId = parts[0];
                List<Integer> squares = new ArrayList<>();

                for (int i = 1; i < parts.length; i++) {
                    int size = Integer.parseInt(parts[i]);
                    squares.add(size);
                }

                canvases.put(canvasId, squares);
                totalSquares += squares.size();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalSquares;
    }

    public void printLargestCanvasTo(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);

        String bestCanvasId = null;
        int bestSquaresCount = 0;
        int bestTotalPerimeter = -1;

        for (Map.Entry<String, List<Integer>> entry : canvases.entrySet()) {

            String id = entry.getKey();
            List<Integer> squares = entry.getValue();

            int count = squares.size();
            int totalPerimeter = 0;

            for (int side : squares) {
                totalPerimeter += 4 * side;  
            }

            if (totalPerimeter > bestTotalPerimeter) {
                bestTotalPerimeter = totalPerimeter;
                bestSquaresCount = count;
                bestCanvasId = id;
            }
        }

        if (bestCanvasId != null) {
            pw.printf("%s %d %d", bestCanvasId, bestSquaresCount, bestTotalPerimeter);
        }
        pw.flush();
    }
}

public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}
