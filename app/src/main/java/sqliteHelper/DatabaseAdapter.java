package sqliteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.josephair.sqlite.Message;

/**
 * Created by Joseph_Air on 10/12/14.
 */
public class DatabaseAdapter {

         SqliteHelper helper;
    //constructor for Helper

    public  DatabaseAdapter(Context context){

        helper = new SqliteHelper(context);
     }
 // function to Insert in to database
    public long insertData(String name, String password){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteHelper.NAME,name);
        contentValues.put(SqliteHelper.PASSWORD,password);
        long id=database.insert(SqliteHelper.TABLE_NAME, null,contentValues);
        return id;
    }
//function to get data from database
     public  String getAllData(){
         String[] columns={SqliteHelper.UID,SqliteHelper.NAME,SqliteHelper.PASSWORD};
         SQLiteDatabase database =helper.getWritableDatabase();
         Cursor cursor = database.query(SqliteHelper.TABLE_NAME,columns,null,null,null,null,null);
         StringBuffer buffer = new StringBuffer();
         while (cursor.moveToNext()){
            int cid = cursor.getInt(0);
             String name = cursor.getString(1);
             String password = cursor.getString(2);
             buffer.append(cid +" " +name +" " +password +"\n");
         }
         return  buffer.toString();
     }
    static class SqliteHelper extends SQLiteOpenHelper  {
        private static  final String DATABASE_NAME ="litedatabase";
        private static  final String TABLE_NAME="User";
        private static  final int DATABASE_VERSION =2;
        private static final String UID = "_id";
        private static  final String NAME = "Name";
        private static  final String PASSWORD = "password";
        //Creating table
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+
                " VARCHAR(255), "+PASSWORD+" VARCHAR(255));";

        //Drop Table if EXIST
        private static final String DROP_TABLE= "DROP TABLE IF EXISTS "+TABLE_NAME;
        //context variable
        private Context context;

        public SqliteHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);

            //context Initialization;
            this.context=context;
            //To Know when Method is called
            Message.message(context,"Constructor was called");

        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            //Create database here
            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context,"onCreate is called");

            } catch (SQLException e) {
                Message.message(context,""+e);
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"onUpgrade is called");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context,""+e);
            }

        }
    }

}
