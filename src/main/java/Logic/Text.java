package Logic;

import java.util.ArrayList;

public class Text {
    private ArrayList arrayList = new ArrayList();
    private String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = " ";
        this.text += text + " \n";
    }
    public void AddText(String text) {
        arrayList.add(text);
    }
    public int Size(){
        return arrayList.size();
    }

    public String Show(int index){
        return (String) arrayList.get(index);
    }
}
