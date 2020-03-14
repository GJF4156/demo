package com.example.demo.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.demo.R;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.base.UniteApp;
import com.example.demo.beans.JsonBean;
import com.example.demo.beans.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInfoFragment extends Fragment implements View.OnClickListener {
    private TextView tvTitle, btHeaderRight, tv_ID, tv_sex, tv_birthdate, tv_address;
    private EditText tv_nick;
    private LinearLayout personal_set_name,
            personal_set_sex, personal_set_birth,
            personal_set_address;
    private Button personal_set_btn;

    private TimePickerView pvTime;
    private OptionsPickerView pvGender, pvCity;
    private ArrayList<String> sexItems = new ArrayList<>();
    //  省份
    ArrayList<String> provinceBeanList = new ArrayList<>();
    //  城市
    ArrayList<String> cities;
    ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    ArrayList<String> district;
    ArrayList<List<String>> districts;
    ArrayList<List<List<String>>> districtList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        initView(view);
        initData();
        initTimePicker();
        initJsonData();
        return view;
    }

    private void initData() {
        btHeaderRight.setVisibility(View.GONE);
        tvTitle.setText("个人资料");
        tv_ID.setText(SPUtil.getUserId());
        if (!SPUtil.getNickName().isEmpty()) {
            tv_nick.setText(SPUtil.getNickName());
        }else{
            tv_nick.setText("请完善你的信息");
        }
        if (!SPUtil.getSex().isEmpty()){
            tv_sex.setText(SPUtil.getSex());
        }else{
            tv_sex.setText("请完善你的信息");
        }
        if (!SPUtil.getBrith().isEmpty()){
            tv_birthdate.setText(SPUtil.getBrith());
        }else {
            tv_birthdate.setText("请完善你的信息");
        }
        if (!SPUtil.getLocation().isEmpty()){
            tv_address.setText(SPUtil.getLocation());
        }else {
            tv_address.setText("请完善你的信息");
        }
        personal_set_name.setOnClickListener(this);
        personal_set_sex.setOnClickListener(this);
        personal_set_birth.setOnClickListener(this);
//        personal_set_safe.setOnClickListener(this);
        personal_set_address.setOnClickListener(this);
        personal_set_btn.setOnClickListener(this);
    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        tv_ID = view.findViewById(R.id.tv_ID);
        tv_sex = view.findViewById(R.id.tv_sex);
        tv_birthdate = view.findViewById(R.id.tv_birthdate);
        tv_address = view.findViewById(R.id.tv_address);
        tv_nick = view.findViewById(R.id.tv_person_name);
        personal_set_name = view.findViewById(R.id.personal_set_name);
        personal_set_sex = view.findViewById(R.id.personal_set_sex);
        personal_set_birth = view.findViewById(R.id.p_setbirth_layout);
        personal_set_address = view.findViewById(R.id.personal_set_address);
//        personal_set_safe = view.findViewById(R.id.personal_set_safe);
        personal_set_btn = view.findViewById(R.id.personal_set_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_set_sex:
                showSexPickView();
//                Toast.makeText(getActivity(), "personal_set_sex", Toast.LENGTH_SHORT).show();
                break;
            case R.id.p_setbirth_layout:
//                Toast.makeText(getActivity(), "p_setbirth_layout", Toast.LENGTH_SHORT).show();
                if (pvTime != null) {
                    pvTime.show(v);
                }
                break;
//            case R.id.personal_set_safe:
//                Toast.makeText(getActivity(), "personal_set_safe", Toast.LENGTH_SHORT).show();
////                goSaftSetting();
//                break;
            case R.id.personal_set_address:
//                Toast.makeText(getActivity(), "personal_set_address", Toast.LENGTH_SHORT).show();
                showCityPickView();
                break;
            case R.id.personal_set_btn:
                String id = tv_ID.getText().toString();
                String nickName = tv_nick.getText().toString();
                String sex = tv_sex.getText().toString();
                String birthdate = tv_birthdate.getText().toString();
                String address = tv_address.getText().toString();
                if (!id.isEmpty() & !nickName.isEmpty() & !sex.isEmpty() & !birthdate.isEmpty() & !address.isEmpty()) {
                    //将数据处理成json格式
                    User user=new User();
                    user.setUsernickName(nickName);
                    user.setUsersex(sex);
                    user.setUserbrith(birthdate);
                    user.setUserlocation(address);
                    String s = new Gson().toJson(user);
                    //将数据更新到服务器中
                    String url="http://129.211.75.130:8080/demo/user/update";
                    Map<String,Object> map=new HashMap<>();
                    map.put("user",s);
                    map.put("token",SPUtil.getToken());
                    XutilsHttp.getInstance().post(url, map, new XutilsHttp.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            JsonBean jsonBean=new Gson().fromJson(result,JsonBean.class);
                            int code = jsonBean.getCode();
                            String msg = jsonBean.getMsg();
                            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                            if (code==100){
                                SharedPreferences.Editor editor = UniteApp.getContext()
                                        .getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                                editor.putString("brith",user.getUserbrith());
                                editor.putString("location",user.getUserlocation());
                                editor.putString("nickName",user.getUsernickName());
                                editor.putString("sex",user.getUsersex());
                                editor.apply();
                            }
                        }

                        @Override
                        public void onFail(String result) {

                        }

                        @Override
                        public void onCancel(Callback.CancelledException cex) {

                        }
                    });
                    //Toast.makeText(getActivity(), id + "   " + nickName + "   " + sex + "   " + birthdate + "   " + address, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //性别选择器
    private void showSexPickView() {
        sexItems.add("男");
        sexItems.add("女");
        pvGender = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_sex.setText(sexItems.get(options1));
            }
        })
                .setBgColor(Color.parseColor("#F6F7F6"))
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setCancelText("取消")
                .setSubmitText("确认")
                .setOutSideCancelable(false)// default is true
                .build();
        pvGender.setPicker(sexItems);
        pvGender.show();
    }

    //日期选择器
    private void initTimePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(1949, 0, 1);
        endDate.set(2020, 3, 31);
        pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                tv_birthdate.setText(calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.DAY_OF_MONTH));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(20)
                .isCyclic(true)
                .setOutSideCancelable(true)
                .setBgColor(Color.parseColor("#F6F7F6"))
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .isCenterLabel(false)
                .build();
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
                tv_address.setText(address);
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
