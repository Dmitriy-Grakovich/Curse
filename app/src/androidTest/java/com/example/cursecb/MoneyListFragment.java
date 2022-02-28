package com.example.cursecb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MoneyListFragment extends Fragment {

    private RecyclerView mMoneyRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money_list, container,
                false);
        mMoneyRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mMoneyRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));
        updateUI();
        return view;
    }
    private void updateUI() {
        MoneyLab moneyLab = MoneyLab.get(getActivity());
        List<Money> crimes = moneyLab.getMonies();
        mAdapter = new CrimeAdapter(crimes);
        mMoneyRecyclerView.setAdapter(mAdapter);
    }
    private class CrimeHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mValueTextView;
        private Money mMoney;
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_money, parent, false));
            mNameTextView = (TextView) itemView.findViewById(R.id.money_name);
            mValueTextView = (TextView) itemView.findViewById(R.id.money_value);
        }
        public void bind(Money money) {
            mMoney = money;
            mNameTextView.setText(mMoney.getName());
            mValueTextView.setText(mMoney.getValue().toString());
        }

    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Money> mMonies;
        public CrimeAdapter(List<Money> monies) {
            mMonies = monies;
        }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);

        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Money money = mMonies.get(position);
            holder.bind(money);
        }
        @Override
        public int getItemCount() {
            return mMonies.size();
        }
    }

}
