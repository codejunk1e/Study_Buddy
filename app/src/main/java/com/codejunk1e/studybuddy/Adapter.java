package com.codejunk1e.studybuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.XViewHolder> {

    ArrayList<String> Description;
    ArrayList<Integer> image;

    Context context;



    public Adapter(Context context, ArrayList<String> Description, ArrayList<Integer> image) {
        this.context = context;
        this.Description = Description;
        this.image = image;

    }


    @Override
    public XViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        XViewHolder vh = new XViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final XViewHolder holder, final int position) {
        // set the data in items
        holder.Description.setText(Description.get(position));
        holder.image.setImageResource(image.get(position));
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return Description.size();
    }

    public class XViewHolder extends RecyclerView.ViewHolder {
        public int mCurrentPosition;
        TextView Description;
        ImageView image;

        public XViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            Description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }


}

