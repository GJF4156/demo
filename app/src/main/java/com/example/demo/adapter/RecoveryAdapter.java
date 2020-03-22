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
import com.example.demo.beans.Reservation;
import com.example.demo.beans.SortsBean;
import com.iflytek.thirdparty.r;

import java.util.List;

public class RecoveryAdapter extends RecyclerView.Adapter<RecoveryAdapter.ViewHolder> {
    private Context mContext;
    private List<Reservation> mDataBeanList;//保存数据源的列表
    private RecoveryAdapter.OnItemClickListener mListener;//初始化监听接口

    public RecoveryAdapter(Context context, List<Reservation> dataBeanList, RecoveryAdapter.OnItemClickListener listener) {
        this.mContext = context;
        this.mDataBeanList = dataBeanList;
        this.mListener = listener;
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecoveryAdapter.ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.recovery_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.personal.setText(mDataBeanList.get(position).getPerson());
        holder.go_time.setText(mDataBeanList.get(position).getGotime());
        holder.personal_phone.setText(mDataBeanList.get(position).getTelephone());
        holder.personal_address.setText(mDataBeanList.get(position).getAddress());
        holder.sort.setText(mDataBeanList.get(position).getSort());
            switch (mDataBeanList.get(position).getStatus()) {
                case "待接单":
                    holder.status_icon_font.setText(R.string.pending);
                    holder.status_icon_font.setTextColor(mContext.getColor(R.color.colorAccent));
                    break;
                case "已处理":
                    holder.status_icon_font.setText(R.string.processed);
                    holder.status_icon_font.setTextColor(mContext.getColor(R.color.colorIconAndTextSelect));
                    break;
                case "已完成":
                    holder.status_icon_font.setText(R.string.completed);
                    holder.status_icon_font.setTextColor(mContext.getColor(R.color.colorPrimaryDark));
                    break;
            }
    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView personal,go_time,personal_phone,personal_address,sort;
        FontIconView status_icon_font;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personal=itemView.findViewById(R.id.personal);
            go_time=itemView.findViewById(R.id.go_time);
            personal_phone=itemView.findViewById(R.id.personal_phone);
            personal_address=itemView.findViewById(R.id.personal_address);
            sort=itemView.findViewById(R.id.sort);
            status_icon_font=itemView.findViewById(R.id.status_icon_font);
        }
    }
}
