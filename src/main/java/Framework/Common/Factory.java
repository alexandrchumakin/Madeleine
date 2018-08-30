package Framework.Common;

public class Factory {
    public static <T> T CreateControl(String pageName, String controlLabel) {
        try {
            String controlType = RepositoryParser.GetControlType(pageName, controlLabel);
            return (T) Class.forName("Framework.Controls." + controlType).getConstructor(String.class, String.class).newInstance(pageName, controlLabel);
        }catch(Exception ex){
            System.out.println(String.format("Cannot init control with page name '%1$s' and control label '%2$s'", pageName, controlLabel) + StringHelper.formatMessage(ex));
        }
        return null;
    }

    public static <T> T CreateControl(String controlLabel) throws Exception {
        return CreateControl(Page.currentPageLabel, controlLabel);
    }

}
