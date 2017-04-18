package com.example.julien.client;

import android.os.AsyncTask;

import com.example.julien.client.Interface.MessageReceivedInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Julien on 18/04/2017.
 */

public class MessageReceivedTask extends AsyncTask<String, Void, String>{


    String str;
    MessageReceivedInterface listener;

    public MessageReceivedTask(MessageReceivedInterface listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        try{

            Socket socket = new Socket(params[0], Integer.parseInt(params[1]));

            while(true){

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()
                ));
                str = in.readLine();
                listener.IOListener(str);
                in.close();
                socket.close();
            }

        }catch(IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Hello result";
    }
}
