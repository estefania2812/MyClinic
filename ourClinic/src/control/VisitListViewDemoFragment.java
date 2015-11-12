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

public class VisitListViewDemoFragment extends ListFragment {
    private ArrayList <Visit> vItems;        
    Context context=null;
	ProgressDialog progressDialog;
	ArrayList<Medicine>meds = new ArrayList<Medicine>();
	ArrayList<Illness>ills = new ArrayList<Illness>();
	AlertDialog alertDialog ;   
	//====================================================================================
    public VisitListViewDemoFragment(ArrayList<Visit> vis) {
		// TODO Auto-generated constructor stub
    	vItems=new ArrayList<Visit>();
        vItems= vis;
	}
	//====================================================================================
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the items list  
        context=this.getActivity();
        // initialize and set the list adapter
        setListAdapter(new VisitListViewDemoAdapter(getActivity(), vItems));
    }  
	//====================================================================================
    @Override
    // בעת לחיצה על כפתור מרשימת הביקורים, יציג בפרוטרוט את פרטי הביקור
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
      final Visit item = vItems.get(position);       
   // show visit details
	final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
   	// set title
   				alertDialogBuilder.setTitle("Visit details");
   				final String kav = "\n-------------------------------------\n";
   				//look for all medicine and illnesses given in this visit-----------------------   				
   			 try {
			        myAsyncTask t1 = new myAsyncTask(this.getActivity(), progressDialog, new myFunc() {						
					@Override
					public void run() throws Exception // try to get the illnesses & the medicines for this visit
					{    
						meds= BackendFactory.getInstance(context).getMedicinesForVisit(item.getVisNum());
						ills= BackendFactory.getInstance(context).getIllnessesForVisit(item.getVisNum());					
						}
				    }, new myFunc() {
					
					@Override
					public void run() // רץ בסיום התהליך
					{	
						String allMeds="";
		   				for (Medicine med : meds)
		   					allMeds = allMeds +"*"+ med.getMedName()+" ";		   			
		   				String allIlls="";
		   				for (Illness ill : ills)
		   					allIlls = allIlls +"*"+ ill.getIllName()+" ";
		   	   			//------------------------------------------------------------------------------
		   				String str="Visit number: "+ item.getVisNum()+"\n"+"Date: "+item.getDateAsString()+"\nDoctor: "+item.getDocLicence()+"\nPatient: "
		   						+item.getPatID()+"\nDescription: "+item.getDocOpinions()+kav+"Medicines given: \n"+allMeds+kav+"Illnesses: \n"+ allIlls;   	 
		   				// set dialog message
		   				alertDialogBuilder
		   					.setMessage(str)
		   					.setCancelable(false)
		   					.setPositiveButton("OK",new DialogInterface.OnClickListener() {
		   						public void onClick(DialogInterface dialog,int id) { 							
		   							dialog.cancel();  							
		   						}
		   					  });   	 
		   					// create alert dialog
		   					alertDialog = alertDialogBuilder.create();   	 
		   					// show it
		   					alertDialog.show(); 
																
					}
				    });
					// הרצת התהליך
					t1.execute();
				} catch (Exception e) {
			 		  Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();// show the error
				}
			}		  
    }
	//====================================================================================

