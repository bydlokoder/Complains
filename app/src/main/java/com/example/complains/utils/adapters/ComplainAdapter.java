package com.example.complains.utils.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.complains.R;
import com.example.complains.activity.DocumentActivity;
import com.example.complains.utils.Complain;

import java.util.List;

public class ComplainAdapter extends RecyclerView.Adapter<ComplainAdapter.ViewHolder>
        implements Filterable {
    private List<Complain> complains;
    private Context context;

    public ComplainAdapter(List<Complain> complains, Context context) {
        this.complains = complains;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complain_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Complain complain = complains.get(position);
        holder.name.setText(complain.getName());
        holder.compainName.setText(complain.getAction().getName());
    }

    @Override
    public int getItemCount() {
        return complains.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView compainName;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            compainName = (TextView) itemView.findViewById(R.id.complainType);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle b = new Bundle();
            b.putSerializable(DocumentActivity.DOCUMENT_KEY, complains.get(getAdapterPosition()).getAction());
            Intent intent = new Intent(context, DocumentActivity.class);
            intent.putExtras(b);
            context.startActivity(intent);
        }
    }
}
