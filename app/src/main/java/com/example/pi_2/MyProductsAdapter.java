package com.example.pi_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {
    public JSONArray elements;
    private Context context;

    public MyProductsAdapter(JSONArray elements, Context context){
        this.elements = elements;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView first_line;
        RelativeLayout container;
        ImageView iamgen;

        public ViewHolder(View itemView) {
            super(itemView);
            first_line = itemView.findViewById(R.id.element_view2_first_line);
            container = itemView.findViewById(R.id.element_view2_container);
            iamgen = itemView.findViewById(R.id.element_view2_image);
        }
    }

    @NonNull
    @Override
    public MyProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_view,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProductsAdapter.ViewHolder holder, int position) {
        try {
            holder.setIsRecyclable(false);
            JSONObject element = elements.getJSONObject(position);
            String name = element.getString("name");
            final String id = element.getString("id");
            holder.first_line.setText(name);
            Picasso.get().load(element.getString("url")).into(holder.iamgen);
            holder.container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, One_Product.class);
                    intent.putExtra("product_id",id);
                    context.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(elements!=null)
            return elements.length();
        else
            return 0;
    }
}