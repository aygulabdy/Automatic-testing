package model;

public class Item {
    private String itemName;

    private String itemURL;

    private int itemCount;
    public Item(String itemName, String itemURL, int itemCount) {
        this.itemName = itemName;
        this.itemURL = itemURL;
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
