package com.example.demo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.base.UniteApp;
import com.example.demo.beans.AddressBean;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<AddressBean.InfoBean.AddressesBean> mDataBeanList;//保存数据源的列表
    private int lastSelectedPosition = -1;

    public AddressAdapter(Context context, List<AddressBean.InfoBean.AddressesBean> dataBeanList) {
        this.mContext = context;
        this.mDataBeanList = dataBeanList;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddressAdapter.ViewHolder(mInflater.inflate(R.layout.address_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.receiver.setText(mDataBeanList.get(position).getReceiver());
        holder.phone_num.setText(mDataBeanList.get(position).getPhone());
        holder.address_info.setText(mDataBeanList.get(position).getAddress());
        holder.ck_chose.setChecked(lastSelectedPosition == position);
        for (int i = 0; i < mDataBeanList.size(); i++) {
            if (holder.ck_chose.isChecked()) {
                SharedPreferences.Editor editor = UniteApp.getContext()
                        .getSharedPreferences("address", Context.MODE_PRIVATE).edit();
                editor.putString("aid", String.valueOf(mDataBeanList.get(position).getAid()));
                editor.putString("receiver", mDataBeanList.get(position).getReceiver());
                editor.putString("phone", mDataBeanList.get(position).getPhone());
                editor.putString("address", mDataBeanList.get(position).getAddress());
                editor.apply();
            }
        }
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSlideListener.onDel(mDataBeanList.get(position).getAid());
            }
        });


    }

    @Override
    public int getItemCount() {
        return null != mDataBeanList ? mDataBeanList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView receiver, phone_num, address_info;
        private RadioButton ck_chose;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiver = itemView.findViewById(R.id.receiver);
            phone_num = itemView.findViewById(R.id.phone_num);
            address_info = itemView.findViewById(R.id.address_info);
            ck_chose = itemView.findViewById(R.id.ck_chose);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            ck_chose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSlideListener {
        void onDel(int position);
    }

    private AddressAdapter.onSlideListener mOnSlideListener;

    public AddressAdapter.onSlideListener getmOnSlideListener() {
        return mOnSlideListener;
    }

    public void setOnDelListener(AddressAdapter.onSlideListener mOnDelListener) {
        this.mOnSlideListener = mOnDelListener;
    }
}
