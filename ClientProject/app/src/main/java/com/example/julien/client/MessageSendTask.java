package com.example.julien.client;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Julien on 18/04/2017.
 */

public class MessageSendTask extends AsyncTask<String, Void, String>{

    Socket socket;
    String ip, message;
    int port;

/*
    public MessageSendTask(String ip, int port, String message){
        this.ip = ip;
        this.port = port;
        this.message = message;
    }
*/
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params) {
        String msg = "hello";

        try{
            socket = new Socket(params[0],Integer.parseInt(params[1]));


            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())));

            out.println(params[2]);
            out.flush();
            out.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
