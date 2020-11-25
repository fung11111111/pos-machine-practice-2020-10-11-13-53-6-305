package pos.machine;

import java.util.Arrays;

public class Main {
    public static void main(String args[]){

        PosMachine p = new PosMachine();
        String str1 = p.printReceipt(ItemDataLoader.loadBarcodes());
        System.out.println(str1);
    }
}
