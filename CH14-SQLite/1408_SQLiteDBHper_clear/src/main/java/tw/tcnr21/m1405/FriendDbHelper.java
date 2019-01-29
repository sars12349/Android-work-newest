package tw.tcnr21.m1405;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class FriendDbHelper  extends SQLiteOpenHelper {
    String TAG = "tcnr21=>";
    public String sCreateTableCommand;    // 資料庫名稱
    private static final String DB_FILE = "friends.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;    // 資料表名稱
    private static final String DB_TABLE = "member";    // 資料庫物件，固定的欄位變數

    private static final String crTBsql = "CREATE TABLE " + DB_TABLE + " ( "
            + "id INTEGER PRIMARY KEY," + "name TEXT NOT NULL," + "grp TEXT,"
            + "address TEXT);";

    private static SQLiteDatabase database;


    public static SQLiteDatabase getDatabase(Context context){
        if (database == null || !database.isOpen())
        {
            database = new FriendDbHelper(context, DB_FILE, null, VERSION)
                    .getWritableDatabase();
        }
        return database;
    }


    public FriendDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //super(context, name, factory, version);
        super(context, "friends.db", null, 1);
        sCreateTableCommand = "";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crTBsql); //建立一個新的member table
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);

        onCreate(db);
    }

    public long insertRec(String b_name, String b_grp, String b_address) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues rec=new ContentValues();
        rec.put("name",b_name);
        rec.put("grp",b_grp);
        rec.put("address",b_address);
        long rowID=db.insert(DB_TABLE,null,rec);
        db.close();
        return rowID;
    }

    public int RecCount() {
        SQLiteDatabase db=getWritableDatabase();
        String sql="SELECT * FROM "+DB_TABLE;
        Cursor recSet=db.rawQuery(sql,null);
        return recSet.getCount();
    }

    public String FindRec(String tname) {
        SQLiteDatabase db=getReadableDatabase();
        String fldSet="ans=";
        String sql="SELECT * FROM "+DB_TABLE+" WHERE name LIKE ? ORDER BY id ASC ";
        String[] args={"%"+tname+"%"};

        Cursor recSet=db.rawQuery(sql,args); //arg擺入  sql 的?

        int columnCount=recSet.getColumnCount();
        Log.d(TAG,"ans:"+recSet.getCount());
        if(recSet.getCount()!=0)
        {
            recSet.moveToFirst();
            fldSet=recSet.getString(0)+" "
                    +recSet.getString(1)+" "
                    +recSet.getString(2)+" "
                    +recSet.getString(3)+"\n";

            while (recSet.moveToNext())
            {
                for(int i=0;i<columnCount;i++)
                {
                    fldSet+=recSet.getString(i)+" ";
                }
                fldSet+="\n";
            }
        }
        recSet.close();
        db.close();
        return fldSet;
    }

    public ArrayList<String> getRecSet() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE;
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();

        //----------------------------
        Log.d(TAG,"recSet="+recSet);
        int columnCount = recSet.getColumnCount();
        while(recSet.moveToNext()){
            String fldSet = "";
            for(int i=0; i<columnCount; i++)
                fldSet += recSet.getString(i) + "#";
            recAry.add(fldSet);
        }
        //------------------------
        recSet.close();
        db.close();

        Log.d(TAG,"recAry="+recAry);
        return recAry;
    }

    public void createTB() {
        // 批次新增
        int maxrec = 20;
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i <= maxrec; i++) {
            ContentValues newRow = new ContentValues();
            newRow.put("name", "路人" +  u_chinano(i));
            newRow.put("grp", "第" + u_chinayear((int) (Math.random() * 4 + 1)) + "組");
            newRow.put("address", "台中市西區工業一路" + (100 + i) + "號");
            db.insert(DB_TABLE, null, newRow);
        }
        db.close();
    }

    private String u_chinayear(int i) {
        String c_number = "";
        String china_no[]={"甲","乙","丙","丁","戊","己","庚","辛","仁","揆"};
        c_number = china_no[i % 10];

        return c_number;
    }

    private String u_chinano(int input_i) {
        String c_number = "";
        String china_no[]={"零","一","二","三","四","五","六","七","八","九"};
        c_number = china_no[input_i % 10];

        return c_number;
    }

    public int clearRec() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE;
        Cursor recSet = db.rawQuery(sql, null);
        if (recSet.getCount() != 0) {
//   String whereClause = "id < 0";
            int rowsAffected = db.delete(DB_TABLE, "1", null); //刪除所有的資料
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            db.close();
            return rowsAffected;  //回傳殺幾筆
        } else {
            db.close();
            return -1;  //return  =-1沒刪成功
        }
    }

}
