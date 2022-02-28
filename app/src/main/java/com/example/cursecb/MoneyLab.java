package com.example.cursecb;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneyLab {
    private static MoneyLab sMoneyLab;
    private List<Money> mMonies;


    private MoneyLab(Context context){
        mMonies = new ArrayList<>();
    }
    public static MoneyLab get(Context context){
        if(sMoneyLab==null){
            sMoneyLab = new MoneyLab(context);
        }
        return sMoneyLab;
    }
    public List<Money> getMonies(){
        return mMonies;
    }
    public Money getMoney(String id){
        for (Money money:mMonies){
            if(money.getId().equals(id)){
                return money;
            }
        }
        return null;
    }

}
