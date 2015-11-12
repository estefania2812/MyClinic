package control;

import java.util.ArrayList;

import com.example.ourclinic.R;
import com.example.ourclinic.R.id;
import com.example.ourclinic.R.layout;
import com.example.ourclinic.R.menu;

import model.backend.BackendFactory;
import model.datasource.DatabaseList;
import entities.Illness;
import entities.Medicine;
import entities.Password;
import entities.Patient;
import entities.Visit;
import android.R.bool;
import android.support.v7.app.ActionBarActivity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IllnesseToAddListViewDemoFragment extends ListFragment {
    private ArrayList <Illness> iItems;  // ListView items list
    long numOfVis; // מספר ביקור שמופיע באותו חלון. אליו אנו רוצים להוסיף מחלות
    Context context=null;
    ProgressDialog progressDialog;

   
	//====================================================================================
    public IllnesseToAddListViewDemoFragment(ArrayList<Illness> ill, long numVis) {
        // initialize the items list and numOfVis
    	iItems=new ArrayList<Illness>();
        iItems= ill;
        numOfVis=numVis;
	}
	//====================================================================================
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this.getActivity();        
        // initialize and set the list adapter        
        setListAdapter(new IllnessesToAddListViewDemoAdapter(getActivity(), iItems, numOfVis));
    }
	//====================================================================================
    // numOfVis בעת לחיצה על מחלה מתוך הרשימה, מנסה להוסיפה לביקור מספר
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
      // retrieve theListView item
      final Illness item = iItems.get(position);   
      
      try {
			myAsyncTask t1 = new myAsyncTask(this.getActivity(), progressDialog, new myFunc() {						
			@Override
			public void run() throws Exception  
			{   
				BackendFactory.getInstance(context).addIllToVis(numOfVis, item.getIllNum());// מנסה להוסיף מחלה עבור הביקור
			}
		    }, new myFunc() {
		
			@Override
			public void run()   // אם התהליך התבצע בהצלחה, מראה הודעה מתאימה
			{	
			 	Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			}
		    });
			// הרצת התהליך
			t1.execute();
		} catch (Exception e) {
	 		  Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();// show the error
		}         
    }
}