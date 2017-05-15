package test.bwie.com.example.ins7566.kyzg.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SearchMyHepler extends SQLiteOpenHelper {
    public SearchMyHepler(Context context, String name, int version) {
        super(context, name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table search(name varchar(20) primary key)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
