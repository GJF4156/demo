package com.example.demo.Fragment;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.Fragment.Presenter.IFragmentHomeP;
import com.example.demo.Fragment.Presenter.impl.FragmentHomePImpl;
import com.example.demo.Fragment.View.IFragmentHomeV;
import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.Utils.ImageUtils;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.activity.SpeachActivity;
import com.example.demo.activity.WebContentActivity;
import com.example.demo.adapter.NewsAdapter;
import com.example.demo.adapter.RecyclerSlideAdapter;
import com.example.demo.adapter.SearchRvAdapter;
import com.example.demo.adapter.SearchRvAdapter2;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.JsonBean;
import com.example.demo.beans.NewsBeans;
import com.example.demo.beans.NewsData;
import com.example.demo.beans.Result;
import com.example.demo.beans.SortsBean;
import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.db.Selector;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends BaseFragment implements View.OnClickListener, IFragmentHomeV {
    private Intent intent;
    private FontIconView center_ar_icon;
    private FontIconView center_voice_icon;
    private FontIconView center_recovery_icon;
    private RecyclerView NewsRv;
    private ImageView imageView;
    private RecyclerSlideAdapter adapter;
    private DbManager db;
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int PICK_PHOTO = 2;
    File outputImage;
    private IFragmentHomeP mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //初始化本地数据库
        initDb();
        mPresenter = new FragmentHomePImpl(this);
        mPresenter.getData(getActivity());
        //初始化数据
        init(view);
        return view;
    }

    //本地数据的初始化
    private void initDb() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("favorite") //设置数据库名
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

    private void init(View view) {
        imageView = view.findViewById(R.id.main_image);
        center_ar_icon = view.findViewById(R.id.center_ar_icon);
        center_voice_icon = view.findViewById(R.id.center_voice_icon);
        center_recovery_icon = view.findViewById(R.id.center_recovery_icon);
        NewsRv = view.findViewById(R.id.news_rv);
        //设置recyclerview的布局管理器
        NewsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        NewsRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        center_ar_icon.setOnClickListener(this);
        center_voice_icon.setOnClickListener(this);
        center_recovery_icon.setOnClickListener(this);

        Drawable drawable = getActivity().getDrawable(R.mipmap.main);
        //将Drawable转化为Bitmap
        Bitmap bitmap = ImageUtils.drawableToBitmap(drawable);
        //获取圆角图片
        Bitmap roundBitmap = ImageUtils.getRoundedCornerBitmap(bitmap, 25.0f);
        imageView.setImageBitmap(roundBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center_ar_icon:
                //显示弹窗
                AlertDialogPhoto();
                break;
            case R.id.center_voice_icon:
                startActivity(new Intent(getActivity(), SpeachActivity.class));
                break;
            case R.id.center_recovery_icon:
                intent = new Intent(getActivity(), WebContentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void AlertDialogPhoto() {
        androidx.appcompat.app.AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setTitle("上传头像")
                .setItems(new String[]{"拍照"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://拍照
                                takephoto();
                                break;
                        }
                    }
                });
        dialog.create().show();
    }

    private void takephoto() {
        outputImage = new File(getActivity().getExternalCacheDir(), "sort.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(getActivity(), "myhead1", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == getActivity().RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity()
                                .getContentResolver()
                                .openInputStream(imageUri));
                        uploadImg();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PICK_PHOTO:
                if (resultCode == getActivity().RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void uploadImg() {
//        String url="https://129.211.75.130:8080/demo/file/upload";
        String url = "http://192.168.137.194:8080/file/upload";
        Map<String,File> map=new HashMap<>();
        map.put("files",new File(outputImage.getPath()));
        XutilsHttp.getInstance().upLoadFile(url, null, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                Gson gson=new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                Map<String, Object> info = jsonBean.getInfo();
                Object data = info.get("data");
                String s = gson.toJson(info);
                System.out.println("**************************************\n"+s);
                Result result1 = gson.fromJson(s, Result.class);
                List<Result.DataBean> data1 = result1.getData();
                if (data1!=null){
                    System.out.println("=====================================\n"+data1.get(0).toString());
                    ShowData(data1);
                }else {
                    List<Result.DataBean> data2 =new ArrayList<>();
                    Result.DataBean datalistBean=new Result.DataBean();
                    datalistBean.setGname("未搜索到或暂未收录");
                    datalistBean.setGtype(4);
                    datalistBean.setAipre(4);
                    datalistBean.setGexplain("未搜索到或暂未收录");
                    datalistBean.setGcontain("未搜索到或暂未收录");
                    datalistBean.setGtip("未搜索到或暂未收录");
                    data2.add(datalistBean);
                    ShowData(data2);
                }
            }

            @Override
            public void onFail(String result) {
                System.out.println("失败结果"+result.toString());
            }

            @Override
            public void onCancel(CancelledException cex) {
                System.out.println("取消结果"+cex.toString());
            }
        });


    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imgPath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getActivity(), uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imgPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.provider.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imgPath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imgPath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imgPath = uri.getPath();
        }
        displayImagePath(imgPath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImagePath(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImagePath(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);


        } else {
            Toast.makeText(getActivity(), "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void getData(List<NewsBeans.NewslistBean> newslistBeanList) {
        NewsAdapter adapter = new NewsAdapter(getActivity(), newslistBeanList, false);
        adapter.setOnDelListener(new NewsAdapter.onSlideListener() {
            @Override
            public void onDel(int position) {
            }

            @Override
            public void onTop(int position) {
                NewsData newsData = new NewsData();
                newsData.setCtime(newslistBeanList.get(position).getCtime());
                newsData.setDescription(newslistBeanList.get(position).getDescription());
                newsData.setPicUrl(newslistBeanList.get(position).getPicUrl());
                newsData.setTitle(newslistBeanList.get(position).getTitle());
                newsData.setUrl(newslistBeanList.get(position).getUrl());
                try {
                    Selector<NewsData> newsDataSelector = db.selector(NewsData.class).where("title", "=", newsData.getTitle())
                            .orderBy("ctime");
                    if (newsDataSelector.count() > 0) {
                        Toast.makeText(getActivity(), "已经收藏过了", Toast.LENGTH_SHORT).show();
                    } else {
                        db.save(newsData);
                        Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
        NewsRv.setAdapter(adapter);
    }


    /**
     * 显示数据
     *
     * @param data
     */
    private void ShowData(List<Result.DataBean> data) {
            Dialog dialog = new Dialog(getActivity(), R.style.custom_dialog);
            //设置布局
            dialog.setContentView(R.layout.dialog_rv_layout);
            RecyclerView rv = dialog.findViewById(R.id.dialog_rv);
            LinearLayoutManager ms = new LinearLayoutManager(getActivity());
            ms.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(ms);
            rv.setAdapter(new SearchRvAdapter2(getActivity(), data, new SearchRvAdapter2.OnItemClickListener() {
                @Override
                public void onClick(int position) {

                }
            }));
            WindowManager manager = getActivity().getWindow().getWindowManager();
            Display display = manager.getDefaultDisplay();
            final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            dialog.show();
            // 设置宽度
            Point size = new Point();
            display.getSize(size);
            // 宽度为当前屏幕的90%
            params.width = (int) (size.x * 0.95);
            params.height = (int) (size.y * 0.9);
            dialog.getWindow().setAttributes(params);
    }
}

