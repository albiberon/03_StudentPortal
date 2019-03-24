package com.example.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PortalAdapter extends RecyclerView.Adapter<PortalViewHolder> {
    private Context context;
    private List<Portal> portals;

    public PortalAdapter(Context context, List<Portal> portals) {
        this.context = context;
        this.portals = portals;
    }

    @NonNull
    @Override
    public PortalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button, parent, false);

        return new PortalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortalViewHolder viewHolder, int i) {
        // Gets a single item in the list from its position
        final Portal portal = portals.get(i);
        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        viewHolder.link.setText( portal.getLabel() );
    }

    @Override
    public int getItemCount() {
        return portals.size();
    }
}
