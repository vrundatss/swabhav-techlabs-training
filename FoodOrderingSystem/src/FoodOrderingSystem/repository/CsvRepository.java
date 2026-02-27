package FoodOrderingSystem.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvRepository<T> {

    protected final String filePath;
    protected final String header;

    protected CsvRepository(String filePath, String header) {
        this.filePath = filePath;
        this.header = header;
    }

    protected abstract T fromRow(String[] row);
    protected abstract String[] toRow(T obj);

    protected List<String[]> readAllRows() {
        List<String[]> rows = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return rows;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) { skipHeader = false; continue; }
                if (line.trim().isEmpty()) continue;
                rows.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    protected void writeAllRows(List<String[]> rows) {
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

    public List<T> getAll() {
        List<String[]> rows = readAllRows();
        List<T> list = new ArrayList<>();
        for (String[] r : rows) {
            T obj = fromRow(r);
            if (obj != null) list.add(obj);
        }
        return list;
    }

    public void saveAll(List<T> list) {
        List<String[]> rows = new ArrayList<>();
        for (T obj : list) rows.add(toRow(obj));
        writeAllRows(rows);
    }

    public void add(T obj) {
        List<T> all = getAll();
        all.add(obj);
        saveAll(all);
    }

//    public void update(T updated) {
//        List<T> all = getAll();
//        for (int i = 0; i < all.size(); i++) {
//            if (getIdOf(all.get(i)).equals(getIdOf(updated))) {
//                all.set(i, updated);
//                saveAll(all);
//                return;
//            }
//        }
//    }
}