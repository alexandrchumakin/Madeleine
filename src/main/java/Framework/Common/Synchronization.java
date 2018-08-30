package Framework.Common;

public class Synchronization {
    public static void WaitPageSource(){
        try{
            for(int i = 0; i < 20; i++) {
                String currentSource = Driver.CurrentDriver.getPageSource();
                Thread.sleep(500);
                String newSource = Driver.CurrentDriver.getPageSource();
                if (currentSource.equals(newSource))
                    break;
                else
                    Thread.sleep(500);
            }
        }
        catch (InterruptedException ex){
            System.out.println(StringHelper.formatMessage(ex));
        }
    }
}
