package com.example.studentportal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class PortalViewHolder extends RecyclerView.ViewHolder {
    public Button link;
    public View view;
    public PortalViewHolder(@NonNull View itemView) {
        super( itemView );
        view = itemView;
        link = itemView.findViewById( R.id.button );
    }
}
