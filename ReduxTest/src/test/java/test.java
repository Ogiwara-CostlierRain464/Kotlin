import java.util.ArrayList;

public class test {

    ArrayList<String> list = new ArrayList<>();//ダイヤモンド演算子(草)

    public void t(){
        String str = null;
        str.length();//Null Pointer Exception!
    }
}
