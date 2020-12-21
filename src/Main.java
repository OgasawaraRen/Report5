import jp.ac.uryukyu.ie.e205701.*;

public class Main {
    public static void main(String[] args) {
        String str = "百二十三";
        System.out.println(Parse.kanjiParse(str));
        str = "一";
        System.out.println(Parse.kanjiParse(str));
        str = "二十一億二千二百二十二万二千二百二十二";
        System.out.println(Parse.kanjiParse(str));
    }
}
