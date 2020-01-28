package com.example.mobiletasks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiletasks.Equipment;
import com.example.mobiletasks.EquipmentDetailsActivity;
import com.example.mobiletasks.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {

    private List<Equipment> equipmentList;
    private Context context;

    public EquipmentAdapter(Context context, List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public EquipmentAdapter.EquipmentViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                                                   final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipment, parent, false);
        return new EquipmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EquipmentViewHolder holder,
                                 final int position) {
        Picasso.get().load(equipmentList.get(position).getPhotoUrl()).into(holder.photoUrl);
        holder.equipmentType.setText(equipmentList.get(position).getEquipmentType());
        holder.equipmentName.setText(equipmentList.get(position).getEquipmentName());
        holder.price.setText(equipmentList.get(position).getPrice());
        holder.workingArea.setText(equipmentList.get(position).getWorkingArea());
        holder.description.setText(equipmentList.get(position).getDescription());
        holder.parentLayout.setOnClickListener(view -> openItemDetails(position));

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    class EquipmentViewHolder extends RecyclerView.ViewHolder {

        private TextView equipmentType;
        private TextView equipmentName;
        private TextView price;
        private TextView workingArea;
        private TextView description;
        private ImageView photoUrl;
        private LinearLayout parentLayout;

        private EquipmentViewHolder(final View itemView) {
            super(itemView);

            photoUrl = itemView.findViewById(R.id.item_equipment_image_view);
            equipmentType = itemView.findViewById(R.id.item_equipment_type);
            equipmentName = itemView.findViewById(R.id.item_equipment_name);
            price = itemView.findViewById(R.id.item_price);
            workingArea = itemView.findViewById(R.id.item_working_state);
            description = itemView.findViewById(R.id.item_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    private void openItemDetails(int position) {
        Intent intent = new Intent(context, EquipmentDetailsActivity.class);
        intent.putExtra("equipment_type", equipmentList.get(position).getEquipmentType());
        intent.putExtra("equipment_name", equipmentList.get(position).getEquipmentName());
        intent.putExtra("price", equipmentList.get(position).getPrice());
        intent.putExtra("working_area", equipmentList.get(position).getWorkingArea());
        intent.putExtra("description", equipmentList.get(position).getDescription());
        intent.putExtra("image", equipmentList.get(position).getPhotoUrl());

        context.startActivity(intent);
    }
}