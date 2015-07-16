package com.example.complains.utils.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.complains.R;
import com.example.complains.activity.ActionActivity;
import com.example.complains.utils.categories.Problem;

import java.io.Serializable;
import java.util.List;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder>
        implements Filterable {
    private List<Problem> problemList;
    private Context context;

    public ProblemAdapter(List<Problem> problemList, Context context) {
        this.problemList = problemList;
        this.context = context;
    }

    @Override
    public ProblemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.agreement_type_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProblemAdapter.ViewHolder holder, int position) {
        Problem problem = problemList.get(position);
        holder.name.setText(problem.getName());
    }

    @Override
    public int getItemCount() {
        return problemList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView help;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.complainType);
            help = (ImageView) itemView.findViewById(R.id.help);
            RelativeLayout layout = (RelativeLayout) itemView.findViewById(R.id.agreementLayout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ActionActivity.class);
                    Problem problem = problemList.get(getAdapterPosition());
                    Bundle b = new Bundle();
                    b.putSerializable(ActionActivity.ACTION_KEY, (Serializable) problem.getActionList());
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Problem problem = problemList.get(getAdapterPosition());
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(problem.getLink()));
                    context.startActivity(intent);
                }
            });
        }
    }
}