package utils;

public class StringCreator {
    public String createEmptyMessageString(String text) {
        return "Найдено 0 результатов по запросу \"" + text +"\"";
    }

    public String getPriceText(String text){
        String expected = "";
        for(int i = 0; i <= text.length(); i++) {
            if(!text.substring(i,i + 1).equals(" ") && !text.substring(i, i + 1).equals("B")){
                expected += text.substring(i, i + 1);
            }
            if (text.charAt(i) == 'B'){
                break;
            }
        }
        return expected;
    }
}
