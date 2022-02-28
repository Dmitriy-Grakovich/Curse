package com.example.cursecb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MoneyFragment extends Fragment {

    private TextView mValueTextView;
    private TextView mNameTextView;
    private TextView mNominalTextView;
    private TextView mPreviousTextView;
    private TextView mCharCodeTextView;
    private Money mMoney;
    private static final String ARG_CRIME_ID = "money_id";

    public static MoneyFragment newInstance(String moneyId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, moneyId);
        MoneyFragment fragment = new MoneyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String moneyId = (String) getArguments().getSerializable(ARG_CRIME_ID);
        mMoney = MoneyLab.get(getActivity()).getMoney(moneyId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_money, container, false);
        mNameTextView = (TextView) v.findViewById(R.id.money_name);
        mValueTextView = (TextView) v.findViewById(R.id.money_value);
        mNominalTextView = (TextView) v.findViewById(R.id.money_nominal);
        mCharCodeTextView = (TextView) v.findViewById(R.id.money_charcode);
        mPreviousTextView = (TextView) v.findViewById(R.id.money_previous);
        mNominalTextView.setText(mMoney.getNominal().toString());
        mNameTextView.setText(mMoney.getName());
        mCharCodeTextView.setText(mMoney.getCharCode());
        mPreviousTextView.setText(mMoney.getPrevious().toString());
        mValueTextView.setText(mMoney.getValue().toString());
        return v;
    }

}
