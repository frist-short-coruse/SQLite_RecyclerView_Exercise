package com.example.vechet.sqlite_recyclerview_exercise.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vechet.sqlite_recyclerview_exercise.R;
import com.example.vechet.sqlite_recyclerview_exercise.callback.OnItemClickListener;
import com.example.vechet.sqlite_recyclerview_exercise.data.local.room.dao.NewsDao;
import com.example.vechet.sqlite_recyclerview_exercise.data.local.room.db.NewsDatabase;
import com.example.vechet.sqlite_recyclerview_exercise.entity.News;
import com.example.vechet.sqlite_recyclerview_exercise.util.ToastMessase;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private List<News> newsList;
    private Context context;
    public OnItemClickListener listener;
    NewsDao newsDao;
    public static final String TAG = "NewsAdapter";

    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.newsList = news;
//        listener = (OnItemClickListener) context;
        newsDao = NewsDatabase.getDatabase(context).getNewsDao();
    }

    public void setClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public void addNews(News news){
        this.newsList.add(0,news);
        notifyDataSetChanged();
    }

    public NewsAdapter(List<News> news) {
        this.newsList = news;
    }

    public void setNews(List<News> news) {
        this.newsList.addAll(news);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cardview_listiem, parent, false
        );
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.tvTitle.setText(news.title);
        holder.tvSubTitle.setText(news.subTitle);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);
        Glide.with(context).load(news.imageThump)
                .apply(options)
                .into(holder.ivThump);
        holder.ivMore.setOnClickListener(v -> {
            //ToastMessase.showMessage(context,"Cilcked");
            PopupMenu menu = new PopupMenu(context,v);
            menu.inflate(R.menu.popup_menu);
            menu.show();
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.menuRemove:
                        this.newsList.remove(position);
                        notifyDataSetChanged();
                        listener.OnDeleteNews(position);
                    case R.id.menuEdit:

                        break;
                }

                return false;
            });
        });
    }

    @Override
    public int getItemCount() {
        return this.newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvSubTitle;
        public ImageView ivThump, ivMore;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivMore = itemView.findViewById(R.id.ivMore);
            ivThump = itemView.findViewById(R.id.ivThump);
        }
    }

}
