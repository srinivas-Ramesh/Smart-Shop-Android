package i322373.com.smartshopping.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import i322373.com.smartshopping.adapter.BillAdapter;
import i322373.com.smartshopping.dataModel.ItemDataModel;
import i322373.com.smartshopping.R;

public class BillingActivity extends AppCompatActivity {

    ArrayList<ItemDataModel> billDataModel;
    TextView totalAmount;
    ListView billListView;

    private static BillAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        totalAmount = (TextView) findViewById(R.id.total_amount_value);
        billListView = (ListView) findViewById(R.id.billing_list_view);
        billDataModel = ItemsCartActivity.itemsDataModelList;
        billAdapter = new BillAdapter(billDataModel, getApplicationContext());

        billListView.setAdapter(billAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalAmount.setText(getTotalAmount(ItemsCartActivity.itemsDataModelList));
    }

    public String getTotalAmount(ArrayList<ItemDataModel> itemList) {
        int total = 0;
        for (ItemDataModel item : itemList) {
            total = total + Integer.parseInt(item.getPrice());
        }
        return String.valueOf(total);
    }

}
