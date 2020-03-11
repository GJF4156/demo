package com.example.demo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.Fragment.Presenter.IFragmentMoreP;
import com.example.demo.Fragment.Presenter.impl.FragmentMorePImpl;
import com.example.demo.Fragment.View.IFragmentMoreV;
import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.adapter.MoreProductAdapter;
import com.example.demo.beans.Product;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements IFragmentMoreV {
    private TextView complex, sold, news,tvTitle,btHeaderRight;
    private RecyclerView moreRv;
    private ProductInfoFragment productInfoFragment;
    //因为和ShopFragment需要的数据一样，所以这里用ShopFragment的MVP层
    private IFragmentMoreP mPresenter;
    private String url = "http://129.211.75.130:8080/demo/products/findAll";
    private int fla=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        mPresenter = new FragmentMorePImpl(this);
        mPresenter.getData(url);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moreRv = view.findViewById(R.id.more_Rv);
        complex = view.findViewById(R.id.complex);
        sold = view.findViewById(R.id.sold);
        news = view.findViewById(R.id.news);
        tvTitle=getActivity().findViewById(R.id.tv_title);
        btHeaderRight=getActivity().findViewById(R.id.bt_header_right);

        tvTitle.setText("商城");
        btHeaderRight.setVisibility(view.GONE);
        complex.setOnClickListener(v -> {
            url = "http://129.211.75.130:8080/demo/products/findAll";
            mPresenter.getData(url);
            complex.setBackground(getActivity().getDrawable(R.drawable.border));
            fla=1;
        });
        sold.setOnClickListener(v -> {
            url = "http://129.211.75.130:8080/demo/products/findOrderBySold ";
            mPresenter.getData(url);
            sold.setBackground(getActivity().getDrawable(R.drawable.border));
            fla=2;
        });
        news.setOnClickListener(v -> {
            url = "http://129.211.75.130:8080/demo/products/findAll";
            mPresenter.getData(url);
            news.setBackground(getActivity().getDrawable(R.drawable.border));
            fla=3;
        });
        moreRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        moreRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void getData(List<Product.ProductInfoListBean> productInfoListBeanList) {
        //实现选项卡功能，点击时，将选中的textview加边框，未选中的不加
        switch (fla){
            case 1:
                complex.setBackground(getActivity().getDrawable(R.drawable.border));
                sold.setBackground(null);
                news.setBackground(null);
                break;
            case 2:
                complex.setBackground(null);
                sold.setBackground(getActivity().getDrawable(R.drawable.border));
                news.setBackground(null);
                break;
            case 3:
                complex.setBackground(null);
                sold.setBackground(null);
                news.setBackground(getActivity().getDrawable(R.drawable.border));
                break;
        }

        moreRv.setAdapter(new MoreProductAdapter(getActivity(), productInfoListBeanList, new MoreProductAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                productInfoFragment = new ProductInfoFragment();
                Bundle bundle = new Bundle();
                String pid = String.valueOf(productInfoListBeanList.get(position).getProduct().getPid());
                bundle.putString("pid", pid);
                productInfoFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, productInfoFragment)
                        .addToBackStack(null)//加入回退栈，按back键回到上一个fragment
                        .commitAllowingStateLoss();
            }
        }));
    }
}
