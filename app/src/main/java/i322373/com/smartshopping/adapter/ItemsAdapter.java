package i322373.com.smartshopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import i322373.com.smartshopping.dataModel.ItemDataModel;
import i322373.com.smartshopping.R;

/**
 * Created by I322373 on 4/30/2017.
 */

public class ItemsAdapter extends ArrayAdapter<ItemDataModel> {

    private ArrayList<ItemDataModel> itemsList;

    Context mContext;

    public ItemsAdapter(ArrayList<ItemDataModel> data, Context context) {
        super(context, R.layout.item_layout, data);
        this.itemsList = data;
        this.mContext = context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView  itemName;
        TextView  itemPrice;
        ImageView itemImage;
        TextView  location;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ItemDataModel dataModel = getItem(position);

        ViewHolder viewHolder = new ViewHolder();

        View result;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        result = inflater.inflate(R.layout.item_layout, null);
        viewHolder.itemName = (TextView) result.findViewById(R.id.item_name);
        viewHolder.itemPrice = (TextView) result.findViewById(R.id.item_cost);
        viewHolder.itemImage = (ImageView) result.findViewById(R.id.item_image);
        viewHolder.location  = (TextView) result.findViewById(R.id.item_location);

        viewHolder.itemName.setText(dataModel.getName());
        viewHolder.itemPrice.setText(dataModel.getPrice());
        viewHolder.itemImage.setImageResource(dataModel.getImageResource());
        viewHolder.location.setText(dataModel.getLocation());
        // Return the completed view to render on screen
        return result;
    }
}
