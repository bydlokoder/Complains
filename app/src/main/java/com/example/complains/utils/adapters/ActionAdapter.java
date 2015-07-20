package com.example.complains.utils.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.complains.R;
import com.example.complains.activity.DocumentActivity;
import com.example.complains.utils.entities.Action;

import java.util.List;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ViewHolder> {

    private List<Action> actionList;
    private Context context;

    public ActionAdapter(List<Action> actionList, Context context) {
        this.actionList = actionList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.agreement_type_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Action action = actionList.get(position);
        holder.name.setText(action.getName());
    }

    @Override
    public int getItemCount() {
        return actionList.size();
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
                    Intent intent = new Intent(context, DocumentActivity.class);
                    Bundle b = new Bundle();
                    Action action = actionList.get(getAdapterPosition());
                    b.putSerializable(DocumentActivity.DOCUMENT_KEY, action);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Action action = actionList.get(getAdapterPosition());
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(action.getLink()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
