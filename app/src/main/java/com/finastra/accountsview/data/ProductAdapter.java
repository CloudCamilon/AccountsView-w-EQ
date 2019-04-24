package com.finastra.accountsview.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finastra.accountsview.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.productName.setText(productList.get(position).getProductName());
        holder.productAccountType.setText(productList.get(position).getProductAccountType());
    }

    @Override
    public int getItemCount() {
        try {
            return productList.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productAccountType;

        public ProductViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.productName);
            productAccountType = (TextView) view.findViewById(R.id.productAccountType);
        }
    }
}
