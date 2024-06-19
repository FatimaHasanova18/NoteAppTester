package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityMainPageBinding

class MainPage : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding
    private lateinit var novList: ArrayList<Nov>
    private lateinit var noteList: ArrayList<Note>
    private lateinit var dayList: ArrayList<day>
    private lateinit var myDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        binding.fab.setOnClickListener {
//            val intent = Intent(this, NotePage::class.java)
//            intent.putExtra("info", "new")
//            startActivity(intent)
           // Toast.makeText(this@MainPage,"deneme",Toast.LENGTH_LONG).show()
           // Toast.makeText(this, "Bu bir Toast mesajıdır", Toast.LENGTH_SHORT).show()
           // Toast.makeText(applicationContext, "Bu bir Toast mesajıdır", Toast.LENGTH_SHORT).show()
            Toast.makeText(baseContext, "Bu bir Toast mesajıdır", Toast.LENGTH_SHORT).show()
        }

        // Günlərin siyahısı
        dayList = arrayListOf(
            day("Tue", "23", "Apr"),
            day("Wed", "24", "Apr"),
            day("Fri", "25", "Apr"),
            day("Sat", "26", "Apr"),
            day("Sun", "27", "Apr")
        )
        binding.recyclerDay.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dayAdapter = DayAdapter(dayList)
        binding.recyclerDay.adapter = dayAdapter

        // Novlərin siyahısı
        novList = arrayListOf(
            Nov("All"),
            Nov("Important"),
            Nov("Lecture Notes"),
            Nov("To-do list"),
            Nov("Shopping")
        )
        binding.recyclerTime.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val novAdapter = TypeAdapter(novList)
        binding.recyclerTime.adapter = novAdapter

        // Notların siyahısı
        binding.recyclerNote.layoutManager = GridLayoutManager(this, 2)
        noteList = ArrayList()
        val noteAdapter = NoteAdapter(noteList)
        binding.recyclerNote.adapter = noteAdapter

        loadNotesFromDatabase()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadNotesFromDatabase() {
        try {
            myDatabase = this.openOrCreateDatabase("Notes", Context.MODE_PRIVATE, null)
            val cursor = myDatabase.rawQuery("SELECT * FROM notes", null)
            val idIx = cursor.getColumnIndex("id")
            val titleIx = cursor.getColumnIndex("title")

            while (cursor.moveToNext()) {
                val idInt = cursor.getInt(idIx)
                val titleStr = cursor.getString(titleIx)
                val note = Note(idInt.toString(), titleStr)
                noteList.add(note)
            }
            cursor.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
