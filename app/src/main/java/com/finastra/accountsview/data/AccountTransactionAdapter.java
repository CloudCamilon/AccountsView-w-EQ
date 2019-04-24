package com.finastra.accountsview.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finastra.accountsview.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountTransactionAdapter extends RecyclerView.Adapter<AccountTransactionAdapter.AccountTransactionViewHolder> {
    private List<AccountTransaction> accountTransactionList;

    public AccountTransactionAdapter(List<AccountTransaction> accountTransactionList) {
        this.accountTransactionList = accountTransactionList;
    }

    @Override
    public AccountTransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new AccountTransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountTransactionViewHolder holder, int position) {
        holder.productName.setText(accountTransactionList.get(position).getDescription());
        holder.productAccountType.setText(accountTransactionList.get(position).getValueDate());
    }

    @Override
    public int getItemCount() {
        try {
            return accountTransactionList.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public class AccountTransactionViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productAccountType;

        public AccountTransactionViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.productName);
            productAccountType = (TextView) view.findViewById(R.id.productAccountType);
        }
    }
}
