package i322373.com.smartshopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import i322373.com.smartshopping.dataModel.ItemDataModel;
import i322373.com.smartshopping.R;

/**
 * Created by I322373 on 4/30/2017.
 */

public class BillAdapter extends ArrayAdapter<ItemDataModel> {

    private ArrayList<ItemDataModel> itemsList;

    Context mContext;

    public BillAdapter(ArrayList<ItemDataModel> data, Context context) {
        super(context, R.layout.item_layout, data);
        this.itemsList = data;
        this.mContext = context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView itemName;
        TextView itemPrice;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ItemDataModel dataModel = getItem(position);

        BillAdapter.ViewHolder viewHolder = new BillAdapter.ViewHolder();

        View result;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        result = inflater.inflate(R.layout.bill_layout, null);
        viewHolder.itemName = (TextView) result.findViewById(R.id.bill_item_name);
        viewHolder.itemPrice = (TextView) result.findViewById(R.id.bill_item_cost);

        viewHolder.itemName.setText(dataModel.getName());
        viewHolder.itemPrice.setText(dataModel.getPrice());

        // Return the completed view to render on screen
        return result;
    }
}
