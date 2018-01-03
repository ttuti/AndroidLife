package org.oxlifeproject.etat.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.model.SectionContentModel;

import java.util.ArrayList;

/**
 * Created by Tuti on 03/01/2018.
 */


public class ViewPagerChapterAdapter extends PagerAdapter {

    private ArrayList<SectionContentModel> content;
    private LayoutInflater inflater;

    public ViewPagerChapterAdapter(Context context, ArrayList<SectionContentModel> content) {

        this.content = content;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        (view).removeView((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {

        position = position % content.size();

        SectionContentModel sectionContentModel = content.get(position);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.page_content, null);

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