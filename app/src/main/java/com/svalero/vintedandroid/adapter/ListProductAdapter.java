package com.svalero.vintedandroid.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.svalero.vintedandroid.R;
import com.svalero.vintedandroid.UserMenuActivity;
import com.svalero.vintedandroid.beans.Product;
import com.svalero.vintedandroid.showProduct.view.ShowProductActivity;

import java.util.ArrayList;

import static com.svalero.vintedandroid.R.drawable.ic_image;
import static com.svalero.vintedandroid.showProduct.view.ShowProductActivity.PRODUCT_ID;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ProductViewHolder> {

    private ArrayList<Product> listProducts;
    private Context context;
    private Activity activity;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public TextView rate;
        public TextView price;
        private ImageView productImage;

        public ProductViewHolder(View v) {
            super(v);
            productName = v.findViewById(R.id.txtProductName);
            rate = v.findViewById(R.id.txtRate);
            price = v.findViewById(R.id.txtPrice);
            productImage = v.findViewById(R.id.row_product_image);
        }
    }

    public ListProductAdapter(ArrayList<Product> listProducts, Context context, Activity activity){
        this.listProducts = listProducts;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = listProducts.get(position);

        holder.productName.setText(product.getName());
        holder.rate.setText(String.valueOf(product.getRate()));
        holder.price.setText(product.getPrice() + " €");
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowProductActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,
                    Pair.create(holder.productImage, holder.productImage.getTransitionName()),
                    Pair.create(holder.productName, holder.productName.getTransitionName()),
                    Pair.create(holder.price, holder.price.getTransitionName()));
            intent.putExtra(PRODUCT_ID, product.getId());
            context.startActivity(intent, options.toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

}
