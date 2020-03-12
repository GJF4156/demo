package com.example.demo.Fragment;


import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.demo.R;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.beans.Address;
import com.example.demo.beans.JsonBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment implements View.OnClickListener {
    private OptionsPickerView pvCity;
    //  省份
    ArrayList<String> provinceBeanList = new ArrayList<>();
    //  城市
    ArrayList<String> cities;
    ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    ArrayList<String> district;
    ArrayList<List<String>> districts;
    ArrayList<List<List<String>>> districtList = new ArrayList<>();
    private TextView tv_city1, tvTitle, btHeaderRight, btBack;
    private LinearLayout address;
    private Button btn_save;
    private DbManager db;
    private EditText et_shouhuoren,et_Mobile,et_xiangxidizhi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        //初始化本地数据库
        initDb();
        initView(view);
        initJsonData();
        initData();
        return view;
    }

    private void initDb() {
        //本地数据的初始化
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("shoppingCar") //设置数据库名
                .setDbVersion(1) //设置数据库版本
                .setDbOpenListener(db -> {
                    db.getDatabase().enableWriteAheadLogging();
                    //开启WAL, 对写入加速提升巨大(作者原话)
                })
                .setDbUpgradeListener((db, oldVersion, newVersion) -> {
                    //数据库升级操作
                });
        db = x.getDb(daoConfig);
    }

    private void initData() {
        tvTitle.setText("收货地址");
        btHeaderRight.setVisibility(View.GONE);
    }

    private void initView(View view) {
        tv_city1 = view.findViewById(R.id.tv_city1);
        address = view.findViewById(R.id.address);
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        btBack = getActivity().findViewById(R.id.btn_back);
        btn_save = view.findViewById(R.id.btn_save);
        et_shouhuoren=view.findViewById(R.id.et_shouhuoren);
        et_Mobile=view.findViewById(R.id.et_Mobile);
        et_xiangxidizhi=view.findViewById(R.id.et_xiangxidizhi);
        btn_save.setOnClickListener(this);
        btBack.setOnClickListener(this);
        address.setOnClickListener(this);
        tv_city1.setOnClickListener(this);
    }

    //地址选择器
    private void showCityPickView() {
        pvCity = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String city = provinceBeanList.get(options1);
                String address; //  如果是直辖市或者特别行政区只设置市和区/县
                if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city) || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                    address = provinceBeanList.get(options1) + "-" + districtList.get(options1).get(options2).get(options3);
                } else {
                    address = provinceBeanList.get(options1) + "-" + cityList.get(options1).get(options2) + "-" + districtList.get(options1).get(options2).get(options3);
                }
                tv_city1.setText(address);
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {

            }
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setBgColor(Color.parseColor("#F6F7F6"))//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();
        pvCity.setPicker(provinceBeanList, cityList, districtList);
        pvCity.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city1:
                showCityPickView();
                break;
            case R.id.btn_save:
                saveData();
                //将数据提交到服务器

                //讲数据保存到数据库

                break;
        }


    }

    private void saveData() {
        String shouhuoren = et_shouhuoren.getText().toString();
        String Mobile = et_Mobile.getText().toString();
        String xiangxidizhi = et_xiangxidizhi.getText().toString();
        String city = tv_city1.getText().toString();
        Address address=new Address();
        address.setAid(0);
        address.setReceiver(shouhuoren);
        address.setPhone(Mobile);
        address.setAddress(city+"-"+xiangxidizhi);
        String url="http://129.211.75.130:8080/demo/address/insert";
        Map<String,Object> map=new HashMap<>();
        map.put("receiver",shouhuoren);
        map.put("phone",Mobile);
        map.put("address",city+"-"+xiangxidizhi);
        map.put("token", SPUtil.getToken());
        XutilsHttp.getInstance().get(url, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                Gson gson=new Gson();
                JsonBean jsonBean=gson.fromJson(result,JsonBean.class);
                String msg = jsonBean.getMsg();
                int code = jsonBean.getCode();
                if (code==100){
                    try {
                        db.save(address);
                        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String result) {

            }

            @Override
            public void onCancel(Callback.CancelledException cex) {

            }
        });

    }


    private void initJsonData() {
        //解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = getJson("province.json");//获取assets目录下的json文件数据
        parseJson(JsonData);
    }

    /**
     * 从asset目录下读取fileName文件内容
     *
     * @param fileName 待读取asset下的文件名
     * @return 得到省市县的String
     */
    private String getJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = getActivity().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 解析json填充集合
     *
     * @param str 待解析的json，获取省市县
     */
    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
//                provinceBeanList.add(new ProvinceBean(provinceName));
                provinceBeanList.add(provinceName);
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();
                //   声明存放城市的集合
                districts = new ArrayList<>();
                //声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();
                    // 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
