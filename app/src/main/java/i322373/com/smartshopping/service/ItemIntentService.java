package i322373.com.smartshopping.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;

import i322373.com.smartshopping.activity.ItemsCartActivity;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ItemIntentService extends IntentService {

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
      /* do what you need to do */
            updateItems();
      /* and here comes the "trick" */
            handler.postDelayed(this, 500);
        }
    };

    public ItemIntentService() {
        super("ItemIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        handler.postDelayed(runnable, 500);
    }

    public void updateItems(){
        RetrieveItems updateItems = new RetrieveItems();
        updateItems.execute();
        ItemsCartActivity.updateItemsDataModelList(RetrieveItems.retrievedItems);
    }
}
