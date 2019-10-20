package com.example.blood_donation.ui.Requests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood_donation.R;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.Holder> {
    List<RequestsModel> models;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_requests,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        RequestsModel model=models.get(position);
        holder.name.setText(model.getName());
        holder.blood.setText(model.getBlood());
        holder.phone.setText(model.getPhone());
        holder.message.setText(model.getMessage());
        holder.place.setText(model.getPlace());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    void setData(List<RequestsModel> models){
        this.models=models;
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView name,blood,phone,place,message,donated;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.req_name);
            blood=itemView.findViewById(R.id.req_blood);
            phone=itemView.findViewById(R.id.req_phone);
            place=itemView.findViewById(R.id.req_place);
            message=itemView.findViewById(R.id.req_message);
        }
    }
}
