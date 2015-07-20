package com.example.complains.utils.adapters;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.complains.fragments.ActionFragment;
import com.example.complains.utils.entities.Problem;

import java.util.List;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder>
        implements Filterable {
    private List<Problem> problemList;
    private Context context;
    private FragmentManager manager;

    public ProblemAdapter(List<Problem> problemList, Context context, FragmentManager manager) {
        this.problemList = problemList;
        this.context = context;
        this.manager = manager;
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
                    Problem problem = problemList.get(getAdapterPosition());
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.fragment_container,
                            ActionFragment.newInstance(problem.getActionList(),
                                    context.getString(R.string.title_activity_action)));
                    ft.addToBackStack(null);
                    ft.commit();
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