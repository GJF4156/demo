package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;
import com.example.demo.beans.SortCard;

import java.util.List;

public class SortMenuAdapter extends RecyclerView.Adapter<SortMenuAdapter.ViewHolder> {
    private Context mContext;
    private List<SortCard> mList;
    private OnItemClickListener mListenter;

    public SortMenuAdapter(Context context, List<SortCard> list, OnItemClickListener listenter) {
        this.mContext = context;
        this.mList = list;
        this.mListenter = listenter;
    }

    @NonNull
    @Override
    public SortMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SortMenuAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.sort_card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sortIcon.setText(mList.get(position).getSortIcon());
        holder.sortName.setText(mList.get(position).getSortName());
        holder.sortDescrip.setText(mList.get(position).getSortDescrip());
        holder.content1.setText(mList.get(position).getContent1());
        holder.content2.setText(mList.get(position).getContent2());
        holder.content3.setText(mList.get(position).getContent3());
            if (mList.get(position).getSortName().equals("可回收垃圾")) {
                holder.sortIcon.setTextColor(mContext.getColor(R.color.recyclableFontColor));
                holder.sortName.setTextColor(mContext.getColor(R.color.recyclableFontColor));
                holder.yaoqiu.setTextColor(mContext.getColor(R.color.recyclableFontColor));
            } else if (mList.get(position).getSortName().equals("厨余垃圾")) {
                holder.sortIcon.setTextColor(mContext.getColor(R.color.kitchenFontColor));
                holder.sortName.setTextColor(mContext.getColor(R.color.kitchenFontColor));
                holder.yaoqiu.setTextColor(mContext.getColor(R.color.kitchenFontColor));
            } else if (mList.get(position).getSortName().equals("其他垃圾")) {
                holder.sortIcon.setTextColor(mContext.getColor(R.color.otherFontColor));
                holder.sortName.setTextColor(mContext.getColor(R.color.otherFontColor));
                holder.yaoqiu.setTextColor(mContext.getColor(R.color.otherFontColor));
            } else if (mList.get(position).getSortName().equals("有害垃圾")) {
                holder.sortIcon.setTextColor(mContext.getColor(R.color.hazardousFontColor));
                holder.sortName.setTextColor(mContext.getColor(R.color.hazardousFontColor));
                holder.yaoqiu.setTextColor(mContext.getColor(R.color.hazardousFontColor));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenter.onClick(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FontIconView sortIcon;
        private TextView sortName, sortDescrip, yaoqiu, content1, content2, content3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sortIcon = itemView.findViewById(R.id.sort_icon);
            sortName = itemView.findViewById(R.id.sort_name);
            sortDescrip = itemView.findViewById(R.id.sort_descrip);
            yaoqiu = itemView.findViewById(R.id.yaoqiu);
            content1 = itemView.findViewById(R.id.content1);
            content2 = itemView.findViewById(R.id.content2);
            content3 = itemView.findViewById(R.id.content3);
        }
    }


    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

}
