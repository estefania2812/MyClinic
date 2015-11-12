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
import android.app.AlertDialog;
import android.app.ListFragment;
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

public class MedicineListViewDemoFragment extends ListFragment {
    private ArrayList <Medicine> mItems;        // ListView items list
    Context context=null;

 
    public MedicineListViewDemoFragment(ArrayList<Medicine> med) {
		// TODO Auto-generated constructor stub
    	mItems=new ArrayList<Medicine>();
        mItems= med;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      
        // initialize and set the list adapter
        setListAdapter(new MedicineListViewDemoAdapter(getActivity(), mItems));
    }
    

	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      
    }
 
  
    
}