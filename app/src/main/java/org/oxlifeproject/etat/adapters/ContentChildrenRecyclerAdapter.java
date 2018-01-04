package org.oxlifeproject.etat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.fragments.ChildContentViewHolder;
import org.oxlifeproject.etat.model.SectionContentChildrenModel;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Tuti on 03/01/2018.
 */

public class ContentChildrenRecyclerAdapter extends RecyclerView.Adapter<ChildContentViewHolder> {


    private List<SectionContentChildrenModel> children;

    public ContentChildrenRecyclerAdapter(List<SectionContentChildrenModel> children) {
        this.children = children;
    }

    @Override
    public ChildContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG,"Fire onCreateViewHolder()");
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_content, null);
        View childLayout = layoutView.findViewById(R.id.child_frame);
        ChildContentViewHolder rcv = new ChildContentViewHolder(childLayout);
        super.bindViewHolder(rcv, viewType);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ChildContentViewHolder holder, int position) {
        Log.e(TAG,"We are binding child content");
        holder.title.setText(children.get(position).getTitle());
        holder.content.setText(children.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return this.children.size();
    }

}
