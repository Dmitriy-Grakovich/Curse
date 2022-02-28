package com.example.cursecb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MoneyListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private MoneyAdapter mAdapter;
    private static final String URL_CB= "https://www.cbr-xml-daily.ru/daily_json.js";
    public static URL mURL;
    private ConnectURLCB mConnectURLCB;
    private List<Money> monies;

    static {
        try {
            mURL = new URL(URL_CB);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money_list, container,
                false);
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));
        updateUI();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    private void updateUI() {
        MoneyLab moneyLab = MoneyLab.get(getActivity());
        monies= moneyLab.getMonies();
        mConnectURLCB = new ConnectURLCB();
        mConnectURLCB.execute(mURL);

    }

    private class MoneyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTextView;
        private TextView mValueTextView;
        private Money mMoney;

        public MoneyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_money, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.money_name);
            mValueTextView = (TextView) itemView.findViewById(R.id.money_value);

        }
        public void bind(Money money) {
            mMoney = money;
            mNameTextView.setText(mMoney.getName());
            mValueTextView.setText(mMoney.getValue().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = CurseActivity.newIntent(getActivity(), mMoney.getId());
            startActivity(intent);
        }
    }

    private class MoneyAdapter extends RecyclerView.Adapter<MoneyHolder> {
        private List<Money> mMonies;
        public MoneyAdapter(List<Money> monies) {
            mMonies = monies;
        }


        @Override
        public MoneyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MoneyHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(MoneyHolder MoneyHolder, int i) {
            Money money = mMonies.get(i);
            MoneyHolder.bind(money);
        }

        @Override
        public int getItemCount() {
            return mMonies.size();
        }
    }
    private class ConnectURLCB extends AsyncTask<URL,Void,String> {


        @Override
        protected String doInBackground(URL... urls) {
            String response = null;

            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }



        private  String getResponseFromURL(URL url) throws IOException{

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                if (scanner.hasNext()) {
                    return scanner.next();
                } else {
                    return null;
                }
            } finally {
                urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);

            JSONObject jsonValute = jsonObject.getJSONObject("Valute");
                Iterator<String> iterator = jsonValute.keys();
           // Set<String> keySet = jsonValute.keys();
            while (iterator.hasNext()) {
                JSONObject jsonMoney = jsonValute.getJSONObject(iterator.next());
                Money money1 = new Money();
                money1.setId(jsonMoney.getString("ID"));
                money1.setNumCode(jsonMoney.getInt("NumCode"));
                money1.setCharCode(jsonMoney.getString("CharCode"));
                money1.setNominal(jsonMoney.getInt("Nominal"));
                money1.setName(jsonMoney.getString("Name"));
                money1.setValue(jsonMoney.getDouble("Value"));
                money1.setPrevious(jsonMoney.getDouble("Previous"));
                monies.add(money1);
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(mAdapter==null){
                mAdapter = new MoneyAdapter(monies);
                mCrimeRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}
