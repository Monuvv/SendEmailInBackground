package com.kpbird.sendemail;

import java.nio.channels.SelectableChannel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SendEmail extends Activity {
	 TextView messageText;
	EditText et_Send_to;
	Button send,select;
	Button attach;
	String imagepath;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et_Send_to=(EditText)findViewById(R.id.et_sendto);
        messageText=(TextView)findViewById(R.id.text);
        send=(Button)findViewById(R.id.send_email);
        select=(Button)findViewById(R.id.select);
       // attach=(Button)findViewById(R.id.button1);
        select.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
			     intent.setType("*/*");
	            intent.setAction(Intent.ACTION_GET_CONTENT);
	            startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
				
			}
		});
        
        send.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String s_send_to=et_Send_to.getText().toString();
		    	Mail m = new Mail("monikav306@gmail.com", "Monika_verma_00"); 
		        String[] toArr = {s_send_to, "monikav306@gmail.com"}; 
		        m.setTo(toArr); 
		        m.setFrom("monikav306@gmail.com"); 
		        m.setSubject("Its only for uuuuuuu."); 
		        m.setBody("I LOVE U SO SO MUCHHHHHH"); 
		        try { 
		        	 m.addAttachment(imagepath);
		         // m.addAttachment("/sdcard/bday.jpg"); 
		          if(m.send()) { 
		            Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
		          } else { 
		            Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show(); 
		          } 
		        } catch(Exception e) { 
		          Log.e("MailApp", "Could not send email", e); 
		        } 
				
			}
		});
        
        
    }
    @Override
   	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   	    
       	if (requestCode == 1 && resultCode == RESULT_OK) {
               //Bitmap photo = (Bitmap) data.getData().getPath(); 
             
               Uri selectedImageUri = data.getData();
               imagepath =getRealPathFromURI(selectedImageUri);
               //bitmap=BitmapFactory.decodeFile(imagepath);
               //imageview.setImageBitmap(bitmap);
               messageText.setText("Uploading file path:" +imagepath);
   	    	
   	    }
       }
//    @Override
//    protected void onResume() {
//    	super.onResume();
	private String getRealPathFromURI(Uri contentUri)
	{
		 String path = null;
	        String[] proj = { MediaStore.MediaColumns.DATA };

	           if("content".equalsIgnoreCase(contentUri.getScheme ()))
	               {
	                   Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
	                   if (cursor.moveToFirst()) {
	                       int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
	                       path = cursor.getString(column_index);
	                   }
	                   cursor.close();
	                   return path;
	               }
	               else if("file".equalsIgnoreCase(contentUri.getScheme()))
	               {
	                   return contentUri.getPath();
	               }
		return null;
	}
    	
    	
//    }
}