package com.example.notesapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapters.RicpeeAdapter
import com.example.notesapp.data.Ricpee

class MainActivity : AppCompatActivity() {
    private lateinit var rvRicpee: RecyclerView
    private lateinit var rvAdapter: RicpeeAdapter
    private lateinit var edName: EditText
    private lateinit var edIns: EditText
    private lateinit var edAuth: EditText


    private lateinit var submitBtn: Button

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getRicpees().observe(this, {
            ricpees -> rvAdapter.update(ricpees)
        })
        edName = findViewById(R.id.etName)
        edIns = findViewById(R.id.etIns)
        edAuth = findViewById(R.id.etAuth)


        submitBtn = findViewById(R.id.btSave)
        submitBtn.setOnClickListener {
            mainViewModel.addRicpee(edName.text.toString(),edIns.text.toString(),edAuth.text.toString())
            edName.text.clear()
            edName.clearFocus()
            edIns.text.clear()
            edIns.clearFocus()
            edAuth.text.clear()
            edAuth.clearFocus()
        }

        rvRicpee = findViewById(R.id.rvNotes) //RV
        rvAdapter = RicpeeAdapter(this)
        rvRicpee.adapter = rvAdapter
        rvRicpee.layoutManager = LinearLayoutManager(this)
    }

    fun raiseDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this) //Dialog
        val updatedTitle = EditText(this)
        val updatedIns = EditText(this)
        val updatedAuth = EditText(this)
        updatedIns.hint = "Enter new Instructions"

        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {

                    _, _ -> mainViewModel.editRicpee(id, updatedTitle.text.toString(),updatedIns.text.toString(),updatedAuth.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Instructions")
        alert.setView(
            updatedIns
            )
        alert.show()
    }

}