package org.oxlifeproject.etat.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.oxlifeproject.etat.R;

/**
 * Created by Tuti on 03/01/2018.
 */

public class ChildContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView title;
    public TextView content;

    public ChildContentViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        title = (TextView) itemView.findViewById(R.id.child_title);
        content = (TextView) itemView.findViewById(R.id.child_content);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
    }

}
