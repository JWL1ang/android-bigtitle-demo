package top.iqin.bigtitledemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private TextView textView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list;
    private ListView listView;
    private ScrollView scrollView;
    private TextView bigTitle_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int[] location = new  int[2] ;
                bigTitle_tv.getLocationInWindow(location); //得到大标题距离activity顶部距离
                System.out.println(location[1]);
                if(location[1] < -60){
//                    System.out.println("显示正常标题\n");
                    textView.setVisibility(View.VISIBLE);
                }
                else{
//                    System.out.println("隐藏正常标题\n");
                    textView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void init() {
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.list_view);
        scrollView = findViewById(R.id.scrollView);
        bigTitle_tv = findViewById(R.id.big_title);
        findViewById(R.id.normal_layout).bringToFront();    //把正常标题拉到顶层显示
        findViewById(R.id.normal_layout).setFocusable(false);
        listView.setFocusable(false);   //启动时页面时ScrollView起始位置不是最顶部，将ListView获取焦点设为false
        list = new ArrayList<>();
        for(int i= 0;i<20;i++){
            list.add("第"+i+"个");
        }
        arrayAdapter = new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,list
        );
        listView.setAdapter(arrayAdapter);
        }


}
