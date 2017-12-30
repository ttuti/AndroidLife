/**
 * Created by Tuti on 29/12/2017.
 */

package org.oxlifeproject.etat.db.config;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.oxlifeproject.etat.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

public class SQLiteHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "life.db";

    private static String DATABASE_PATH = "";
    private final Context mContext;
    private boolean mNeedUpdate = true;

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
    }

    public void updateDataBase() throws IOException {
        Log.w(TAG,"Inside SQLiteOpenHelper.updateDatabase()");
        if (mNeedUpdate) {
            File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();
            mNeedUpdate = false;
        }
    }

    public SQLiteDatabase HelperSeqExec()throws IOException{
        updateDataBase();
        copyDataBase();
        if(!openDataBase()){
            throw new Error("ErrorOpeningDataBase");
        }
        return this.getWritableDatabase();
    }

    private void copyDataBase() {
        Log.w(TAG,"Inside SQLiteOpenHelper.copyDataBase()");
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (!dbFile.exists()) {
            this.getReadableDatabase();
            this.close();
            try {
                InputStream mInput = mContext.getResources().openRawResource(R.raw.life);
                OutputStream mOutput = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);
                byte[] mBuffer = new byte[1024];
                int mLength;
                while ((mLength = mInput.read(mBuffer)) > 0)
                    mOutput.write(mBuffer, 0, mLength);
                mOutput.flush();
                mOutput.close();
                mInput.close();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    public boolean openDataBase() throws SQLException {
        Log.w(TAG,"Inside SQLiteOpenHelper.openDataBase()");
        database = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return database != null;
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(TAG,"Inside SQLiteOpenHelper.onCreate()");
        try {
            this.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"Inside SQLiteOpenHelper.onUpgrade()");
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }
}
