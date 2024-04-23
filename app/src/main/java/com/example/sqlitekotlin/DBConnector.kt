package com.example.sqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBConnector(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NOMBRE_COl + " TEXT," +
                EDAD_COL + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun guardarEnDB(nombre : String, edad : String ){

        val values = ContentValues()

        values.put(NOMBRE_COl, nombre)
        values.put(EDAD_COL, edad)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun leerDB(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }


    companion object{

        private val DATABASE_NAME = "ANDROID_DEVS"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "usuario"

        val ID_COL = "id"

        val NOMBRE_COl = "nombre"

        val EDAD_COL = "edad"
    }


}
