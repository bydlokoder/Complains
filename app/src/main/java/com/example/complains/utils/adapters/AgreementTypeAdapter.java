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
import com.example.complains.fragments.ProblemFragment;
import com.example.complains.utils.categories.AgreementType;

import java.util.List;

public class AgreementTypeAdapter extends RecyclerView.Adapter<AgreementTypeAdapter.ViewHolder>
        implements Filterable {
    private List<AgreementType> agreementTypes;
    private Context context;
    private FragmentManager manager;

    public AgreementTypeAdapter(List<AgreementType> agreementTypes, Context context,
                                FragmentManager manager) {
        this.agreementTypes = agreementTypes;
        this.context = context;
        this.manager = manager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.agreement_type_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AgreementType type = agreementTypes.get(position);
        holder.name.setText(type.getName());
        //holder.icon.setImageDrawable(context.getDrawable((type.getIconResId())));
    }

    @Override
    public int getItemCount() {
        return agreementTypes.size();
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
                    AgreementType type = agreementTypes.get(getAdapterPosition());
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.fragment_container,
                            ProblemFragment.newInstance(type.getProblemList(),
                                    context.getString(R.string.title_activity_problem)));
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AgreementType type = agreementTypes.get(getAdapterPosition());
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(type.getLink()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
