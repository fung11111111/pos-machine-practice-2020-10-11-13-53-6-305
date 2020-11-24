package pos.machine;

import java.util.*;

public class PosMachine {
    private int total = 0;

//       P : 2 mins
//       D : 2 mins
//       C : check whether the return result is correct according to the requirement
//       A : fix the bug and test again

    public String printReceipt(List<String> barcodes) {

        List<ItemInfo> itemsInCart = createItemByBarcodes(barcodes);
        Map<String, Integer> itemSubToltalNum = getSubTotalNumOfItem(itemsInCart);
        String itemReceipt = getEachItemReceipt(itemSubToltalNum);

        return generateFinalReceipt(itemReceipt);
    }


//    P : 1 mins
//    D : 2 mins
//    C : check whether the count of each item is correct
//    A : fix the bug and test again
    private Map<String, Integer> getSubTotalNumOfItem(List<ItemInfo> itemsInCart) {
        Map<String, Integer> itemSubToltalNum = new HashMap<>();

        for (ItemInfo itemInfo : itemsInCart) {
            if (itemSubToltalNum.containsKey(itemInfo.getName())) {
                itemSubToltalNum.replace(itemInfo.getName(), itemSubToltalNum.get(itemInfo.getName()) + 1);
            } else {
                itemSubToltalNum.put(itemInfo.getName(), 1);
            }
        }

        return itemSubToltalNum;
    }
    //P:1 mins
//    D : 2 mins
//    C : check whether each receipt string is correct
//    A : fix the bug and test again if needed
    private String getEachItemReceipt(Map<String, Integer> itemSubToltalNum) {
        int subTotal = 0;
        List<ItemInfo> intemInfos = ItemDataLoader.loadAllItemInfos();
        String itemReceipt = "";
        for (ItemInfo item : intemInfos) {
            if (itemSubToltalNum.get(item.getName()) != null) {
                subTotal = item.getPrice() * itemSubToltalNum.get(item.getName());
                addTotalCharge(subTotal);
                String itemString = "Name: " + item.getName() + ", Quantity: " + itemSubToltalNum.get(item.getName()) +
                        ", Unit price: " + item.getPrice() + " (yuan), Subtotal: " + subTotal + " (yuan)\n";
                itemReceipt += itemString;
            }
        }
        return itemReceipt;
    }
    //P:1 mins
//    D : 2 mins
//    C : check whether final receipt is correct and get correct total amount
//    A : fix the bug and test again if needed
    private String generateFinalReceipt(String itemReceipt) {
        String header = "***<store earning no money>Receipt***\n";
        String divider = "----------------------\n";
        String footer = "**********************";
        String totalCharge = "Total: " + getTotal() + " (yuan)\n";
        return header + itemReceipt + divider + totalCharge + footer;
    }

    //P:1 mins
//    D : 2 mins
//    C : check whether the input itme can get the related infomation
//    A : fix the bug and test again if needed
    private List<ItemInfo> createItemByBarcodes(List<String> barcodes) {
        List<ItemInfo> itemList = ItemDataLoader.loadAllItemInfos();
        ArrayList<ItemInfo> itemsInCart = new ArrayList<>();
        for (String barcode : barcodes) {
            for (ItemInfo item : itemList) {
                if (barcode.equals(item.getBarcode())) {
                    itemsInCart.add(item);
                    break;
                }
            }
        }
        return itemsInCart;
    }
    //P:1 mins
//    D : 1 mins
//    C : check whether the total can be added
//    A : fix the bug and test again if needed
    private void addTotalCharge(int subTotal) {
        total += subTotal;
    }
    //P:1 mins
//    D : 1 mins
//    C : check whether the total can be got
//    A : fix the bug and test again if needed
    private int getTotal() {
        return total;
    }
    //P:1 mins
//    D : 1 mins
//    C : check whether the load all item in db
//    A : fix the bug and test again if needed
    private List<ItemInfo> getItemList(List<String> barcodes) {
        return ItemDataLoader.loadAllItemInfos();
    }

}
