package com.example.huandog_v02;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String table01 = "human";
    public static final String table02 = "dog";
    public static final String table03 = "walk";


    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag","db 생성_db가 없을때만 최초로 실행함");
        createTable(db);
        createTabled(db);
        createTableTime(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("drop table if exists walk");
    }

    public void createTable(SQLiteDatabase db){
        String sql01 = "CREATE TABLE if not exists "+ table01+ "(" +
                "jEmail text,"+
                "jPass text, " +
                "jPassChk text, " +
                "jName text," +
                "jPhone text," +
                "jAddr text)";
        try {
            db.execSQL(sql01);
        }catch (SQLException e){

        }

    }
    public void createTabled(SQLiteDatabase db){
        String sql02 = "CREATE TABLE if not exists "+ table02+ "(" +
                "dRegNum text,"+
                "dName text, " +
                "dAge text, " +
                "dSort text," +
                "dGender text," +
                "dPlace text)";
        try {
            db.execSQL(sql02);
        }catch (SQLException e){

        }


    }

    public void createTableTime (SQLiteDatabase db){
        String sql03 = "CREATE TABLE if not exists " + table03+"(" +
                "walkTime text," +
                "walkDay text)";
        try{
            db.execSQL(sql03);
        }catch(SQLException e){

        }
    }

    public void insertUser(SQLiteDatabase db, String email, String pass, String passChk, String name, String phone, String addr ){
        Log.i("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO"+table01+"(jEmail, jPass, jPassChk, JName, jPhone, jAddr)" +
                    "values('"+email+"','"+pass+"','"+passChk+"','"+name+"','"+phone+"','"+addr+"')'";
            db.execSQL(sql);
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void insertUserd(SQLiteDatabase db, String dnum, String dname, String dage, String dsort, String dgender, String dplace ){
        Log.i("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO "+table02+" (dRegNum,dName,dAge,dSort,dGender,dPlace)" +
                    "values('"+dnum+"','"+dname+"','"+dage+"','"+dsort+"','"+dgender+"','"+dplace+"')'";
            db.execSQL(sql);
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void insertTime (SQLiteDatabase db,String walkTime, String walkDay){
        db.beginTransaction();

        try{
            String sql = "INSERT INTO "+table03+ "(walkTime, walkDay)"+
                    "values ('"+walkTime+"','"+walkDay+"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

    }

}
