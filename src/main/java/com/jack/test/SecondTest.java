package com.jack.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondTest {

    public static void main(String[] args) {
        String urlPath = "https://www.cnblogs.com/jack1995/p/9198235.html";
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            //connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Charset","UTF-8");

            connection.connect();

            int resultCode = connection.getResponseCode();
            if(HttpURLConnection.HTTP_OK == resultCode){
                StringBuffer sb = new StringBuffer();
                String readLine = new String();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));

                boolean flag = false;
                while ((readLine = br.readLine())!=null){
                    if(readLine.contains("href") && readLine.contains("postTitle2")){
                        System.out.println(readLine);
                        Pattern pattern = Pattern.compile("(?<=href\\=\").*?(?=\"\\>)");
                        Matcher matcher = pattern.matcher(readLine);
                        while(matcher.find()){
                            System.out.println(matcher.group());
                        }
                        Pattern pattern1 = Pattern.compile("(?<=html\"\\>).*?(?=\\<)");
                        matcher = pattern1.matcher(readLine);
                        while (matcher.find()){
                            System.out.println(matcher.group());
                        }

                    }
                    if(!flag){
                        if(readLine.contains("cnblogs_post_body")){
                            flag = true;
                        }
                    }

                    if(readLine.contains("postDesc")){
                        flag = false;
                        System.out.println(readLine);
                        Pattern pattern1 = Pattern.compile("(?<=date\"\\>).*?(?=\\<)");
                        Matcher matcher = pattern1.matcher(readLine);
                        while (matcher.find()){
                            System.out.println(matcher.group());
                        }
                    }
                    if(flag){
                        sb.append(readLine).append("\n");
                    }


                }
                System.out.println(sb.toString());
                br.close();
                //System.out.println(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
