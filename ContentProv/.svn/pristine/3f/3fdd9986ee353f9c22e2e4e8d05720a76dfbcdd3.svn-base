package ca.campbell.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
// this code test  / uses it's own content provider
// that is not the intention, content providers are meant to allow other
// apps to share their data
public class ContentProv extends Activity {
	public static final String TAG = "CheckCP";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_prov);
		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 
				//---add a book--
				ContentValues values = new ContentValues();
				values.put(BooksContentProvider.TITLE, ((EditText) findViewById(R.id.txtTitle)).getText().toString());
				values.put(BooksContentProvider.ISBN, ((EditText) findViewById(R.id.txtISBN)).getText().toString());
				Uri uri = getContentResolver().insert( BooksContentProvider.CONTENT_URI, values);

				Toast.makeText(getBaseContext(),uri.toString(), Toast.LENGTH_LONG).show();
			
				// original nesting the listener for retrieve in case list is empty
			
			} // btnAdd onClick()}
		});
		Button btnRetrieve = (Button) findViewById(R.id.btnRetrieve);
		btnRetrieve.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 
				//---retrieve the titles--
				Uri allTitles = Uri.parse( "content://ca.campbell.contentprovider.Books/books");
				// managedQuery depricated -> CursorLoader
				Cursor c = managedQuery(allTitles, null, null, null, null);
				if (c. moveToFirst()) { 
					do{ 
						// change this to put them into a listview 
						Log.v(TAG,  c.getString(c.getColumnIndex( BooksContentProvider._ID)) + ", " 
								+ c.getString(c.getColumnIndex( BooksContentProvider.TITLE)) + ", " 
								+ c.getString(c.getColumnIndex( BooksContentProvider.ISBN)));
					} while (c.moveToNext()); 
				} 
			} //btnRetrieve onClick()
		}); 
	} // onCreate()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_content_prov, menu);
		return true;
	}


} // contentProv class
