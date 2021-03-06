package control;

import java.util.List;

import com.example.ourclinic.R;

import entities.Illness;
import entities.Medicine;
import entities.Visit;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IllnessesToAddListViewDemoAdapter extends ArrayAdapter<Illness> {
	long numVis;
	 
    public IllnessesToAddListViewDemoAdapter(Context context, List<Illness> items, long numOfVis) {
        super(context, R.layout.list_item, items);
        numVis = numOfVis;
    }
	//====================================================================================

	// return the view of the illnesses list that we received. 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.itemDetails_textview);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.itemIcon);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view 
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        // update the item view
       Illness item = getItem(position);
       viewHolder.ivIcon.setBackgroundResource(R.drawable.add);       
        viewHolder.tvTitle.setText(item.toString());        
        return convertView;
    }
	//====================================================================================

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     * 
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    }
}
