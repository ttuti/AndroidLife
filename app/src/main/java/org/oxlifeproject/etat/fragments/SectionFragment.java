package org.oxlifeproject.etat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.dao.SectionContentDAO;
import org.oxlifeproject.etat.dao.SectionDAO;
import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.adapters.SectionAdapter;
import org.oxlifeproject.etat.model.SectionModel;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SectionFragment extends Fragment {

    private SQLiteHelper sQLiteHelper;
    private Context context;
    private SectionAdapter sectionAdapter;
    private SectionDAO sectionDAO;
    private SectionContentDAO sectionContentDAO;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        sQLiteHelper = new SQLiteHelper(context);
        sectionDAO = new SectionDAO();


        View thisView = inflater.inflate(R.layout.fragment_section, container, false);
        ArrayList<SectionModel> sections = sectionDAO.getAllSections(sQLiteHelper);

        sectionAdapter= new SectionAdapter(context,R.layout.fragment_section,sections);
        listView = (ListView) thisView.findViewById(R.id.sectionListView);
        listView.setAdapter(sectionAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int SectionID=position+1;
                Log.w(TAG, String.valueOf(SectionID));
                ViewPagerFragment pagerFragment = new ViewPagerFragment();
                Bundle args = new Bundle();
                args.putString("SECTION_ID", String.valueOf(SectionID));
                pagerFragment.setArguments(args);

                FragmentManager fragmentManager =  SectionFragment.this.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainLayout, pagerFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        sQLiteHelper.close();
        return thisView;
    }
}
