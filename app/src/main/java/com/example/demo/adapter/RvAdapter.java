package com.example.demo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;
import com.example.demo.beans.SortsBean;

import java.util.List;

/**
 * recyclerview适配器
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private Context mContext;
    private List<SortsBean.DatalistBean> mDataBeanList;//保存数据源的列表
    private OnItemClickListener mListener;//初始化监听接口

    /**
     * 构造函数
     *
     * @param context
     * @param dataBeanList
     * @param listener
     */
    public RvAdapter(Context context, List<SortsBean.DatalistBean> dataBeanList, OnItemClickListener listener) {
        this.mContext = context;
        this.mDataBeanList = dataBeanList;
        this.mListener = listener;
    }

    /**
     * 加载recyclerview子项布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_liner_item, parent, false));
    }

    /**
     * 初始化子项布局的组件
     *
     * @param holder
     * @param position
     */
    @SuppressLint({ "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, final int position) {
            holder.rubbish_name_tv.setText(mDataBeanList.get(position).getGname() + ":");
            switch (mDataBeanList.get(position).getGtype()) {
                case 4:
                    holder.icont_font_tv.setText(R.string.notincludedFont);
                    holder.sort_name_tv.setText("输入错误或暂未收录！");
                    holder.icont_font_tv.setTextColor(mContext.getColor(R.color.notincludedFontColor));
                    holder.sort_name_tv.setTextColor(mContext.getColor(R.color.notincludedFontColor));
                    break;
                case 0:
                    //可回收垃圾
                    holder.icont_font_tv.setText(R.string.recyclableFont);
                    holder.sort_name_tv.setText("可回收垃圾");
                    holder.icont_font_tv.setTextColor(mContext.getColor(R.color.recyclableFontColor));
                    holder.sort_name_tv.setTextColor(mContext.getColor(R.color.recyclableFontColor));
                    break;
                case 1:
                    //有害垃圾
                    holder.icont_font_tv.setText(R.string.hazardousFont);
                    holder.sort_name_tv.setText("有害垃圾");
                    holder.icont_font_tv.setTextColor(mContext.getColor(R.color.hazardousFontColor));
                    holder.sort_name_tv.setTextColor(mContext.getColor(R.color.hazardousFontColor));
                    break;
                case 2:
                    //厨余垃圾
                    holder.icont_font_tv.setText(R.string.kitchenFont);
                    holder.sort_name_tv.setText("厨余垃圾");
                    holder.icont_font_tv.setTextColor(mContext.getColor(R.color.kitchenFontColor));
                    holder.sort_name_tv.setTextColor(mContext.getColor(R.color.kitchenFontColor));
                    break;
                case 3:
                    //其他垃圾
                    holder.icont_font_tv.setText(R.string.otherFont);
                    holder.sort_name_tv.setText("其他垃圾");
                    holder.icont_font_tv.setTextColor(mContext.getColor(R.color.otherFontColor));
                    holder.sort_name_tv.setTextColor(mContext.getColor(R.color.otherFontColor));
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
        private FontIconView icont_font_tv;
        private TextView rubbish_name_tv;
        private TextView sort_name_tv;

        //初始化组件
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icont_font_tv = itemView.findViewById(R.id.icont_font_tv);
            rubbish_name_tv = itemView.findViewById(R.id.rubbish_name_tv);
            sort_name_tv = itemView.findViewById(R.id.sort_name_tv);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
