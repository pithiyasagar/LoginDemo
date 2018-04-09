package expence.abc.com.logindemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import expence.abc.com.logindemo.R;
import expence.abc.com.logindemo.model.Item;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {
 
    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private ArrayList<Item> itemList;

    // Constructor of the class
    public ItemRecyclerAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
 
    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    // specify the row layout file and click for each row
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }
 
    // load data in each row element
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {
        TextView item = holder.txtItem;
        item.setText(itemList.get(listPosition).name);
    }
 
    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtItem;

        ViewHolder(View itemView) {
            super(itemView);

            txtItem = (TextView) itemView.findViewById(R.id.txt_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + txtItem.getText());
        }
    }
}