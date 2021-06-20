import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WriteDataToCSV {

    public void writeData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("/Users/manrajsingh/Downloads/archive/DataCleaning/olist_sellers_dataset.csv"));
        BufferedReader csvReader2 = new BufferedReader(new FileReader("/Users/manrajsingh/Downloads/archive/olist_geolocation_dataset.csv"));
        FileWriter csvWriter = new FileWriter("/Users/manrajsingh/Downloads/archive/DataCleaning/olist_geolocation_dataset.csv");
        Map<String, Integer> rowData = new HashMap<>();
        Map<String, Boolean> touchedRow = new HashMap<>();

        String row;
        int t=0, totalcount = 0;

        boolean firstRow = true;
        int dataLocation = 1;
        while ((row = csvReader.readLine()) != null) {
            if(firstRow){
                firstRow = false;
                continue;
            }

            int randomNo = (int)(Math.random()*100000)%4 + 1;
            String[] data = row.split(",");
            if(!rowData.containsKey(data[dataLocation])){
                rowData.put(data[dataLocation], randomNo);
                touchedRow.put(data[dataLocation], false);
                System.out.println(++t + ".)  " + row + "   (." + totalcount );
            }
        }
        t=0; totalcount=0;
        dataLocation = 0;
        while ((row = csvReader2.readLine()) != null) {
            ++totalcount;
            String[] data = row.split(",");
            if(data.length<= dataLocation){
                System.out.println("\n\n\n\t\t\tData With Error"+ (t) + ".)  " + row + "   (." + totalcount +"\n\n");
                continue;
            }
            if(t==0){
                csvWriter.append(row);
                csvWriter.append("\n");
                System.out.println(++t + ".)  " + row + "   (." + totalcount );

            }
            else{
                String dataValue = data[dataLocation];
                if(rowData.containsKey(dataValue)){
                    if(rowData.get(dataValue) >0){
                        csvWriter.append(row);
                        touchedRow.put(dataValue, true);
                        csvWriter.append("\n");
                        rowData.put(dataValue, rowData.get(dataValue)-1);
                        System.out.println(++t + ".)  " + row + "   (." + totalcount );

                    }

                }
            }

        }
        csvReader.close();
        csvWriter.flush();
        csvWriter.close();
        t=0;
        System.out.println("\n\n\n\t\t\tNot Touched Rows"+"\n\n");
        for(Map.Entry<String, Boolean> iterator : touchedRow.entrySet()){
            if(!iterator.getValue()){
                System.out.println(++t + ".)\t\t"+ iterator.getKey());
            }
        }
    }


}
