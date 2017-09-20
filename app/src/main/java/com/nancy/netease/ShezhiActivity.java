package com.nancy.netease;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class ShezhiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private RelativeLayout clear;
    private RelativeLayout update;
    private TextView tv_clear;
    private TextView tv_update;
    private ProgressBar mProgress;

    private static  final  int DOWN_UPDATE=1;
    private static  final  int DOWN_OVER=2;
    private int progress;
    private Thread downLoadThread;
    private boolean interceptFlag=false;
    private String updateMsg="有最新的软件包，快下载吧";
    //http://softfile.3g.qq.com:8080/msoft/179/24659/43549/qq_hd_mini_1.4.apk"
    private String apkUrl="http://gdown.baidu.com/data/wisegame/f98d235e39e29031/baiduxinwen.apk";
    private static final  String savePath="mnt/sdcard/updateddemo";
    private static final  String saveFileName=savePath+"UpdateDemoRelease.apk";

    private boolean ts_flag1=false;
    private ImageView iv_img1;

    private boolean ts_flag2=false;
    private ImageView iv_img2;

    private boolean ts_flag3=false;
    private ImageView iv_img3;

    private boolean ts_flag4=false;
    private ImageView iv_img4;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    break;
                case DOWN_OVER:
                    installApk();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        initView();
        initData();
    }

    /**
     * 初始化
     */
    private void initView() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        //RelativeLayout
        clear = (RelativeLayout) findViewById(R.id.clear);
        update = (RelativeLayout) findViewById(R.id.update);
        //TextView
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_update = (TextView) findViewById(R.id.tv_update);
        clear.setOnClickListener(this);
        update.setOnClickListener(this);

        iv_img1 = (ImageView) findViewById(R.id.iv_img1);
        iv_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ts_flag1==false)
                {
                    ts_flag1=true;
                    iv_img1.setImageResource(R.drawable.select);
                }
                else
                {
                    ts_flag1=false;
                    iv_img1.setImageResource(R.drawable.none);

                }
            }
        });

        iv_img2 = (ImageView) findViewById(R.id.iv_img2);
        iv_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ts_flag2==false)
                {
                    ts_flag2=true;
                    iv_img2.setImageResource(R.drawable.select);
                }
                else
                {
                    ts_flag2=false;
                    iv_img2.setImageResource(R.drawable.none);

                }
            }
        });

        iv_img3 = (ImageView) findViewById(R.id.iv_img3);
        iv_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ts_flag3==false)
                {
                    ts_flag3=true;
                    iv_img3.setImageResource(R.drawable.select);
                }
                else
                {
                    ts_flag3=false;
                    iv_img3.setImageResource(R.drawable.none);

                }
            }
        });

        iv_img4 = (ImageView) findViewById(R.id.iv_img4);
        iv_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ts_flag4==false)
                {
                    ts_flag4=true;
                    iv_img4.setImageResource(R.drawable.select);
                }
                else
                {
                    ts_flag4=false;
                    iv_img4.setImageResource(R.drawable.none);

                }
            }
        });


    }
    /**
     * 初始化缓存大小和版本信息
     */
    private void initData() {

        //ImageLoader.getInstance().displayImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3740267368,4049536772&fm=11&gp=0.jpg", iv);

        initVersion();
        initCaches();

    }
    /**
     * 初始化缓存信息
     */
    private void initCaches() {

        long fileSize = 0;
        //获取data/data/包名／cache文件大小
        fileSize += getDirSize(getCacheDir());
        //获取files文件夹大小
        fileSize += getDirSize(getFilesDir());

        tv_clear.setText(formatFileSize(fileSize));

        System.out.println("cachedir" + getCacheDir());
        System.out.println("filesdir" + getFilesDir());
    }

    /**
     * 初始化版本信息
     */
    private void initVersion() {
        //版本信息
        try {
            PackageManager manager = getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            tv_update.setText("V" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
       switch (view.getId())
       {
           case R.id.iv_back:
               finish();
               break;
           case R.id.clear:
               final AlertDialog.Builder clearBuilder = new AlertDialog.Builder(this);
               clearBuilder.setTitle("清理缓存");
               clearBuilder.setMessage("确定要清理缓存？");
               clearBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               clearBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       clearCaches();
                   }
               });

               clearBuilder.show();

               break;
           case R.id.update:
               final AlertDialog.Builder versionBuilder = new AlertDialog.Builder(this);
               versionBuilder.setTitle("检查版本");
               versionBuilder.setMessage("确定要版本更新？");
               versionBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               versionBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(ShezhiActivity.this, "版本更新", Toast.LENGTH_SHORT).show();
                       showDownloadDialog();
                   }
               });

               versionBuilder.show();

               break;
       }
    }

    private void showDownloadDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("软件版本更新");
        final LayoutInflater inflater= LayoutInflater.from(this);
        View v=inflater.inflate(R.layout.progress,null);
        mProgress = v.findViewById(R.id.mprogress);
        builder.setView(v);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
                interceptFlag=true;
            }
        });
        builder.create().show();
        downloadApk();
    }

    private Runnable mdownApkRunnable=new Runnable() {
        @Override
        public void run() {
            try {
                URL url=new URL(apkUrl);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.connect();
                int lenght=conn.getContentLength();
                InputStream is = conn.getInputStream();
                File file=new File(savePath);
                if(!file.exists()){
                    file.mkdir();
                }
                String apkFile=saveFileName;
                File ApkFile=new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count=0;
                byte buf[]=new byte[1024];
                do {
                    int numread=is.read(buf);
                    count +=numread;
                    progress=(int)(((float)count/lenght)*100);
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if(numread<=0){
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf,0,numread);
                }while (!interceptFlag);
                fos.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void downloadApk(){
        downLoadThread=new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    private void installApk(){
        File apkfile=new File(saveFileName);
        if(!apkfile.exists()){
            return;
        }
        Intent inn=new Intent(Intent.ACTION_VIEW);
        inn.setDataAndType(Uri.parse("file://"+apkfile.toString()),"application/vnd.android.package-archive");
        this.startActivity(inn);

    }

    /**
     * 清理缓存
     */
    private void clearCaches() {
        //清除cache目录下缓存
        clearCaches(getCacheDir());
        //清除files目录下缓存
        clearCaches(getFilesDir());
        File sharedDir = new File("/data/data/" + getPackageName() + "/shared_prefs");
        clearCaches(sharedDir);

        //清除成功后，重新设置下页面数据
        tv_clear.setText("0B");

    }

    /**
     * 清除缓存逻辑
     *
     * @param dir
     */
    private void clearCaches(File dir) {
        //判断file是文件夹
        if (dir != null && dir.isDirectory()) {

            //得到该目录下所有文件
            File[] f = dir.listFiles();
            //遍历删除该目录下所有文件
            if (f!=null&&f.length>0){

                for (File file1 : f) {

                    //删除方法
                    file1.delete();

                }
            }

            Toast.makeText(ShezhiActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {//如果是文件的，累加大小
                dirSize += file.length();
            } else if (file.isDirectory()) {//如果是目录，累加大小
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");//保留两位小数
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
