package Framework.Common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringHelper {
    public static String formatMessage(Exception ex){
        return ex.getClass().toString() + ": " + ex.getMessage().substring(0,ex.getMessage().indexOf("\n"));
    }

    public static String parseValue(String value){
        if(value.contains("{generateData}")) {
            String currTime = getCurrentDate();
            value = value.replace("{generateData}", currTime);
            TableHelper.lastGeneratedData = currTime;
        }else if(value.contains("{lastGeneratedData}"))
            value = value.replace("{lastGeneratedData}", TableHelper.lastGeneratedData);
        return value;
    }

    public static String getCurrentDate(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }
}
