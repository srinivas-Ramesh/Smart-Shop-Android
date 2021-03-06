package i322373.com.smartshopping.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import i322373.com.smartshopping.R;
import i322373.com.smartshopping.adapter.ItemsAdapter;
import i322373.com.smartshopping.dataModel.ItemDataModel;
import i322373.com.smartshopping.service.ItemIntentService;
import i322373.com.smartshopping.service.RetrieveItems;

public class ItemsCartActivity extends AppCompatActivity {

    private static boolean backgroundService_flag = false;
    public static ArrayList<ItemDataModel> itemsDataModelList;
    private ListView ItemListView;
    public static ItemsAdapter adapter;
    private RetrieveItems updateItems;
    private ImageView updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        updateButton = (ImageView) findViewById(R.id.update_button);
        ItemListView = (ListView) findViewById(R.id.items_list);
        itemsDataModelList = new ArrayList<>();
        adapter = new ItemsAdapter(itemsDataModelList, getApplicationContext());
        ItemListView.setAdapter(adapter);

        if(isNetworkAvailable()) {
            startService(new Intent(this, ItemIntentService.class));
            backgroundService_flag = true;
        }
        else{
            Toast.makeText(this,"Please enable Internet connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isNetworkAvailable()){
            if(!backgroundService_flag){
                startService(new Intent(this,ItemIntentService.class));
                backgroundService_flag = true;
            }
        }
    }

    /**
     * Method to update the list of items in the ListView by comparing it with the updated list
     * present in RetrieveItems class
     *
     * @param itemList list of items
     */
    public static void updateItemsDataModelList(ArrayList<ItemDataModel> itemList) {
        if (itemList.size() != itemsDataModelList.size()) {
            itemsDataModelList.removeAll(itemsDataModelList);
            itemsDataModelList.addAll(itemList);
            adapter.notifyDataSetChanged();
        }
    }


    /**
     * On click Listener of Bill Button
     *
     * @param v View
     */
    public void onClickOfBill(View v) {
        Intent billIntent = new Intent(this, BillingActivity.class);
        startActivity(billIntent);
    }

    /**
     * On clicl listener of Offer Button
     *
     * @param v View
     */
    public void onClickOfOffers(View v) {
        Intent offerIntent = new Intent(this, OfferActivity.class);
        startActivity(offerIntent);
    }


    /**
     * on click listener of update button
     *
     * @param v View
     */
    public void onClickOfUpdate(View v) {
        updateItems = new RetrieveItems();
        updateItemsDataModelList(RetrieveItems.retrievedItems);
        updateItems.execute();
    }

    /**
     * check if internet connectivity is present
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
