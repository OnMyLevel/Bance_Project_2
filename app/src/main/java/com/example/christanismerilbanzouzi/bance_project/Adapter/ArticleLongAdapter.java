package com.example.christanismerilbanzouzi.bance_project.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.christanismerilbanzouzi.bance_project.Model.ArticleLong;
import com.example.christanismerilbanzouzi.bance_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by christanismerilbanzouzi on 19/03/2018.
 */

public class ArticleLongAdapter extends RecyclerView.Adapter<ArticleLongAdapter.ArticleLongViewHolder> {
    private Context context;
    private ArrayList<ArticleLong> mlist;
    private OnItemClickListener mListener;
    //Interface on itemclick view
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ArticleLongAdapter(Context excontext,ArrayList<ArticleLong> exmlist){
        context=excontext;
        mlist=exmlist;
    }

    @Override
    public ArticleLongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View V= LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new ArticleLongViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ArticleLongViewHolder holder, int position) {

        ArticleLong currenItem= mlist.get(position);
        String imageUrl = currenItem.getImage();
        String name = currenItem.getName();
        String price=currenItem.getPrice();
        holder.textView.setText(name);
        holder.textViewT.setText("Price:"+price+"$");
        Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class ArticleLongViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public TextView textViewT;

        public ArticleLongViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.articleImage);
            textView = itemView.findViewById(R.id.articleName);
            textViewT=itemView.findViewById(R.id.articlePrice);

            // on rajoute notre onclickListener pour avoir les détail de l'article
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
