package org.oxlifeproject.etat.fragments;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.dao.SectionDAO;
import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.adapters.SectionAdapter;
import org.oxlifeproject.etat.model.SectionModel;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SectionFragment extends Fragment {

    private SQLiteHelper sQLiteHelper;
    private SQLiteDatabase database;
    private Context context;
    private SectionAdapter sectionAdapter;
    private SectionDAO sectionDAO;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        sQLiteHelper = new SQLiteHelper(context);
        sectionDAO = new SectionDAO();
        try {
            database=sQLiteHelper.HelperSeqExec();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        View thisView = inflater.inflate(R.layout.fragment_section, container, false);

        ArrayList<SectionModel> sections = sectionDAO.getAllSections(database);

        sectionAdapter= new SectionAdapter(context,R.layout.fragment_section,sections);
        listView = (ListView) thisView.findViewById(R.id.sectionListView);
        listView.setAdapter(sectionAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int SectionID=position+1;
                Log.w(TAG,String.valueOf(SectionID));
            }
        });
        sQLiteHelper.close();
        return thisView;
    }

}
