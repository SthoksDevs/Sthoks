package com.sthoksdevs.sthoks.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Calendar;

public class MyDb {
    private static String TBL_MEMO = "tblMemo";
    private static String TBL_QUIZ = "tblQuiz";
    private static final String CREATE_MEMO = ("CREATE TABLE " + TBL_MEMO + " (m_id INTEGER PRIMARY KEY AUTOINCREMENT,question VARCHAR(50) NOT NULL,answer VARCHAR(50))");
    private static final String CREATE_QUIZ = ("CREATE TABLE " + TBL_QUIZ + " (q_id INTEGER PRIMARY KEY AUTOINCREMENT,question VARCHAR(50) NOT NULL,answer VARCHAR(50) NOT NULL,attempt_time VARCHAR(16) NOT NULL,score INTEGER(2) NOT NULL)");
    private ContentValues cValues;
    private final Context context;

    /* renamed from: db */
    SQLiteDatabase f62db;
    DbHelper helper;

    public MyDb(Context context2) {
        this.context = context2;
        this.helper = new DbHelper(context2);
    }

    public class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, "sthoks", (SQLiteDatabase.CursorFactory) null, 1);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL(MyDb.CREATE_QUIZ);
                sQLiteDatabase.execSQL(MyDb.CREATE_MEMO);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.w("DBAdapter", "Upgrading database from version " + i + " to " + i2 + ". NOTE: Old data will be destroyed.");
            StringBuilder sb = new StringBuilder();
            sb.append("DROP TABLE IF EXISTS ");
            sb.append(MyDb.TBL_QUIZ);
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MyDb.TBL_MEMO);
            onCreate(sQLiteDatabase);
        }
    }

    public void open() throws SQLException {
        try {
            this.f62db = this.helper.getWritableDatabase();
        } catch (Exception unused) {
            this.f62db = this.helper.getReadableDatabase();
        }
    }

    public void close() {
        this.helper.close();
    }

    public void addToMemo(String[] strArr, String[] strArr2) {
        this.f62db = this.helper.getWritableDatabase();
        this.cValues = new ContentValues();
        Log.d("questions.length = " + strArr.length, " answers.length = " + strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            this.cValues.put("question", strArr[i]);
            this.cValues.put("answer", strArr2[i]);
            this.f62db.insert(TBL_MEMO, null, this.cValues);
        }
        if (this.cValues != null) {
            this.cValues = null;
        }
        this.f62db.close();
    }

    public void addToQuiz(String str, String str2, String str3) {
        this.f62db = this.helper.getWritableDatabase();
        Calendar instance = Calendar.getInstance();
        String str4 = String.valueOf(instance.get(11)) + ":" + String.valueOf(instance.get(12));
        this.cValues = new ContentValues();
        this.cValues.put("question", str);
        this.cValues.put("answer", str2);
        this.cValues.put("attempt_time", (String.valueOf(instance.get(5)) + "/" + String.valueOf(instance.get(2) + 1) + "/" + String.valueOf(instance.get(1))) + " " + str4);
        this.cValues.put("score", str3);
        this.f62db.insert(TBL_QUIZ, null, this.cValues);
        this.f62db.close();
        this.cValues = null;
    }

    public Cursor checkAnswer(String str) {
        this.f62db = this.helper.getReadableDatabase();
        return this.f62db.query(TBL_MEMO, new String[]{"answer"}, "question=?", new String[]{str}, null, null, null);
    }
}
