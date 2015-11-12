package model;

import control.myFunc;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * ������ �� ������ ��� ���� ����� �� ��������� ���� ����� ���� ������ ��� ���� ������ ����� �����
 * 
 * @author arale & ariel
 *
 */
public class myAsyncTask extends AsyncTask<Void, Void, Void>  
{

private ProgressDialog progressDialog;
private Activity myAct;
private Exception exceptionThrown;
private myFunc back, post;

/**
 * 
 * @param activity ���� �� ��������� ���� ����� �� ������
 * @param progress ���� �� ������ ������ �� ��������� ��� ����� ������ ������ ����
 * @param b ������� ���� ���� ����
 * @param p ������� ���� ���� �����
 * ��������� �� ����� �� ������ myFunc
 * ���� �� ���� ���� �� ���������� ��� 
 */
 public myAsyncTask(Activity activity, ProgressDialog progress, myFunc b, myFunc p) 
 { 
	 super();
	 myAct = activity;
	 back = b;
	 post = p;
	 progressDialog = progress;
 } 
  
 @Override 
 protected Void doInBackground(Void... params) 
 {
	 try 
	 {
		back.run(); // ���� �� �������� ����� ������ ����
	 } 
	 catch (Exception e) // �� ������ �����
	 {
		 exceptionThrown = e;
		  
		 if (e.getMessage().contains("Read timed out")) // �� ������ ��� ���� ������ ����
		 {
			 exceptionThrown = new Exception("No response from server. Try again.");
		 } 
		 else if (e.getMessage().contains("@@@")) // �� ����� ����� ����� ������ ����� ��, �� ����� ���� ��������� �������� ���� ��� ���� ��������
		 {
			 String s = e.getMessage();
			 s = s.replaceAll("[^@]*@@@|###[^@]*", ""); // ���� ������� �� �� ���� ������ ���� ����� ������, ������ �� �� ���� �� ����� ����� �������
			 exceptionThrown = new Exception(s); // ���� ��� �� ������ ������� ����, ���� ��� �� ���� �� ��� ���
		 }
		 else // �� ������ ����� ���� �������������, �� ���� ��� ����
		 {
			 exceptionThrown = new Exception("Problem.");
		 }

	}
	 
	 return null;
 } 

 @Override 
 protected void onPreExecute() // ���� ����� ������ 
 { 
	 try 
	 {
		 progressDialog = ProgressDialog.show(myAct, "Please wait", "...���� ������", true);
	 } 
	 catch (Exception e) 
	 {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 } 

 @Override 
 protected void onPostExecute(Void result) // ���� ����� ������ 
 { 
	 try 
	 {
		if (progressDialog.isShowing()) 
		{ 
			progressDialog.dismiss(); 
		}
		
		if (exceptionThrown != null) 
		{ 
			Toast.makeText(myAct, exceptionThrown.getMessage(), Toast.LENGTH_SHORT).show();
		}
		else
		{
			post.run();	
		}
		
	 } 
	 catch (Exception e) 
	 {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 } 
}