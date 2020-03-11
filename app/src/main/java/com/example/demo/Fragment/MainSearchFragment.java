package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.adapter.BaseAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.Product;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainSearchFragment extends BaseFragment {
    private SearchView product_search_view;
    private RecyclerView product_list;
    private TextView tvTitle,btHeaderRight;
    private String url="http://129.211.75.130:8080/demo/products/findByDescription?description=";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_search, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tvTitle.setText("搜索");
        btHeaderRight.setVisibility(View.GONE);
        product_search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MoreFragment moreFragment = new MoreFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,moreFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String url1=url+newText;
                loadData(url1,0);
                return true;
            }
        });

        product_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        product_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void initView(View view) {
        product_search_view=view.findViewById(R.id.product_search_view);
        product_list=view.findViewById(R.id.product_list);
        tvTitle=getActivity().findViewById(R.id.tv_title);
        btHeaderRight=getActivity().findViewById(R.id.bt_header_right);
    }

    @Override
    public void onSuccess(String result) {
        Product product = new Gson().fromJson(result, Product.class);
        product_list.setAdapter(new BaseAdapter(getActivity(), product.getProductInfoList(), new BaseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                ProductInfoFragment productInfoFragment = new ProductInfoFragment();
                Bundle bundle = new Bundle();
                String pid = String.valueOf(product.getProductInfoList().get(position).getProduct().getPid());
                bundle.putString("pid", pid);
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
