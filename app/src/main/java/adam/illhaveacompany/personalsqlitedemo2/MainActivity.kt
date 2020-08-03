package adam.illhaveacompany.personalsqlitedemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_enter.setOnClickListener {
            Toast.makeText(this,"Record Added", Toast.LENGTH_LONG).show()
        }
    }

    fun addRecord() {
        val title = et_title.text.toString()
        val author = et_author.text.toString()
        val yearPublished = et_year.text.toString()
        val publisher = et_publisher.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        if(title.isNotEmpty() && author.isNotEmpty() && yearPublished.isNotEmpty() && publisher.isNotEmpty()) {
            val status = databaseHandler.addBook(Book(0, title, author, yearPublished ,publisher))

            if(status > -1) {
               Toast.makeText(applicationContext, "Record Saved", Toast.LENGTH_LONG).show()
                et_author.text?.clear()
                et_publisher.text?.clear()
                et_title.text?.clear()
                et_year.text?.clear()
            }else{
                Toast.makeText(applicationContext,"Record save failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}