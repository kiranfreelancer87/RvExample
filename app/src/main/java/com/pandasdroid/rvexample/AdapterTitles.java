package com.pandasdroid.rvexample;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pandasdroid.rvexample.databinding.View1Binding;

import java.util.ArrayList;

public class AdapterTitles extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<MainModel> list = new ArrayList<>();

    public AdapterTitles(ArrayList<MainModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderTitles(View1Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderTitles h = (ViewHolderTitles) holder;
        MainModel item = list.get(position);
        h.binding.tvTitle.setText(item.title);
        AdapterSubTitles adapterSubTitles = new AdapterSubTitles(item.list);
        h.binding.rvSubtitles.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.VERTICAL, false));
        h.binding.rvSubtitles.setAdapter(adapterSubTitles);
        h.binding.rvSubtitles.setHasFixedSize(true);
        h.binding.rvSubtitles.setItemViewCacheSize(item.list.size() + 1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolderTitles extends RecyclerView.ViewHolder {

        private View1Binding binding;

        public ViewHolderTitles(@NonNull View1Binding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
