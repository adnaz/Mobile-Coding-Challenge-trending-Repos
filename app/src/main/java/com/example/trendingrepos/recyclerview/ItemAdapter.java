package com.example.trendingrepos.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trendingrepos.R;
import com.example.trendingrepos.response.Item;

import java.util.Objects;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {

    private Context context;
    public ItemAdapter(Context context){

       super(DIFF_CALLBACK);
        this.context=context;
    }
    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = getItem(position);
        if (item != null) {
            holder.title.setText(item.name);
            holder.description.setText(item.description);
            holder.numberOfStars.setText(Integer.toString(item.stargazers_count));
            holder.ownerName.setText(item.owner.login);

            Glide.with(context)
                    .load(item.owner.avatar_url)
                    .into(holder.ownerImageView);
        }else{
            Toast.makeText(context, "Item is null", Toast.LENGTH_LONG).show();
        }
    }



    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView ownerName;
        TextView numberOfStars;
        ImageView ownerImageView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            ownerName = itemView.findViewById(R.id.ownerName);
            numberOfStars = itemView.findViewById(R.id.stars);
            ownerImageView=itemView.findViewById(R.id.imageView);
        }
    }
}

