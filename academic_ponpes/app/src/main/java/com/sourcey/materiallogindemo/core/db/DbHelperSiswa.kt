package com.sourcey.materiallogindemo.core.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sourcey.materiallogindemo.models.SiswaModels


class DbHelperSiswa (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "siswa"
        private const val TBL_SISWA = "tbl_siswa"
        private const val ID = "id"
        private const val card_code = "card_code"
        private const val nis = "nis"
        private const val nama = "nama"
    }

    override fun onCreate(db: SQLiteDatabase) {
        var createTblSiswa = "CREATE TABLE " + TBL_SISWA + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                card_code + " TEXT, " +
                nis + " TEXT, " +
                nama + " TEXT );";
        db.execSQL(createTblSiswa)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_SISWA")
        onCreate(db)
    }

    fun insertSiswa(siswaModels: SiswaModels): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, siswaModels.id)
        contentValues.put(card_code, siswaModels.card_code)
        contentValues.put(nis, siswaModels.nis)
        contentValues.put(nama, siswaModels.nama)

        val success = db.insert(TBL_SISWA, null, contentValues)
        db.close()
        return success

    }

    fun getAllSiswa(): ArrayList<SiswaModels>{
        val siswaList: ArrayList<SiswaModels> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_SISWA"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var card_code: String
        var nis: String
        var nama: String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                card_code =  cursor.getString(cursor.getColumnIndex("card_code"))
                nis = cursor.getString(cursor.getColumnIndex("nis"))
                nama = cursor.getString(cursor.getColumnIndex("nama"))
                val siswa = SiswaModels(id, card_code, nis, nama)
                siswaList.add(siswa)
            }while (cursor.moveToNext())
        }
        return siswaList
    }

    fun updateSiswa(id: Int, card_code: String, nis: String, nama: String): Int{
        var db = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(ID, id)
        contentValues.put("card_code", card_code)
        contentValues.put("nis", nis)
        contentValues.put("nama", nama)

        val success = db.update(TBL_SISWA, contentValues, "id=$id", null)
        db.close()
        return success

    }

    fun deleteSiswa(id: Int): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val succress = db.delete(TBL_SISWA, "id=$id", null)
        db.close()
        return succress
    }


}