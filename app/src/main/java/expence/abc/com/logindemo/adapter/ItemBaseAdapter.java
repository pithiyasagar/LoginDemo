package expence.abc.com.logindemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import expence.abc.com.logindemo.R;
import expence.abc.com.logindemo.model.Item;

public class ItemBaseAdapter extends BaseAdapter {

    private ArrayList<Item> itemArrayList;

    public ItemBaseAdapter(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    @Override
    public int getCount() {
        return itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txtItem.setText(itemArrayList.get(position).name);

        return view;
    }

    private class ViewHolder {
        TextView txtItem;

        public ViewHolder(View view) {
            txtItem = (TextView) view.findViewById(R.id.txt_item);
        }
    }
}
