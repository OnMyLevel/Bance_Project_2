package com.example.christanismerilbanzouzi.bance_project.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.christanismerilbanzouzi.bance_project.Interface.ItemClickListener;
import com.example.christanismerilbanzouzi.bance_project.R;

/**
 * Created by christanismerilbanzouzi on 18/03/2018.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textArticleView;
    public ImageView imageView;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ArticleViewHolder(View itemView) {
        super(itemView);
        textArticleView= (TextView) itemView.findViewById(R.id.recycler_article_name);
        imageView=(ImageView) imageView.findViewById(R.id.recycler_article_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
