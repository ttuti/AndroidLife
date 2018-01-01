package org.oxlifeproject.etat.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.model.SectionContentModel;

import java.util.ArrayList;

/**
 * Created by Tuti on 01/01/2018.
 */

public class SectionContentPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<SectionContentModel> contents;

    public SectionContentPagerAdapter(Context context, ArrayList<SectionContentModel> sectionModelContent) {
        mContext = context;
        contents = sectionModelContent;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View detailView= vi.inflate(R.layout.page_content, null);

        ViewHolder holder = new ViewHolder();
        holder.title = (TextView) detailView.findViewById(R.id.contentTitle);
        holder.content = (TextView) detailView.findViewById(R.id.contentSub);
        detailView.setTag(holder);

        SectionContentModel individualSection= contents.get(position);
        holder.title.setText("Module "+individualSection.getId()+": " + individualSection.getTitle());
        holder.content.setText(individualSection.getContent());
        collection.addView(detailView);
        return detailView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private class ViewHolder  {
        TextView title;
        TextView content;
    }

}
