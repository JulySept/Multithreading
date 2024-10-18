import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MatrixReader {

    // Метод для чтения размеров матрицы (строки и столбцы)
    public static int[] readDimensions(BufferedReader br) throws IOException {
        String[] dimensions = br.readLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        return new int[]{rows, cols};
    }

    // Метод для чтения матрицы
    public static int[][] readMatrix(BufferedReader br, int rows, int cols) throws IOException {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] values = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }
        return matrix;
    }

    // Метод для вывода матрицы на экран
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Метод для чтения матрицы из файла (объединенный метод для удобства)
    public static int[][] readMatrixFromFile(String filePath, int matrixNumber) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Пропускаем строки до нужной матрицы
            for (int i = 1; i < matrixNumber; i++) {
                int[] dimensions = readDimensions(br);
                for (int j = 0; j < dimensions[0]; j++) {
                    br.readLine(); // Пропуск строк матрицы
                }
            }
            // Чтение нужной матрицы
            int[] dimensions = readDimensions(br);
            return readMatrix(br, dimensions[0], dimensions[1]);
        }
    }
}
