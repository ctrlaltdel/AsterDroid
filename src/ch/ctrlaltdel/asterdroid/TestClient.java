package ch.ctrlaltdel.asterdroid;

import java.io.*;
import java.net.*;
import android.widget.TextView;

public class TestClient {
  public static void main(TextView tv) throws IOException {
    Socket s = null;
    PrintWriter out = null;
    BufferedReader in = null;

    System.err.println("TestClient.main");
  
    try {
      s = new Socket("62.220.137.130", 1234);
      in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      System.err.println("Connected");
    } catch (UnknownHostException e) {
      System.err.println("UnknownHostException");
      tv.setText("Unknown host");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("IOException");
      tv.setText("Couln't connect to server");
      System.exit(1);
    }
  
    String userInput;
  
    while ((userInput = in.readLine()) != null) {
        System.err.println(userInput);
        tv.setText(userInput);
        try {
          Thread.sleep(1000);
        } catch (Exception e) { }
    }
  
    out.close();
    s.close();
  }
}
