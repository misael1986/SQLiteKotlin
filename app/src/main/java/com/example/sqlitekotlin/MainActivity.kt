package com.example.sqlitekotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guardar = findViewById<Button>(R.id.guardar)

        val leer = findViewById<Button>(R.id.leer)

//----------------------------------------------------

        guardar.setOnClickListener{

            val db = DBConnector(this, null)

            val nombre = findViewById<EditText>(R.id.nombre)
            val edad = findViewById<EditText>(R.id.edad)

            val nombreTxt = nombre.text.toString()
            val edadTxt = edad.text.toString()

            db.guardarEnDB(nombreTxt, edadTxt)

            Toast.makeText(this, nombre.text.toString() + " almacenado en la DB", Toast.LENGTH_LONG).show()

            nombre.text.clear()
            edad.text.clear()
        }

//-----------------------------------------------

        leer.setOnClickListener{

            val db = DBConnector(this, null)

            val lista_nombres = findViewById<TextView>(R.id.lista_nombres)
            val lista_edades = findViewById<TextView>(R.id.lista_edades)

            val cursor = db.leerDB()


            cursor!!.moveToFirst()

            lista_nombres.append(cursor.getString(cursor.getColumnIndexOrThrow(DBConnector.NOMBRE_COl)) + "\n")
            lista_edades.append(cursor.getString(cursor.getColumnIndexOrThrow(DBConnector.EDAD_COL)) + "\n")


            while(cursor.moveToNext()){
                lista_nombres.append(cursor.getString(cursor.getColumnIndexOrThrow(DBConnector.NOMBRE_COl)) + "\n")
                lista_edades.append(cursor.getString(cursor.getColumnIndexOrThrow(DBConnector.EDAD_COL)) + "\n")
            }

            cursor.close()


        }
    }
}