package expence.abc.com.logindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import expence.abc.com.logindemo.adapter.ItemBaseAdapter;
import expence.abc.com.logindemo.model.Item;

public class ItemListBaseActivity extends AppCompatActivity {

    //Initialize view
    private ListView lstMain;

    //Initialize Adapter
    private ItemBaseAdapter itemBaseAdapter;

    // Initializing list view with the custom adapter
    ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_base);

        itemBaseAdapter = new ItemBaseAdapter(itemList);
        lstMain = (ListView) findViewById(R.id.lst_main);
        lstMain.setAdapter(itemBaseAdapter);

        // Populating list items
        for(int i=0; i<100; i++) {
            Item item = new Item();
            item.name = "Item " + i;
            itemList.add(item);
        }
    }
}
