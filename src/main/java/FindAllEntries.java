import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FindAllEntries {

    public void getAllEntries() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("/Users/manrajsingh/Downloads/archive/DataCleaning/olist_sellers_dataset.csv"));
        Map<String, Integer> rowData = new HashMap<>();
        Map<String, Boolean> touchedRow = new HashMap<>();

        String row;
        int t=0;
        int totalcount = 0;

        boolean firstRow = true;
        while ((row = csvReader.readLine()) != null) {
            totalcount++;
            if (firstRow) {
                firstRow = false;
                continue;
            }
            int dataLocation = 1;
            int randomNo = (int) (Math.random() * 100000) % 6 + 25;
            String[] data = row.split(",");
            if(!rowData.containsKey(data[dataLocation])){
                rowData.put(data[dataLocation], randomNo);
                touchedRow.put(data[dataLocation], false);
                System.out.println(++t + ".)  " + row + "   (." + totalcount );
            }
        }
    }
}
