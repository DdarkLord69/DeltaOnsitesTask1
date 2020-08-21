package com.example.directorytree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<ItemData> itemData;

    public ItemAdapter(ArrayList<ItemData> itemData) {
        this.itemData = itemData;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        final ItemData currentItem = itemData.get(position);

        holder.itemName.setText(currentItem.getName());
        holder.icon.setImageResource( (currentItem.isDirectory())? R.drawable.ic_folder : R.drawable.ic_file);

        if(!currentItem.isDirectory()) {
            holder.arrow.setImageResource(R.drawable.ic_null);
        }

        if(currentItem.isDirectory()) {

            ArrayList<ItemData> list = new ArrayList<>();
            File[] files = currentItem.getFile().listFiles();
            for(File file : files) {
                int i = (file.isDirectory()) ? R.drawable.ic_folder : R.drawable.ic_file;
                list.add(new ItemData(file.getName(), file, file.isDirectory(), i, false));
            }

            ItemAdapter adapter = new ItemAdapter(list);
            holder.subList.setAdapter(adapter);
            holder.subList.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        }

        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentItem.isExpanded()) {
                    if(currentItem.isDirectory()) {
                        holder.arrow.setImageResource(R.drawable.ic_closed);
                    }
                        currentItem.setExpanded(false);
                    holder.subList.setVisibility(View.GONE);
                } else {
                    if(currentItem.isDirectory()) {
                        holder.arrow.setImageResource(R.drawable.ic_expanded);
                    }
                    currentItem.setExpanded(true);
                    holder.subList.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public ImageView icon;
        public ImageView arrow;
        public RecyclerView subList;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            icon = itemView.findViewById(R.id.item_icon);
            arrow = itemView.findViewById(R.id.arrow);
            subList = itemView.findViewById(R.id.sub_list);
        }
    }
}
