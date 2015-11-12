package model;

import control.myFunc;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * הרעיון של המחלקה היא לממש מחלקה של אסינכרוני טאסק בצורה נוחה לשימוש בכל מקום שנצטרך לייצר תהליך
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
 * @param activity מקבל את האקטיביטי ממנו בנינו את התהליך
 * @param progress מקבל את הפרוסס דיאלוג של האקטיביטי כדי שיוכל להפעיל ולכבות אותו
 * @param b פונקציה שהוא יבצע ברקע
 * @param p פונקציה שהוא יבצע בסיום
 * הפונקציות הם מימוש של המחלקה myFunc
 * וראה שם כיצד לממש את האינטרפייס הזה 
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
		back.run(); // מריץ את הפונקציה שקיבל לביצוע ברקע
	 } 
	 catch (Exception e) // אם התרחשה שגיאה
	 {
		 exceptionThrown = e;
		  
		 if (e.getMessage().contains("Read timed out")) // אם השגיאה היא שאין תקשורת לשרת
		 {
			 exceptionThrown = new Exception("No response from server. Try again.");
		 } 
		 else if (e.getMessage().contains("@@@")) // אם נזרקה שגיאה מהשרת שאנחנו זרקנו שם, אז עטפנו אותה בשטרודלים וסולמיות ולכן היא תכיל שטרודלים
		 {
			 String s = e.getMessage();
			 s = s.replaceAll("[^@]*@@@|###[^@]*", ""); // מוחק מהשגיאה את כל הזבל שמופיע לפני ואחרי השגיאה, ומשאיר רק את החלק עם התוכן החשוב מהשגיאה
			 exceptionThrown = new Exception(s); // זורק שוב את השגיאה מהמכשיר עצמו, ולכן היא לא תגיע עם עוד זבל
		 }
		 else // אם התרחשה שגיאה בלתי קונבנציונאלית, אז תגיד שיש בעיה
		 {
			 exceptionThrown = new Exception("Problem.");
		 }

	}
	 
	 return null;
 } 

 @Override 
 protected void onPreExecute() // לפני ביצוע התהליך 
 { 
	 try 
	 {
		 progressDialog = ProgressDialog.show(myAct, "Please wait", "...טוען נתונים", true);
	 } 
	 catch (Exception e) 
	 {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 } 

 @Override 
 protected void onPostExecute(Void result) // לאחר ביצוע התהליך 
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