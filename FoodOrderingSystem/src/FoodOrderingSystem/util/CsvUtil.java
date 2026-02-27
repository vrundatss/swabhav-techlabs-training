package FoodOrderingSystem.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<String[]> readCsv(String filePath) {
        List<String[]> rows = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return rows;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) { headerSkipped = true; continue; }
                if (line.trim().isEmpty()) continue;
                rows.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static void writeCsv(String filePath, List<String[]> rows, String header) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(header);
            bw.newLine();
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}