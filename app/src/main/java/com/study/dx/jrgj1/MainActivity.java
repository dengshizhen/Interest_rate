package com.study.dx.jrgj1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private TextView textView;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private EditText editText6;
    private EditText editText7;
    private Button button;
    private  double num1;
    private  double num2;
    private double num3;
    private int times;
    private String ny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=(EditText) findViewById(R.id.editText);
        editText2=(EditText) findViewById(R.id.editText2);
        editText3=(EditText) findViewById(R.id.editText3);
        editText6=(EditText) findViewById(R.id.editText6);
        editText7=(EditText) findViewById(R.id.editText7);
        button=(Button) findViewById(R.id.button);
        radioGroup=(RadioGroup) findViewById(R.id.radioGoupe);
        button.setOnClickListener(OnClickListener);
        radioGroup.setOnCheckedChangeListener(ChangeListener);

    }

    private RadioGroup.OnCheckedChangeListener ChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup arg0, int arg1) {
            int radioButtonId = arg0.getCheckedRadioButtonId();
                           //根据ID获取RadioButton的实例
            radioButton1 = (RadioButton) findViewById(radioButtonId);
            if(radioButton1.getText().equals("月")){
                times=30;
                ny="月";
            }else{
                times=360;
                ny="年";
            }

        }
    };
    private View.OnClickListener OnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            num1=Double.parseDouble(String.valueOf(editText1.getText()));
            num2=Double.parseDouble(String.valueOf(editText2.getText()));
            num3=Integer.parseInt(String.valueOf(editText3.getText()));

            System.out.println(""+num1+" "+num2+" "+times);
            int count1=0;
            int count2=0;
            double tmp=0;
            String[] result=new String[1000000];
            String[] result2=new String[1000000];
            for(int i=0;i<num3*times;i++){
                double temp=num2;
                tmp+=num1*num2;
                num2 = num2 - num1 * num2;
                    result[count1] = new String("");
                    result[count1] = (count1 + 1) + "   " + String.valueOf(num1 * temp) + "　" + String.valueOf(temp);
                if((count1+1)%times==0) {
                    result2[count2]="第"+(count2+1)+ny+":"+String.valueOf(tmp);
                    count2++;
                    tmp=0;
                }
                    count1++;


            }
            editText6.setText("每天计算结果");
            for(int i=0;i<count1;i++){
                System.out.println(result[i]);
                       editText6.append("\r\n"+result[i]+"\r\n");
            }
            editText7.setText("统计结果");
            for(int i=0;i<count2;i++){
                System.out.println(result2[i]);
                editText7.append("\r\n"+result2[i]+"\r\n");
            }

            WriteStringToFile(result2,count1);


        }
    };
    public void getSDPath(){
        File sdDir = null;
        File sdDir1 = null;
        File sdDir2 = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            sdDir1 = Environment.getDownloadCacheDirectory();
            sdDir2 = Environment.getRootDirectory();
        }
        System.out.println("getExternalStorageDirectory(): "+sdDir.toString());
        System.out.println("getDataDirectory(): "+sdDir1.toString());
        System.out.println("getRootDirectory(): "+sdDir2.toString());
    }

    public void WriteStringToFile(String[] result,int count) {
        try {
            File sdDir = null;
            File sdDir1 = null;
            File sdDir2 = null;
            boolean sdCardExist = Environment.getExternalStorageState()
                    .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
            if (sdCardExist)
            {
                sdDir = Environment.getExternalStorageDirectory();//获取跟目录
                sdDir1 = Environment.getDownloadCacheDirectory();
                sdDir2 = Environment.getRootDirectory();
            }
            System.out.print(sdDir);
            File file = new File("/sdcard/result.txt");
            if(!file.exists()){
                System.out.println("不存在");
                System.out.println(file.createNewFile());
                file.createNewFile();
            }
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            //ps.println("http://www.jb51.net");// 往文件里写入字符串
            ps.println("");
            for(int i=0;i<count;i++){
                ps.append(result[i]);// 在已有的基础上添加字符串
                ps.append("\r\n");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
