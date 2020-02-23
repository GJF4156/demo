package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.beans.SortsBean;

import java.util.List;

public class SearchRvAdapter extends RecyclerView.Adapter<SearchRvAdapter.ViewHolder> {

    private Context mContext;
    private List<SortsBean.DatalistBean> mDataBeanList;//保存数据源的列表
    private OnItemClickListener mListener;//初始化监听接口

    public SearchRvAdapter(Context context, List<SortsBean.DatalistBean> dataBeanList, OnItemClickListener listener) {
        this.mContext = context;
        this.mDataBeanList = dataBeanList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SearchRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchRvAdapter.ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.speach_search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRvAdapter.ViewHolder holder, final int position) {
            holder.name.setText(mDataBeanList.get(position).getGname());
            holder.sortDescription.setText(mDataBeanList.get(position).getTip());
            switch (mDataBeanList.get(position).getGtype()) {
                case 0:
                    //可回收垃圾
                    holder.sortName.setText("可回收垃圾");
                    holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.recyclableFontColor));
                    break;
                case 1:
                    //有害垃圾
                    holder.sortName.setText("有害垃圾");
                    holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.hazardousFontColor));
                    break;
                case 2:
                    //厨余垃圾
                    holder.sortName.setText("厨余垃圾");
                    holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.kitchenFontColor));
                    break;
                case 3:
                    //其他垃圾
                    holder.sortName.setText("其他垃圾");
                    holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.otherFontColor));
                    break;
                case 4:
                    //其他垃圾
                    holder.sortName.setText("未搜索到");
                    holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.notincludedFontColor));
                    break;
                default:
                    break;
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, sortName, sortDescription;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            sortName = itemView.findViewById(R.id.sort_name);
            sortDescription = itemView.findViewById(R.id.sort_description);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
