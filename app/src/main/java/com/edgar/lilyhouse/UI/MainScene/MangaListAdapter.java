package com.edgar.lilyhouse.UI.MainScene;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.lilyhouse.Data.MainSceneData.MangaItem;
import com.edgar.lilyhouse.R;
import com.edgar.lilyhouse.Utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

public class MangaListAdapter extends RecyclerView.Adapter<MangaListAdapter.MainViewHolder> {

    private static final String TAG = "===============" + MangaListAdapter.class.getSimpleName();
    private List<MangaItem> listItems;
    private Context context;

    public MangaListAdapter(Context context) {
        this.context = context;
        this.listItems = new ArrayList<>();
    }

    public void setListItems(List<MangaItem> listItems) {
        this.listItems = new ArrayList<>(listItems);
        notifyDataSetChanged();
    }

    public void addListItems(List<MangaItem> mangaItems) {
        int insertPosition = listItems.size();
        this.listItems.addAll(mangaItems);
        notifyItemInserted(insertPosition);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_main_list, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        if (listItems == null || listItems.size() == 0) {
            return;
        }

        holder.tvTitle.setText(listItems.get(position).getName());
        holder.tvAuthor.setText(listItems.get(position).getAuthors());

        String urlString = listItems.get(position).getCover();
        if (!urlString.startsWith("https")) {
            urlString = "https://images.dmzj.com/" + urlString;
        }
        GlideUtil.setImageView(holder.ivCoverImage, urlString);

//        Log.d(TAG, "onBindViewHolder: " + listItems.get(position).getName() + ": " +
//                listItems.get(position).getAdd_time());

    }

    @Override
    public long getItemId(int position) {
        return listItems.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return (listItems == null ? 0 : listItems.size());
    }

    protected class MainViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ivCoverImage;
        protected TextView tvTitle;
        protected TextView tvAuthor;

        protected MainViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCoverImage = itemView.findViewById(R.id.iv_cover_image);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvAuthor = itemView.findViewById(R.id.tv_item_author);


        }
    }

    public int getCurPage() {
        if (listItems == null || listItems.size() == 0) {
            return 0;
        }
        return listItems.get(listItems.size() - 1).getLocPage();
    }
}
