package org.oxlifeproject.etat.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.model.SectionContentChildrenModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tuti on 03/01/2018.
 */

public class SectionContentChildrenDAO {

    public ArrayList<SectionContentChildrenModel> getContentChildren(SQLiteHelper sQLiteHelper, int SectionContentID) {

        SQLiteDatabase database;

        try {
            database=sQLiteHelper.HelperSeqExec();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        Cursor cursor = database.rawQuery("SELECT * FROM SectionContentChildren WHERE SectionContent = "+SectionContentID, null);
        ArrayList<SectionContentChildrenModel> children = new ArrayList<SectionContentChildrenModel>();
        SectionContentChildrenModel child;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                child = new SectionContentChildrenModel();
                child.setID(Integer.parseInt(cursor.getString(0)));
                child.setTitle(cursor.getString(1));
                child.setContent(cursor.getString(2));
                child.setSectionContent(Integer.parseInt(cursor.getString(3)));
                children.add(child);
            }
        }
        cursor.close();
        database.close();
        return children;
    }
}
