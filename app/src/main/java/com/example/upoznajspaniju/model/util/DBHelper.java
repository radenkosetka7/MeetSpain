package com.example.upoznajspaniju.model.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.upoznajspaniju.model.entities.Atrakcija;
import com.example.upoznajspaniju.model.entities.Drzava;
import com.example.upoznajspaniju.model.entities.Grad;
import com.example.upoznajspaniju.model.entities.Novost;

import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="spain_data.db";
    private static final String TABLE_NAME_CITY ="city";
    private static final String TABLE_NAME_NEWS="news";
    private static final String TABLE_NAME_ATTRACTIONS="attractions";
    public static final Integer DATABASE_VERSION=1;
    private static final String ID_COL="id";

    private static final String NAZIV_GRADA="naziv";
    private static final String OPIS="opis";
    private static final String VIDEO="video";

    private static final String NAZIV_ATRKACIJE="atrkacija";
    private static final String OMILJENA="omiljena";
    private static final String SLIKA="slika";
    private static final String LATITUDE="latitude";
    private static final String LONGITUDE="longitude";

    private static final String NASLOV="naslov";
    private static final String URL="url";
    private static final String SADRZAJ="sadrzaj";


    private static final String create_city="CREATE TABLE " + TABLE_NAME_CITY + " (" +
            ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAZIV_GRADA + " TEXT," +
            OPIS + " TEXT," +
            VIDEO + " TEXT," +
            LATITUDE + " REAL DEFAULT 0," +
            LONGITUDE + " REAL DEFAULT 0)";

    private static final String create_attraction="CREATE TABLE " + TABLE_NAME_ATTRACTIONS +
            " (" + ID_COL +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAZIV_ATRKACIJE + " TEXT," +
            SLIKA + " TEXT," +
            OPIS + " TEXT," +
            OMILJENA + " INTEGER DEFAULT 0," +
            LATITUDE + " REAL DEFAULT 0," +
            LONGITUDE + " REAL DEFAULT 0)";

    private static final String create_news="CREATE TABLE " + TABLE_NAME_NEWS +
            " (" + ID_COL +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NASLOV + " TEXT," +
            SLIKA + " TEXT," +
            URL + " TEXT," +
            SADRZAJ + " TEXT)";

    private static final String SQL_DELETE_ENTRY_CITY =
            "DROP TABLE IF EXISTS " + TABLE_NAME_CITY;

    private static final String SQL_DELETE_ENTRY_NEWS =
            "DROP TABLE IF EXISTS " + TABLE_NAME_NEWS;

    public DBHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(create_city);
        sqLiteDatabase.execSQL(create_news);
        sqLiteDatabase.execSQL(create_attraction);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY_CITY);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY_NEWS);
        onCreate(sqLiteDatabase);
    }

    public void insertGrad(Grad grad)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAZIV_GRADA,grad.getNaziv());
        values.put(VIDEO,grad.getVideo());
        values.put(OPIS,grad.getOpis());
        values.put(LATITUDE,grad.getLatitude());
        values.put(LONGITUDE,grad.getLongitude());
        db.insert(TABLE_NAME_CITY,null,values);
        db.close();
    }

    public void insertAtrakcija(Atrakcija atrakcija)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAZIV_ATRKACIJE,atrakcija.getIme());
        values.put(SLIKA,atrakcija.getSlika());
        values.put(OPIS,atrakcija.getOpis());
        values.put(OMILJENA,atrakcija.getOmiljena());
        values.put(LATITUDE,atrakcija.getLatitude());
        values.put(LONGITUDE,atrakcija.getLongitude());
        db.insert(TABLE_NAME_ATTRACTIONS,null,values);
        db.close();
    }

    public void insertNovost(Novost novost)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NASLOV,novost.getNaslov());
        values.put(SLIKA,novost.getSlika());
        values.put(SADRZAJ,novost.getSadrzaj());
        values.put(URL,novost.getUrl());
        db.insert(TABLE_NAME_NEWS,null,values);
        db.close();
    }
    @SuppressLint("Range")
    public List<Grad> getAllGradovi()
    {
        List<Grad> gradovi=new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_CITY, null);
        res.moveToFirst();
        while(res.isAfterLast()==false)
        {
            Grad tempGrad=new Grad();
            tempGrad.setNaziv(res.getString(res.getColumnIndex(NAZIV_GRADA)));
            tempGrad.setVideo(res.getString(res.getColumnIndex(VIDEO)));
            tempGrad.setOpis(res.getString(res.getColumnIndex(OPIS)));
            tempGrad.setLatitude(res.getDouble(res.getColumnIndex(LATITUDE)));
            tempGrad.setLongitude(res.getDouble(res.getColumnIndex(LONGITUDE)));
            gradovi.add(tempGrad);
            res.moveToNext();
        }
        res.close();
        return gradovi;
    }

    @SuppressLint("Range")
    public List<Atrakcija> getAllOmiljeno()
    {
        List<Atrakcija> atrakcije=new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_ATTRACTIONS, null);
        res.moveToFirst();
        while(res.isAfterLast()==false)
        {
            Atrakcija tempAtrakcija=new Atrakcija();
            tempAtrakcija.setIme(res.getString(res.getColumnIndex(NAZIV_ATRKACIJE)));
            tempAtrakcija.setSlika(res.getString(res.getColumnIndex(SLIKA)));
            tempAtrakcija.setOpis(res.getString(res.getColumnIndex(OPIS)));
            tempAtrakcija.setOmiljena(Integer.valueOf(res.getString(res.getColumnIndex(OMILJENA))));
            tempAtrakcija.setLatitude(res.getDouble(res.getColumnIndex(LATITUDE)));
            tempAtrakcija.setLongitude(res.getDouble(res.getColumnIndex(LONGITUDE)));
            if(tempAtrakcija.getOmiljena().equals(Integer.valueOf("1")))
            {
                atrakcije.add(tempAtrakcija);
            }
            res.moveToNext();
        }
        res.close();
        return atrakcije;
    }

    @SuppressLint("Range")
    public List<Atrakcija> getAllAtrakcije()
    {
        List<Atrakcija> atrakcije=new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_ATTRACTIONS, null);
        res.moveToFirst();
        while(res.isAfterLast()==false)
        {
            Atrakcija tempAtrakcija=new Atrakcija();
            tempAtrakcija.setIme(res.getString(res.getColumnIndex(NAZIV_ATRKACIJE)));
            tempAtrakcija.setSlika(res.getString(res.getColumnIndex(SLIKA)));
            tempAtrakcija.setOpis(res.getString(res.getColumnIndex(OPIS)));
            tempAtrakcija.setOmiljena(Integer.valueOf(res.getString(res.getColumnIndex(OMILJENA))));
            tempAtrakcija.setLatitude(res.getDouble(res.getColumnIndex(LATITUDE)));
            tempAtrakcija.setLongitude(res.getDouble(res.getColumnIndex(LONGITUDE)));
            tempAtrakcija.setId(Integer.parseInt(res.getString(res.getColumnIndex(ID_COL))));

            atrakcije.add(tempAtrakcija);
            res.moveToNext();
        }
        res.close();
        return atrakcije;
    }


    @SuppressLint("Range")
    public List<Novost> getAllNovosti()
    {
        List<Novost> novosti=new LinkedList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_NEWS, null);
        res.moveToFirst();
        while(res.isAfterLast()==false)
        {
            Novost tempNovost=new Novost();
            tempNovost.setNaslov(res.getString(res.getColumnIndex(NASLOV)));
            tempNovost.setSlika(res.getString(res.getColumnIndex(SLIKA)));
            tempNovost.setSadrzaj(res.getString(res.getColumnIndex(SADRZAJ)));
            tempNovost.setUrl(res.getString(res.getColumnIndex(URL)));
            novosti.add(tempNovost);
            res.moveToNext();
        }
        res.close();
        return novosti;
    }

    public void updateAtrakcija(Integer key,Integer value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String update = "UPDATE "+TABLE_NAME_ATTRACTIONS+" SET "+OMILJENA+" = "+value.toString()+
                " WHERE "+ID_COL+" = "+key;
        db.execSQL(update);
    }

    public void deleteItems()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM "+TABLE_NAME_CITY;
        db.execSQL(delete);

        SQLiteDatabase db1 = this.getWritableDatabase();
        String delete1 = "DELETE FROM "+TABLE_NAME_ATTRACTIONS;
        db.execSQL(delete1);
    }

    public void deleteNovosti()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM "+TABLE_NAME_NEWS;
        db.execSQL(delete);
    }
}
