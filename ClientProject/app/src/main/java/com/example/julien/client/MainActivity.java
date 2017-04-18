package com.example.julien.client;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.julien.client.Interface.MessageReceivedInterface;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static int port =8080;
    private static String ip ="192.168.0.57";
    Socket socket;
    DataInputStream userInput;
    PrintStream theOutputStream;
    String messages;

    private Button buttonPing;
    private EditText editTextViewMessage;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        buttonPing = (Button) findViewById(R.id.buttonPing);
        editTextViewMessage = (EditText) findViewById(R.id.EditTextViewMessage);
        msg = (TextView) findViewById(R.id.msg);

        //On délcenche l'écoute des messages rentrant dans le chat

        MessageReceivedTask messageReceivedTask = new MessageReceivedTask(new MessageReceivedInterface() {
            @Override
            public void IOListener(String message) {

                //editTextViewMessage.invalidate();
                messages += message + " \n ";
                msg.setText(messages);
            }
        });

        messageReceivedTask.execute(ip, String.valueOf(port));

        buttonPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Hello team7";
                message = editTextViewMessage.getText().toString();

                Toast.makeText(getBaseContext(), "text "+ip+":"+port, Toast.LENGTH_LONG).show();
                MessageSendTask messageTask = new MessageSendTask();
                messageTask.execute(ip, String.valueOf(port), message);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //client.onDestroy()
    }


}
