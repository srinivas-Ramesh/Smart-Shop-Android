package i322373.com.smartshopping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import i322373.com.smartshopping.dataModel.ItemDataModel;
import i322373.com.smartshopping.adapter.ItemsAdapter;
import i322373.com.smartshopping.R;
import i322373.com.smartshopping.service.RetrieveItems;

public class OfferActivity extends AppCompatActivity {

    ListView offersList;
    public static ItemsAdapter offerAdapter;
    public static ArrayList<ItemDataModel> recommendedItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        offersList = (ListView) findViewById(R.id.recommendation_list_view);
        offerAdapter = new ItemsAdapter(recommendedItemsList,getApplicationContext());
        offersList.setAdapter(offerAdapter);

        updateItemsDataModelList(RetrieveItems.retrievedOffersItems);
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateItemsDataModelList(RetrieveItems.retrievedItems);
    }


    /**
     * Method to update the list of items in the ListView by comparing it with the updated list
     * present in RetrieveItems class
     *
     * @param itemList list of items
     */
    public static void updateItemsDataModelList(ArrayList<ItemDataModel> itemList) {
        if (itemList.size() != recommendedItemsList.size()) {
            recommendedItemsList.removeAll(recommendedItemsList);
            recommendedItemsList.addAll(itemList);
            offerAdapter.notifyDataSetChanged();
        }
    }
}
