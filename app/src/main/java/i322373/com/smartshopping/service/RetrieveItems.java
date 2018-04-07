package i322373.com.smartshopping.service;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import i322373.com.smartshopping.R;
import i322373.com.smartshopping.dataModel.ItemDataModel;

/**
 * Created by I322373 on 5/1/2017.
 */

public class RetrieveItems extends AsyncTask {

    public volatile static ArrayList<ItemDataModel> retrievedItems = new ArrayList<>();
    public volatile static ArrayList<ItemDataModel> retrievedOffersItems = new ArrayList<>();

    JSONArray JsonResponseArray;
    private String urlAsString = "https://smartshopp1941546332trial.hanatrial.ondemand.com/smartshop/app/items/all";
    //https://smartshopi322373trial.hanatrial.ondemand.com


    @Override
    protected Object doInBackground(Object[] params) {
        updateRetrievedItems(urlAsString);
        return null;
    }


    /**
     * This method makes a get call to the URL specified and obtains a JSON response. This response is updated and prepareItemList method is called
     * to get the list of ItemDataModel objects.This list is updated to the retrievedItems list.
     *
     * @param URLAsString URL of the call to be made
     */
    public void updateRetrievedItems(String URLAsString) {


        try {
            URL url = new URL(URLAsString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "text/plain");
            connection.setRequestProperty("Cache-Control", "no-cache");

            connection.connect();

            if (connection != null) {
                try {
                    String response = "";
                    int httpResult = connection.getResponseCode();
                    if (httpResult == HttpURLConnection.HTTP_OK || httpResult == HttpURLConnection.HTTP_CREATED || httpResult == HttpURLConnection.HTTP_ACCEPTED) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(connection.getInputStream(), "utf-8"));
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            response = response.concat(line + "\n");
                        }
                        br.close();
                        JsonResponseArray = new JSONArray(response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        /*
        sets the List present in the Items cart activity with the updated List formed by the response
        got by HTTP Get Call
         */
        retrievedItems = prepareItemsList(JsonResponseArray);
        retrievedOffersItems = prepareOffersList(JsonResponseArray);
    }


    /**
     * This method prepares the ArrayList of items containing ItemDataModel Objects from the
     * List of Ids got from the JsonResponse. According to the Id an ItemDataModel object is created
     * and is populated with relevant data.Finally it is added to the arrayList of ItemDataModel which
     * will be returned back
     *
     * @param itemsArray JSON array consisting of an array of item id
     * @return Array List of ItemDataModel objects prepared from the Json response obtained
     */

    public ArrayList<ItemDataModel> prepareItemsList(JSONArray itemsArray) {

        ArrayList<ItemDataModel> itemsList = new ArrayList<>();

        for (int i = 0; i < itemsArray.length(); i++) {

            ItemDataModel item = new ItemDataModel();

            try {
                int id = itemsArray.getJSONObject(i).getInt("id");

                switch (id) {

                    case 4194131:
                        item.setId("4194131");
                        item.setName("Dove");
                        item.setImageResource(R.drawable.dove);
                        item.setPrice("53");
                        item.setLocation("F1.S2.R3");
                        itemsList.add(item);
                        break;
                    case 4199131:
                        item.setId("4199131");
                        item.setName("Pepsi");
                        item.setImageResource(R.drawable.pepsi);
                        item.setPrice("20");
                        item.setLocation("F1.S4.R1");
                        itemsList.add(item);
                        break;
                    case 4204131:
                        item.setId("4204131");
                        item.setName("Duracell");
                        item.setImageResource(R.drawable.duracell_logo);
                        item.setPrice("10");
                        item.setLocation("F2.S5.R1");
                        itemsList.add(item);
                        break;
                    case 4209131:
                        item.setId("4209131");
                        item.setName("DairyMilk Silk");
                        item.setImageResource(R.drawable.silk);
                        item.setPrice("120");
                        item.setLocation("F1.S7.R2");
                        itemsList.add(item);
                        break;
                    case 4214131:
                        item.setId("4214131");
                        item.setName("Oceans");
                        item.setImageResource(R.drawable.oceans_juice);
                        item.setPrice("35");
                        item.setLocation("F1.S7.R1");
                        itemsList.add(item);
                        break;
                    case 4219131:
                        item.setId("4219131");
                        item.setName("Wilson Ball");
                        item.setImageResource(R.drawable.wilson);
                        item.setPrice("100");
                        item.setLocation("F2.S3.R3");
                        itemsList.add(item);
                        break;
                    default:
                        item.setId(String.valueOf(id));
                        item.setName("Unknown Item");
                        item.setImageResource(R.drawable.main_screen_background);
                        item.setPrice("0");
                        itemsList.add(item);
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return itemsList;
    }


    /**
     * This method prepares the ArrayList of items containing ItemDataModel Objects from the
     * List of Ids got from the JsonResponse. According to the Id an ItemDataModel object is created
     * and is populated with relevant data.Finally it is added to the arrayList of ItemDataModel which
     * will be returned back
     *
     * @param itemsArray JSON array consisting of an array of item id
     * @return Array List of ItemDataModel objects prepared from the Json response obtained
     */
    public static ArrayList<ItemDataModel> prepareOffersList(JSONArray itemsArray) {

        ArrayList<ItemDataModel> itemsList = new ArrayList<>();

        for (int i = 0; i < itemsArray.length(); i++) {

            ItemDataModel item = new ItemDataModel();

            try {
                int id = itemsArray.getJSONObject(i).getInt("id");

                switch (id) {

                    case 4194131:
                        item.setId("4194131");
                        item.setName("Pears");
                        item.setImageResource(R.drawable.pears);
                        item.setPrice("30");
                        item.setLocation("F1.S2.R3");
                        itemsList.add(item);
                        break;
                    case 4199131:
                        item.setId("4199131");
                        item.setName("CocaCola");
                        item.setImageResource(R.drawable.coca_cola);
                        item.setPrice("15");
                        item.setLocation("F1.S4.R1");
                        itemsList.add(item);
                        break;
                    case 4204131:
                        item.setId("4204131");
                        item.setName("Everyday");
                        item.setImageResource(R.drawable.eveready_battery);
                        item.setPrice("8");
                        item.setLocation("F2.S5.R1");
                        itemsList.add(item);
                        break;
                    case 4209131:
                        item.setId("4209131");
                        item.setName("Galaxy");
                        item.setImageResource(R.drawable.galaxy);
                        item.setPrice("40");
                        item.setLocation("F1.S7.R2");
                        itemsList.add(item);
                        break;
                    case 4214131:
                        item.setId("4214131");
                        item.setName("Tropicana");
                        item.setImageResource(R.drawable.tropicana);
                        item.setPrice("25");
                        item.setLocation("F1.S7.R1");
                        itemsList.add(item);
                        break;
                    case 4219131:
                        item.setId("4219131");
                        item.setName("Vicky Ball");
                        item.setImageResource(R.drawable.vicky);
                        item.setPrice("30");
                        item.setLocation("F2.S3.R3");
                        itemsList.add(item);
                        break;
                    default:
                        item.setId(String.valueOf(id));
                        item.setName("Unknown Item");
                        item.setImageResource(R.mipmap.ic_launcher);
                        item.setPrice("0");
                        itemsList.add(item);
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return itemsList;

    }
}
