package adam.illhaveacompany.personalsqlitedemo2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        //changed when I want to add a column
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "LibraryDatabase"

        //we're only using one table - contacts
        private const val TABLE_LIBRARY = "LibraryTable"
        //*changed from TABLE_CONTACTS

        //these are the keys of the contacts table
        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "title"
        //*I'd exclude this and change KEY_NAME to KEY_PICTURE -- BLOB is a blob of data - might be my picture
        private const val KEY_AUTHOR = "author"
        private const val KEY_YEAR_PUBLISHED = "yearPublished"
        private const val KEY_PUBLISHER = "publisher"
    }//4

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_LIBRARY_TABLE = ("CREATE TABLE" + TABLE_LIBRARY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_AUTHOR + " TEXT," + KEY_YEAR_PUBLISHED + " TEXT," +
                KEY_PUBLISHER + " TEXT" + ")")
        db?.execSQL(CREATE_LIBRARY_TABLE)
    }//5

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRARY)
        onCreate(db)
    }//6

    fun addBook(book: Book) : Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(KEY_TITLE, book.title)
        contentValues.put(KEY_AUTHOR, book.author)
        contentValues.put(KEY_YEAR_PUBLISHED, book.year)
        contentValues.put(KEY_PUBLISHER, book.publisher)

        val success = db.insert(TABLE_LIBRARY, null, contentValues)

        db.close()
        return success
    }//7
}