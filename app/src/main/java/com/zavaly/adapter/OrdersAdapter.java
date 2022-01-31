package com.zavaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.R;
import com.zavaly.models.orders.Order;
import com.zavaly.utils.ChildClickListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private Context context;
    private List<Order> orders = new ArrayList<>();
    private ChildClickListener childClickListener;

    public OrdersAdapter(Context context, List<Order> orders, ChildClickListener childClickListener) {
        this.context = context;
        this.orders = orders;
        this.childClickListener = childClickListener;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_orders_rv, parent, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {

        if (orders != null) {

            holder.dateTV.setText("Date: " + orders.get(position).getCreatedAt());
            holder.orderCodeTV.setText(orders.get(position).getOrderCode());
            holder.orderStatusTV.setText(orders.get(position).getStatus());

        }

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTV, orderCodeTV, orderStatusTV;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.date_tv);
            orderCodeTV = itemView.findViewById(R.id.order_code_tv);
            orderStatusTV = itemView.findViewById(R.id.order_status_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    childClickListener.onChildClick(view, getAdapterPosition());

                }
            });
        }
    }
}
