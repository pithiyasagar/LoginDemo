package expence.abc.com.logindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import expence.abc.com.logindemo.adapter.ItemRecyclerAdapter;
import expence.abc.com.logindemo.model.Item;

public class ItemListRecyclerActivity extends AppCompatActivity {

    //Initialize view
    private RecyclerView rclItemList;

    //Initialize Adapter
    private ItemRecyclerAdapter itemRecyclerAdapter;

    // Initializing list view with the custom adapter
    ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_recycler);

        itemRecyclerAdapter = new ItemRecyclerAdapter(itemList);
        rclItemList = (RecyclerView) findViewById(R.id.rcl_item_list);
        rclItemList.setLayoutManager(new LinearLayoutManager(this));
        rclItemList.setItemAnimator(new DefaultItemAnimator());
        rclItemList.setAdapter(itemRecyclerAdapter);

        // Populating list items
        for(int i=0; i<100; i++) {
            Item item = new Item();
            item.name = "Item " + i;
            itemList.add(item);
        }
    }
}
