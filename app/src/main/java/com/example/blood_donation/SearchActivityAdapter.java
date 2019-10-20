package com.example.blood_donation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.ViewHolder> {

    Context context;
    List<ProfileModel> list;

    public SearchActivityAdapter(Context context, List<ProfileModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_search_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProfileModel model = list.get(position);
        
        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.phone.setText(model.getPhone());
        holder.city.setText(model.getCity());
        holder.email.setText(model.getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView phone;
        TextView age;
        TextView city;
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            phone = itemView.findViewById(R.id.textViewPhone);
            age = itemView.findViewById(R.id.textViewAge);
            city = itemView.findViewById(R.id.textViewCity);
            email = itemView.findViewById(R.id.textViewEmail);
        }
    }
}
