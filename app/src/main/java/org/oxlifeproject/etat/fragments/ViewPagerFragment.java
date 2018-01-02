package org.oxlifeproject.etat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.adapters.SectionAdapter;
import org.oxlifeproject.etat.adapters.SectionContentPagerAdapter;
import org.oxlifeproject.etat.dao.SectionContentDAO;
import org.oxlifeproject.etat.dao.SectionDAO;
import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.model.SectionContentModel;
import org.oxlifeproject.etat.model.SectionModel;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ViewPagerFragment extends Fragment {

    private SQLiteHelper sQLiteHelper;
    private Context context;
    private SectionAdapter sectionAdapter;
    private SectionDAO sectionDAO;
    private SectionContentDAO sectionContentDAO;
    ListView listView;
    private View childPagerView;
    private ViewPager viewPager;
    private LayoutInflater mInflater;
    private ViewGroup mContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        mContainer = container;
        mInflater = inflater;
        sQLiteHelper = new SQLiteHelper(context);
        sectionDAO = new SectionDAO();
        sectionContentDAO = new SectionContentDAO();


        View thisView = inflater.inflate(R.layout.pager_section_content, container, false);
        viewPager = (ViewPager) thisView.findViewById(R.id.viewpager);
        ArrayList<SectionContentModel> sectionContent = sectionContentDAO.getAllSectionContent(sQLiteHelper,1);
        //TODO: Add ViewPager + fragments
        /**
         *  Why is the code below not loading the section content in pages?
         */
        viewPager.setAdapter(new SectionContentPagerAdapter(context,sectionContent));
        viewPager.setVisibility(View.VISIBLE);
        sQLiteHelper.close();
        return thisView;
    }

}
