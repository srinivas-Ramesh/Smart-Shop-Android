package i322373.com.smartshopping.dataModel;

import android.widget.ImageView;

/**
 * Created by I322373 on 4/30/2017.
 */

public class ItemDataModel {

    private String id;
    private String name;
    private String price;
    private int imageResource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
