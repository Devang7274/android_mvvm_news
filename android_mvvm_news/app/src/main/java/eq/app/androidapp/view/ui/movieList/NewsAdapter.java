package eq.app.androidapp.view.ui.movieList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import eq.app.androidapp.R;
import eq.app.androidapp.view.model.NewsArticle;

import static eq.app.androidapp.view.util.CustomLessMoreTextView.makeTextViewResizable;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private ArrayList<NewsArticle> articles;

    NewsAdapter(Context context, ArrayList<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_row, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.NewsViewHolder holder, int position) {
        holder.tvName.setText(articles.get(position).getTitle());
        holder.tvDesCription.setText(articles.get(position).getDescription());
        holder.tvSource.setText("Source : " + articles.get(position).getSource().getName());
        makeTextViewResizable( holder.tvDesCription, 1, "See More", true);
        Picasso.get().load(articles.get(position).getUrlToImage()).error(R.drawable.placeholder).into(holder.ivNews,  new Callback() {
            @Override
            public void onSuccess() {
                if (holder.progressbar != null) {
                    holder.progressbar .setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Exception e) {
                holder.progressbar .setVisibility(View.GONE);
                holder.ivNews.setImageResource(R.drawable.placeholder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDesCription;
        TextView tvSource;
        ImageView ivNews;
        ProgressBar progressbar;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDesCription = itemView.findViewById(R.id.tvDescription);
            ivNews = itemView.findViewById(R.id.ivNews);
            tvSource = itemView.findViewById(R.id.tvSource);
            progressbar = itemView.findViewById(R.id.progressbar);


        }
    }


   }