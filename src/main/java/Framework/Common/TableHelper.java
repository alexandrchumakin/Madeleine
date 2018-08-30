package Framework.Common;

import java.util.*;

public class TableHelper {
    public List<Map<String,String>> tableData;
    public static String lastGeneratedData;

    public TableHelper(List<Map<String,String>> tableData){
        this.tableData = tableData;
        parseTable();
    }

    public String getValueByTitle(String title, int rowNumber){
        return tableData.get(rowNumber).get(title);
    }

    private void parseTable(){
        //{generateData}
        //{lastGeneratedData}
        List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
        Map<String, String> resultMap = new HashMap<String, String>();
        for (Map<String, String> aTableData : tableData) {
            Collection<String> valuesList = aTableData.values();
            String[] arrayList = valuesList.toArray(new String[valuesList.size()]);
            for(int i = 0; i < arrayList.length; i++){
                arrayList[i] = StringHelper.parseValue(arrayList[i]);
                resultMap.put(aTableData.keySet().toArray()[i].toString(), arrayList[i]);
            }
        }
        resultList.add(resultMap);
        tableData = resultList;
    }

}
