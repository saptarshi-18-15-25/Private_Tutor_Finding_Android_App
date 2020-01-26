package com.example.new_tryv10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    TextView dname,dnum,dadd,dsub;
    ImageButton msg,cll;
    String s,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dname = findViewById(R.id.dtextView);
        dnum = findViewById(R.id.dtextView2);
        dadd = findViewById(R.id.textView);
        dsub = findViewById(R.id.textView2s);

        msg = findViewById(R.id.message_btn);
        cll = findViewById(R.id.call_btn);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String j = (String) b.get("shname");
            dname.setText(j);
            n=j;
            String i = (String) b.get("shnum");
            dnum.setText(i);
            s="tel:"+i;
            String k = (String) b.get("saddress");
            dadd.setText(k);
            String l = (String) b.get("ssub");
            dsub.setText(l);
        }
        cll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(Intent.ACTION_CALL);
                ic.setData(Uri.parse(s));
                startActivity(ic);
                Toast.makeText(DetailsActivity.this,"Calling...",Toast.LENGTH_LONG).show();
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(s,null,"Hi, I want to talk to you about tution",null,null);
                Toast.makeText(DetailsActivity.this,"Request has been sent",Toast.LENGTH_LONG).show();

            }
        });

    }
}
