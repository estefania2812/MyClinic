package control;

import java.util.List;

import com.example.ourclinic.R;

import entities.Medicine;
import entities.Visit;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VisitListViewDemoAdapter extends ArrayAdapter<Visit> {
	 
    public VisitListViewDemoAdapter(Context context, List<Visit> items) {
        super(context, R.layout.list_item, items);
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.itemIcon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.itemDetails_textview);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view 
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        // update the item view
       Visit item = getItem(position);
        viewHolder.tvTitle.setText(item.toString()); 
        viewHolder.ivIcon.setBackgroundResource(R.drawable.visit);       

        return convertView;
    }
    
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     * 
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    //    TextView tvDescription;
    }
}
