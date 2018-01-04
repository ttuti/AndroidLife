package org.oxlifeproject.etat.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.dao.SectionContentChildrenDAO;
import org.oxlifeproject.etat.db.config.SQLiteHelper;
import org.oxlifeproject.etat.model.SectionContentChildrenModel;
import org.oxlifeproject.etat.model.SectionContentModel;

import java.util.ArrayList;

/**
 * Created by Tuti on 03/01/2018.
 */


public class ViewPagerChapterAdapter extends PagerAdapter {

    private ArrayList<SectionContentModel> content;
    private LayoutInflater inflater;
    private SectionContentChildrenDAO sectionContentChildrenDAO;
    private SQLiteHelper sQLiteHelper;
    private Context context;

    public ViewPagerChapterAdapter(Context context, ArrayList<SectionContentModel> content) {

        this.content = content;
        this.context = context;
        this.sQLiteHelper = new SQLiteHelper(context);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sectionContentChildrenDAO = new SectionContentChildrenDAO();
    }
    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        (view).removeView((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {

        position = position % content.size();

        SectionContentModel sectionContentModel = content.get(position);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.page_content, null);

        if(sectionContentModel.isHasChildren()){

            ArrayList<SectionContentChildrenModel> children= sectionContentChildrenDAO.getContentChildren(sQLiteHelper,sectionContentModel.getId());
            RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.content_children, null);
            RecyclerView recyclerView = relativeLayout.findViewById(R.id.child_recycler);
            recyclerView.setHasFixedSize(true);

            StaggeredGridLayoutManager sglChildren = new StaggeredGridLayoutManager(2, 1);
            recyclerView.setLayoutManager(sglChildren);

            ContentChildrenRecyclerAdapter rcAdapter = new ContentChildrenRecyclerAdapter(context, children);
            recyclerView.setAdapter(rcAdapter);

            FrameLayout frame = (FrameLayout) layout.findViewById(R.id.child_frame);
            frame.setVisibility(View.VISIBLE);
            //TODO recyclerView becomes visible but no data is attached. Why?
        }
        TextView title = layout.findViewById(R.id.contentTitle);
        TextView content = layout.findViewById(R.id.contentSub);

        title.setText(sectionContentModel.getTitle());
        content.setText(sectionContentModel.getContent());
        (view).addView(layout);
        return layout;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
 }