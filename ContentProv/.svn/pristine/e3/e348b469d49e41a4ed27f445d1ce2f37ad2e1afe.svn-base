package ca.campbell.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class BooksContentProvider extends ContentProvider {

	public static final String PROVIDER_NAME = "ca.campbell.contentprovider.Books";
	public static final Uri CONTENT_URI = Uri.parse("content://"+ PROVIDER_NAME + "/books");
	public static final String _ID = "_id";
	public static final String TITLE = "title";
	public static final String ISBN = "isbn";
	private static final int BOOKS = 1;
	private static final int BOOK_ID = 2;
	private static final UriMatcher uriMatcher;
	static{ uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	uriMatcher.addURI(PROVIDER_NAME, "books", BOOKS);
	uriMatcher.addURI(PROVIDER_NAME, "books/#", BOOK_ID);
	} //---for database use--
	private SQLiteDatabase booksDB;


	private static final String DATABASE_NAME = "Books";
	private static final String DATABASE_TABLE = "titles";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (_id integer primary key autoincrement, " + "title text not null, isbn text not null);" ;

	private static class DatabaseHelper extends SQLiteOpenHelper { 
		DatabaseHelper(Context context) { 
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		} 
		@Override  
		public void onCreate(SQLiteDatabase db) { 
			db.execSQL(DATABASE_CREATE); 
		}
		@Override  
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { Log.w("Content provider database", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS titles");
		onCreate(db);
		} 
	} 
	@Override  
	public int delete(Uri uri, String sel, String[] selArgs) { 
		String argList;
		int count;
		switch (uriMatcher.match(uri)){
		case BOOKS: count = booksDB.delete( DATABASE_TABLE, sel, selArgs);
		break;
		case BOOK_ID: String id = uri.getPathSegments().get(1);
		if (TextUtils.isEmpty(sel))
			argList = "";
		else 
			argList = " AND (" + sel + ")";
		count = booksDB.delete( DATABASE_TABLE, _ID + " = " + id + argList, selArgs);
		break;
		default: throw new IllegalArgumentException("Unknown URI " + uri);

		} getContext().getContentResolver().notifyChange(uri, null);
		return count;
	} 
	@Override public String getType(Uri uri) { 
		switch (uriMatcher.match(uri)){ 
		//---get all books--
		case BOOKS: 
			return "vnd.android.cursor.dir/vnd.campbell.books ";
		//---get a particular book--
		case BOOK_ID: 
			return "vnd.android.cursor.item/vnd.campbell.books ";
		default: 
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		} 
	} 
	@Override  
	public Uri insert(Uri uri, ContentValues values) { 
		//---add a new book--
		long rowID = booksDB.insert( DATABASE_TABLE, "", values);
		//---if added successfully--
		if (rowID>0) {  
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		
		throw new SQLException("Failed to insert row into " + uri);
	} // insert()
	@Override  
	public boolean onCreate() { 
		Context context = getContext();
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		booksDB = dbHelper.getWritableDatabase();
		return (booksDB == null)? false:true;
	} 
	@Override  
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) { SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
	sqlBuilder.setTables(DATABASE_TABLE);
	if (uriMatcher.match(uri) == BOOK_ID) 
		//---if getting a particular book--

		sqlBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));
	if (sortOrder==null || sortOrder=="") sortOrder = TITLE;
	Cursor c = sqlBuilder.query( booksDB, projection, selection, selectionArgs, null, null, sortOrder);

	//---register to watch a content URI for changes--
	c.setNotificationUri(getContext().getContentResolver(), uri);
	return c;
	}
	@Override  
	public int update(Uri uri, ContentValues values, String sel, String[] selArgs) { 
		int count = 0;
		String argList;

		switch (uriMatcher.match(uri)){ 
		case BOOKS: 
			count = booksDB.update( DATABASE_TABLE, values, sel, selArgs);
			break;
		case BOOK_ID: 
			if (TextUtils.isEmpty(sel))
				argList = "";
			else 
				argList = " AND (" + sel + ")";
			count = booksDB.update( DATABASE_TABLE, values, _ID + " = " + uri.getPathSegments().get(1) + argList, selArgs);
			break;

		default: throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;

	} // update()
}  // class 
