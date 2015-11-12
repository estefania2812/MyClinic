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
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MedicinesToAddListViewDemoFragment extends ListFragment {
    private ArrayList <Medicine> mItems;        // ListView items list
    long numOfVis; // עבור ביקור זה ננסה להוסיף תרופות מתוך הרשימה
    Context context=null;
	ProgressDialog progressDialog;
	View view ;	

	//====================================================================================
    public MedicinesToAddListViewDemoFragment(ArrayList<Medicine> med, long numVis) {
		// TODO Auto-generated constructor stub
    	mItems=new ArrayList<Medicine>();
        mItems= med;
        numOfVis=numVis;
        

	}
	//====================================================================================
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the items list
        context=this.getActivity();	
        
     //  view = inflator.inflate(R.layout.activity_patient_details, container, false);	
        // initialize and set the list adapter
        setListAdapter(new MedicinesToAddListViewDemoAdapter(getActivity(), mItems, numOfVis));
    }
    	//====================================================================================
        // numOfVis בעת לחיצה על תרופה מתוך הרשימה, מנסה להוסיפה לביקור מספר
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
          // retrieve theListView item
          final Medicine item = mItems.get(position);  
        
          try {
				myAsyncTask t1 = new myAsyncTask(this.getActivity(), progressDialog, new myFunc() {						
    			@Override
    			public void run() throws Exception  
    			{   
    				
    				BackendFactory.getInstance(context).addMedToVis(numOfVis, item.getMedNum());// מנסה להוסיף תרופה עבור הביקור
    			  
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
    	//====================================================================================

}