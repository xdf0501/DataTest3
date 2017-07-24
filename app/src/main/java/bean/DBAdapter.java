package bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
/**
 * Created by j on 2017/7/22.
 */

//Android中推荐使用DBAdapter类实现对数据库的添加删除查找，更新操作
public class DBAdapter {
    //申明数据库的基本信息 包括数据库的文件名称 表名称 和版本号 以及数据库表的属性名称
    private static final String DB_NAME = "people.db";
    private static final String DB_TABLE = "peopleinfo";
    private static final int DB_VERSION = 1;

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUM = "num";
    public static final String KEY_CHIN = "chin";
    public static final String KEY_MATH = "math";
    public static final String KEY_ENG = "eng";

    private SQLiteDatabase db;//申明数据库的实例
    private final Context context;
    private DBOpenHelper dbOpenHelper;// 申明了一个帮助类用来辅助建立 更新和打开数据库

    private static class DBOpenHelper extends SQLiteOpenHelper{
        public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
    private static final String DB_CREATE="CREATE table IF NOT EXISTS "+DB_TABLE
            + " (" + KEY_ID + " integer primary key autoincrement,"
            + KEY_NAME + " text not null,"
            + KEY_NUM + " integer,"
            + KEY_CHIN + " float,"
            + KEY_MATH + " float,"
            + KEY_ENG + " float)" ;

        //数据库第一次创建时被调用，一般用来创建数据库中的表并完成初始化工作
        @Override
        public void onCreate(SQLiteDatabase db) {
            //调用SQLiteDatabase实例的execSQL方法执行创建表的SQL命令
            db.execSQL(DB_CREATE);

        }
        //在数据库升级时被调用，一般用来删除旧的数据库表，并将新数据转移到新版本的数据库表中
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+DB_TABLE);
            onCreate(db);

        }
    }
    public DBAdapter(Context _context) {
        context = _context;
    }

    public void open() throws SQLiteException {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
            dbOpenHelper.onCreate(db);
        }
        catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }

    }
    /** Close the database */
    public void close() {
        if (db != null){
            db.close();
            db = null;
        }
    }




    //用来添加一条数据
    public long insert(People people){
        //创建一个ContentValue这个类的对象实例
        ContentValues newValues=new ContentValues();
        newValues.put(KEY_ID,people.ID);
        newValues.put(KEY_NAME,people.Name);
        newValues.put(KEY_NUM ,people.Num);
        newValues.put(KEY_CHIN,people.Chin);
        newValues.put(KEY_MATH ,people.Math);
        newValues.put(KEY_ENG,people.Eng);
        return db.insert(DB_TABLE, null, newValues);


    }


    public long deleteAllData(){
        return db.insert(DB_TABLE, null, null);
    }

    //根据id删除一条数据
    public long deleteOneData(long id){
        return db.delete(DB_TABLE,KEY_ID+"="+id,null);
    }

    //用来获取全部数据
    public People[] queryAllDATA(){
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_NUM, KEY_CHIN,KEY_MATH,KEY_ENG},
                null, null, null, null, null,null);
        return ConvertToPeople(results);
    }

    public People[] queryOneDATA(long id){
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_NUM, KEY_CHIN,KEY_MATH,KEY_ENG},
                KEY_ID + "=" + id, null, null, null,null);
        return ConvertToPeople(results);

    }

    //根据id更新一条数据
    public long updateOneData(long id,People people){
        ContentValues updateValues=new ContentValues();
        updateValues.put(KEY_NAME,people.Name);
        updateValues.put(KEY_NUM, people.Num);
        updateValues.put(KEY_CHIN, people.Chin);
        updateValues.put(KEY_MATH, people.Math);
        updateValues.put(KEY_ENG, people.Eng);
        return db.update(DB_TABLE,updateValues,KEY_ID+"="+id,null);

    }
    //将查询结果转换自定义的People类实例
    private People[] ConvertToPeople(Cursor cursor){
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()){
            return null;
        }
        People[] peoples = new People[resultCounts];
        for (int i = 0 ; i<resultCounts; i++){
            peoples[i] = new People();
            peoples[i].ID = cursor.getInt(0);
            peoples[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            peoples[i].Num = cursor.getInt(cursor.getColumnIndex(KEY_NUM));
            peoples[i].Chin = cursor.getFloat(cursor.getColumnIndex(KEY_CHIN));
            peoples[i].Math = cursor.getFloat(cursor.getColumnIndex(KEY_MATH));
            peoples[i].Eng = cursor.getFloat(cursor.getColumnIndex(KEY_ENG));


            cursor.moveToNext();
        }
        return peoples;
    }





}
