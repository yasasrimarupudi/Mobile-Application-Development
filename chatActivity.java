
package com.example.chatingappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {
    Button send;
    EditText txt_phone_number, txt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_phone_number = (EditText) findViewById(R.id.txt_phone_number);
        txt_message = (EditText) findViewById(R.id.txt_message);
        send = (Button)findViewById(R.id.button);
    }

    public void btn_sender(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            MyMessage();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }

    }

    private void MyMessage() {
        String phoneNumber = txt_phone_number.getText().toString().trim();
        String Message = txt_message.getText().toString().trim();

        if (!txt_phone_number.getText().toString().equals("") || !txt_message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Message, null, null);

            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"please Enter Number or Message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyMessage();
                } else {
                    Toast.makeText(this, "You don't have Required Permission to make this action", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
