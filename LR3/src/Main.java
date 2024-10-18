import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Task implements Runnable {
    private int[][] matrix;
    private int[][] vector;
    private int[][] result;
    private int row;
    private int column;
    private int num;

    public Task(int[][] matrix, int[][] vector, int[][] result, int row, int column, int num) {
        this.matrix = matrix;
        this.vector = vector;
        this.result = result;
        this.row = row;
        this.column = column;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            result[row][column] += matrix[row][i] * vector[i][column];
        }
    }


    public static class Main {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            String filePath = "matrix_and_vector.txt"; // Путь к файлу с матрицами
            int[][] matrix = new int[0][0];
            int[][] vec = new int[0][0];
            try {
                // Чтение и вывод первой матрицы
                matrix = MatrixReader.readMatrixFromFile(filePath, 1);
                System.out.println("Первая матрица:");
                MatrixReader.printMatrix(matrix);

                // Чтение и вывод второй матрицы
                vec = MatrixReader.readMatrixFromFile(filePath, 2);
                System.out.println("Вектор:");
                MatrixReader.printMatrix(vec);

            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
            int rows = matrix.length;
            int count = matrix[0].length;
            int columns = vec[0].length;
            int threadCount = 10;
            int[][] result = new int[rows][columns];
            NumberFormat f = NumberFormat.getInstance();

            for (int i = 1; i <= threadCount; i++) {
                result = new int[rows][columns];
                long startTime = System.nanoTime();
                ExecutorService executorService = Executors.newFixedThreadPool(i);
                List<Future<?>> futures = new ArrayList<>();

                for (int row = 0; row < rows; row++) {
                    for (int column = 0; column < columns; column++) {
                        //for (int num = 0; num < count; num++) {
                        futures.add(executorService.submit(new Task(matrix, vec, result, row, column, count)));
                        //}
                    }
                }
                for (Future<?> future : futures) {
                    future.get();
                }
                executorService.shutdown();


                long endTime = System.nanoTime();
                System.out.println("потоки - " + i + " время - " + f.format(endTime - startTime) + " ns");
            }

            // Печать результата после завершения всех задач
            System.out.println("Результат умножения:");
            MatrixReader.printMatrix(result);

        }
    }
}