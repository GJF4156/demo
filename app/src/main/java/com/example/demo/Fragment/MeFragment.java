package com.example.demo.Fragment;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.ScreenUtils;
import com.example.demo.activity.ContentActivity;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    FontIconView msign;
    FontIconView minsetting;
    CardView cv_collect,cv2_collect;
    RelativeLayout rlayout_follow;
    RelativeLayout rlayout_info;
    RelativeLayout rlayout_setting;
    RelativeLayout rlayout_about;
    CircleImageView mineHead;

    private TextView mine_username, userId;
    private DbManager db;

    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int PICK_PHOTO = 2;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        initDb();
        bindview(view);
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

    private void bindview(View view) {
        msign = view.findViewById(R.id.mine_sign);
        minsetting = view.findViewById(R.id.mine_minsetting);
        cv_collect = view.findViewById(R.id.mine_cardview);
        cv2_collect = view.findViewById(R.id.mine_card2view);
        rlayout_about = view.findViewById(R.id.relativelayout_jifen);
        rlayout_follow = view.findViewById(R.id.relativelayout_info);
        rlayout_info = view.findViewById(R.id.relativelayout_setting);
        rlayout_setting = view.findViewById(R.id.relativelayout_about);
        mineHead = view.findViewById(R.id.mine_head);
        mine_username = view.findViewById(R.id.mine_username);
        userId = view.findViewById(R.id.userId);
//        string_num1=view.findViewById(R.id.string_num1);

        initData();
        msign.setOnClickListener(this);
        minsetting.setOnClickListener(this);
        cv_collect.setOnClickListener(this);
        cv2_collect.setOnClickListener(this);
        rlayout_about.setOnClickListener(this);
        rlayout_follow.setOnClickListener(this);
        rlayout_info.setOnClickListener(this);
        rlayout_setting.setOnClickListener(this);
        mineHead.setOnClickListener(this);
    }

    private void initData() {
        mine_username.setText(SPUtil.getNickName());
        userId.setText(SPUtil.getUserId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_head:
                //显示头像
                AlertDialogPhoto();
                break;
            case R.id.mine_minsetting:
                //跳转到个人资料页面
                intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.mine_sign:
                Toast.makeText(getActivity(), "签到功能正在优化...", Toast.LENGTH_SHORT).show();
                //showDialog();
                break;
            case R.id.mine_cardview:
                //跳转到我的收藏页面
                intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type", "3");
                startActivity(intent);
                break;
            case R.id.relativelayout_jifen:
                //跳转到积分页面
                intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type", "4");
                startActivity(intent);
                break;
            case R.id.relativelayout_info:
                //跳转到通知页面
                intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type", "5");
                startActivity(intent);
                break;
            case R.id.relativelayout_setting:
                //跳转到设置页面
                intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type", "6");
                startActivity(intent);
                break;
            case R.id.relativelayout_about:
                //跳转到关于我们页面
                intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type", "7");
                startActivity(intent);
                break;
            case R.id.mine_card2view:
                intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type", "10");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void setScreenBgDarken() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        lp.dimAmount = 0.5f;
        getActivity().getWindow().setAttributes(lp);
    }

    public void showDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sign_layout, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.setCancelable(false);
        Button sign_btn = view.findViewById(R.id.sign_btn);
        sign_btn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setScreenBgDarken();
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(getActivity()) / 24 * 17), RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    private void AlertDialogPhoto() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setTitle("上传头像")
                .setItems(new String[]{"从相册选择", "拍照"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://从相册选择
                                pickphoto();
                                break;
                            case 1://拍照
                                takephoto();
                                break;
                        }
                    }
                });
        dialog.create().show();
    }

    //调用系统相册
    private void pickphoto() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }

    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    //调用摄像头
    private void takephoto() {
        File outputImage = new File(getActivity().getExternalCacheDir(), "outputImage.jpg");
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
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        mineHead.setImageBitmap(bitmap);
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
            mineHead.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getActivity(), "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }

}
