package org.oxlifeproject.etat.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.oxlifeproject.etat.R;
import org.oxlifeproject.etat.model.SectionModel;

import java.util.ArrayList;

/**
 * Created by Tuti on 29/12/2017.
 */

public class SectionAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<SectionModel> sections;

    public SectionAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);
        this.context= context;
        sections=objects;
    }

    private class ViewHolder  {
        TextView title;
        TextView author;
        TextView contributor;
        TextView id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        ViewHolder holder=null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.section_cardview, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.sectionName);
            holder.author = (TextView) convertView.findViewById(R.id.sectionAuthor);
            holder.contributor = (TextView) convertView.findViewById(R.id.sectionContributor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SectionModel individualSection= sections.get(position);
        holder.title.setText("Section "+individualSection.getId()+": " + individualSection.getTitle());
        holder.author.setText("Author: "+ individualSection.getAuthors());
        holder.contributor.setText("Contributors: "+individualSection.getContributors());
        return convertView;
    }
}
