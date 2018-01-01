package org.oxlifeproject.etat.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.model.SectionModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tuti on 30/12/2017.
 */

public class SectionDAO {

    public ArrayList<SectionModel> getAllSections(SQLiteHelper sQLiteHelper) {

        SQLiteDatabase database;

        try {
            database=sQLiteHelper.HelperSeqExec();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        Cursor cursor = database.rawQuery("SELECT * FROM Section", null);
        ArrayList<SectionModel> sections = new ArrayList<SectionModel>();
        SectionModel sectionModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                sectionModel = new SectionModel();
                sectionModel.setId(Integer.parseInt(cursor.getString(0)));
                sectionModel.setTitle(cursor.getString(1));
                sectionModel.setAuthors(cursor.getString(2));
                sectionModel.setContributors(cursor.getString(3));
                sectionModel.setIntro(cursor.getString(4));
                sectionModel.setInfo(cursor.getString(5));
                sections.add(sectionModel);
            }
        }
        cursor.close();
        database.close();
        return sections;
    }
}
