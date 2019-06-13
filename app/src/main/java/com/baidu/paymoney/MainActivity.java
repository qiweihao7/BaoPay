package com.baidu.paymoney;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    private static final String TAG = "MainActivity";

    private static final int SDK_PAY_FLAG = 10;
    private Button payFor;
    //post请求,token,金额,应用版本号,操作系统系统
    private String mUrl = "http://api.banmi.com/api/app/v3/payments/alipay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniper();
        initView();
    }

    private void iniper() {
        String[] per = {Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(this,per,100);
    }

    private void initView() {
        payFor = (Button) findViewById(R.id.payFor);
        payFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliPay();
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void aliPay() {

        // 1.请求自己家的服务器 商品id 用户id 商品价格 商品数量
        RequestQueue queue = Volley.newRequestQueue(this); //请求队列
        StringRequest request = new StringRequest(Request.Method.POST, mUrl, this, this){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                ArrayMap<String, String> map = new ArrayMap<>();
                map.put("amount","1.0");
                map.put("token","mKVoQRrXlMvmfWbgWzyDYHF1KOUN5e4bwWrOiab9tZ8sWIQAZmg48lSa2NOORqpko6V0l4cYCs1Uip23M6MglE6Xp6LvHKO9RUNP4m8pHjp1zHb8nmK1bU1pvRp2LFkwA");
                map.put("version","3.3.3");
                map.put("os","android");
                return map;
            }
        };
        queue.add(request); // ☆ 发起请求

    }

    // 成功回调
    @Override
    public void onResponse(String s) {
        Log.i(TAG, "onErrorResponse: "+s);
        // 2.解析自己服务器返回的数据 拿到支付串码
        Gson gson = new Gson();
        AliPayBean aliPayBean = gson.fromJson(s, AliPayBean.class);

                // 3.调用支付宝的SKD,拉起支付界面
         callAliSdk(aliPayBean);


    }

    private void callAliSdk(AliPayBean aliPayBean) {
        final String orderInfo = aliPayBean.getResult().getSign();   // 订单信息

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String,String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    //4.处理支付宝app返回的支付结果
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showToast("支付成功:"+payResult);
                        Log.d(TAG, "支付成功: "+payResult+",resultInfo:"+resultInfo);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showToast("支付失败:"+payResult);
                        Log.d(TAG, "支付失败: "+payResult+",resultInfo:"+resultInfo);
                    }
                    break;
                }

                default:
                    break;
            }
        };
    };

    // 失败回调
    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
