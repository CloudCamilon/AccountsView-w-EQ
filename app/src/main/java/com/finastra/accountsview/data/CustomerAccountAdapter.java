package com.finastra.accountsview.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finastra.accountsview.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerAccountAdapter extends RecyclerView.Adapter<CustomerAccountAdapter.CustomerAccountViewHolder> {
    private List<CustomerAccount> customerAccountList;

    public CustomerAccountAdapter(List<CustomerAccount> customerAccountList) {
        this.customerAccountList = customerAccountList;
    }

    @Override
    public CustomerAccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new CustomerAccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomerAccountViewHolder holder, int position) {
        holder.productName.setText(customerAccountList.get(position).getAccountId());
        holder.productAccountType.setText(customerAccountList.get(position).getAccountName());
    }

    @Override
    public int getItemCount() {
        try {
            return customerAccountList.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public class CustomerAccountViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productAccountType;

        public CustomerAccountViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.productName);
            productAccountType = (TextView) view.findViewById(R.id.productAccountType);
        }
    }
}
