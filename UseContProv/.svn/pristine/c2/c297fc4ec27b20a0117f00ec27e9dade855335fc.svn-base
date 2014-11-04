package ca.campbell.usecontprov;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UseCP extends Activity {
	public static final String TAG = "USECP";
	public static final String PROVIDER_NAME = "ca.campbell.contentprovider.Books";
	public static final String CONTENT_STRING = "content://"+ PROVIDER_NAME + "/books";
	public static final Uri CONTENT_URI = Uri.parse(CONTENT_STRING);
	public static final String _ID = "_id";
	public static final String TITLE = "title";
	public static final String ISBN = "isbn";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_use_cp);

		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 
				//---add a book--
				ContentValues values = new ContentValues();
				values.put(TITLE, ((EditText) findViewById(R.id.txtTitle)).getText().toString());
				values.put(ISBN, ((EditText) findViewById(R.id.txtISBN)).getText().toString());
				Uri uri = getContentResolver().insert( CONTENT_URI, values);

				Toast.makeText(getBaseContext(),uri.toString(), Toast.LENGTH_LONG).show();

				// original nesting the listener for retrieve in case list is empty

			} // btnAdd onClick()}
		});

		Button btnRetrieve = (Button) findViewById(R.id.btnRetrieve);
		btnRetrieve.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 
				//---retrieve the titles--
				Uri allTitles = Uri.parse(CONTENT_STRING);
				// managedQuery depricated -> CursorLoader
				Cursor c = managedQuery(allTitles, null, null, null, null);
				ListView lv = (ListView) findViewById(R.id.lv);
				// Now create a new list adapter bound to the cursor.
				// SimpleListAdapter is designed for binding to a Cursor.
				ListAdapter adapter = new SimpleCursorAdapter(UseCP.this, // Context.
						android.R.layout.two_line_list_item, // Specify the row template
						// to use (here, two columns bound to the  two retrieved cursor rows).
						c, // Pass in the cursor to bind to.
						// Array of cursor columns to bind to.
						new String[] { TITLE, ISBN },
						// Parallel array of which template objects to bind to those
						// columns.
						new int[] { android.R.id.text1, android.R.id.text2 }, 0);

				// Bind to our new adapter.
				lv.setAdapter(adapter);
			} // onclick()
		}); //btnRetrieve onClick()
	} // onCreate()

} // UseCP  class

