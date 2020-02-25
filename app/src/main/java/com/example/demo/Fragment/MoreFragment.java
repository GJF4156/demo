package com.example.demo.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demo.Fragment.Presenter.IFragmentShopP;
import com.example.demo.Fragment.Presenter.impl.FragmentShopPImpl;
import com.example.demo.Fragment.View.IFragmentShopV;
import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.activity.ContentActivity;
import com.example.demo.adapter.MoreProductAdapter;
import com.example.demo.beans.Product;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements IFragmentShopV {
    private RecyclerView moreRv;
    private ProductInfoFragment productInfoFragment;

    //因为和ShopFragment需要的数据一样，所以这里用ShopFragment的MVP层
    private IFragmentShopP mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        mPresenter=new FragmentShopPImpl(this);
        mPresenter.getData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moreRv=view.findViewById(R.id.more_Rv);
        moreRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        moreRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void getData(List<Product.ProductInfoListBean> productInfoListBeanList) {
        moreRv.setAdapter(new MoreProductAdapter(getActivity(), productInfoListBeanList, new MoreProductAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                productInfoFragment=new ProductInfoFragment();
                Bundle bundle = new Bundle();
                String pid= String.valueOf(productInfoListBeanList.get(position).getProduct().getPid());
                bundle.putString("pid",pid);
                productInfoFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,productInfoFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        }));
    }
}
