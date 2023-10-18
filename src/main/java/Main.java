import com.uk.ncl.Tools;
import com.uk.ncl.entity.Client;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Date date = new Date();
        Client c = new Client("Haitao","He", Tools.parseToDate("1999-04-28"));
        System.out.println(c.getBirth());
    }
}