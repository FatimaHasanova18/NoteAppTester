package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityNotePageBinding

class NotePage : AppCompatActivity() {
    private lateinit var binding: ActivityNotePageBinding
    private lateinit var myDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.customToolbar)

        // Intent məlumatlarını düzgün alın
        val info = intent.getStringExtra("info")
        val id = intent.getStringExtra("id")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.img_5)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.arrow.setOnClickListener {
            val intent = Intent(this@NotePage, MainPage::class.java)
            startActivity(intent)
        }

        if (info == "new") {
            binding.headText.setText("")
            binding.Text.setText("")
        } else {
            loadNoteFromDatabase(id)
        }
    }

    private fun loadNoteFromDatabase(noteId: String?) {
        try {
            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)
            val cursor = myDatabase.rawQuery("SELECT * FROM notes WHERE id = ?", arrayOf(noteId))
            val titleIndex = cursor.getColumnIndex("title")
            val detailsIndex = cursor.getColumnIndex("details")
            if (cursor.moveToFirst()) {
                binding.headText.setText(cursor.getString(titleIndex))
                binding.Text.setText(cursor.getString(detailsIndex))
            }
            cursor.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun insertDatabase() {
        try {
            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes (id INTEGER PRIMARY KEY, title VARCHAR, details VARCHAR)")
            val sqlQuery = "INSERT INTO notes (title, details) VALUES (?, ?)"
            val statement = myDatabase.compileStatement(sqlQuery)
            statement.bindString(1, binding.headText.text.toString())
            statement.bindString(2, binding.Text.text.toString())
            statement.execute()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
