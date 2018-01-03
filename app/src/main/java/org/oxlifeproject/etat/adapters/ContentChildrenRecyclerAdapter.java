package org.oxlifeproject.etat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.fragments.ChildContentViewHolder;
import org.oxlifeproject.etat.model.SectionContentChildrenModel;

import java.util.List;

/**
 * Created by Tuti on 03/01/2018.
 */

public class ContentChildrenRecyclerAdapter extends RecyclerView.Adapter<ChildContentViewHolder> {


    private List<SectionContentChildrenModel> children;
    private Context context;

    public ContentChildrenRecyclerAdapter(Context context, List<SectionContentChildrenModel> children) {
        this.children = children;
        this.context = context;
    }

    @Override
    public ChildContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(context).inflate(R.layout.page_content, null);
        ChildContentViewHolder rcv = new ChildContentViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ChildContentViewHolder holder, int position) {
        holder.title.setText(children.get(position).getTitle());
        holder.content.setText(children.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return this.children.size();
    }

}
