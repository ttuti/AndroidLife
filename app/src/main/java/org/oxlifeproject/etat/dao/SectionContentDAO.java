package org.oxlifeproject.etat.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.model.SectionContentModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tuti on 31/12/2017.
 */

public class SectionContentDAO {

    public ArrayList<SectionContentModel> getAllSectionContent(SQLiteHelper sQLiteHelper, int SectionID) {

        SQLiteDatabase database;

        try {
            database=sQLiteHelper.HelperSeqExec();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        Cursor cursor = database.rawQuery("SELECT * FROM SectionContent WHERE Section = "+SectionID, null);
        ArrayList<SectionContentModel> sectionContents = new ArrayList<SectionContentModel>();
        SectionContentModel sectionContentModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                sectionContentModel = new SectionContentModel();
                sectionContentModel.setId(Integer.parseInt(cursor.getString(0)));
                sectionContentModel.setSection(Integer.parseInt(cursor.getString(1)));
                sectionContentModel.setTitle(cursor.getString(2));
                sectionContentModel.setContent(cursor.getString(3));
                sectionContentModel.setQuiz(Boolean.valueOf(cursor.getString(4)));
                sectionContentModel.setAssessment(Boolean.valueOf(cursor.getString(5)));
                sectionContentModel.setHasChildren(Boolean.valueOf(cursor.getString(6)));
                sectionContentModel.setVideo(Boolean.valueOf(cursor.getString(7)));
                sectionContentModel.setVideoURL(cursor.getString(8));
                sectionContents.add(sectionContentModel);
            }
        }
        cursor.close();
        database.close();
        return sectionContents;
    }
}
