package org.oxlifeproject.etat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.adapters.ViewPagerChapterAdapter;
import org.oxlifeproject.etat.dao.SectionContentDAO;
import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.model.SectionContentModel;

import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {

    private SQLiteHelper sQLiteHelper;
    private Context context;
    private SectionContentDAO sectionContentDAO;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        sQLiteHelper = new SQLiteHelper(context);
        sectionContentDAO = new SectionContentDAO();

        View thisView = inflater.inflate(R.layout.pager_section_content, container, false);
        viewPager = (ViewPager) thisView.findViewById(R.id.viewpager);
        String sectionId = getArguments().getString("SECTION_ID");
        ArrayList<SectionContentModel> sectionContent = sectionContentDAO.getAllSectionContent(sQLiteHelper,Integer.valueOf(sectionId));

        final int content_count=sectionContent.size();

        viewPager.setAdapter(new ViewPagerChapterAdapter(context,sectionContent));
        viewPager.setVisibility(View.VISIBLE);
        sQLiteHelper.close();

        // Watch for button clicks.
        Button button = (Button)thisView.findViewById(R.id.goto_first);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        button = (Button)thisView.findViewById(R.id.goto_last);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(content_count-1);
            }
        });
        button = (Button)thisView.findViewById(R.id.goto_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() < (content_count-2)){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1,true);
                }
            }
        });
        button = (Button)thisView.findViewById(R.id.goto_prev);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() > 1){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1,true);
                }
            }
        });
        return thisView;
    }

}
