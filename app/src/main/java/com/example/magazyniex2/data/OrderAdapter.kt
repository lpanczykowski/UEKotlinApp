package com.example.magazyniex2.data

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magazyniex2.R
import com.example.magazyniex2.data.model.Order
import com.example.magazyniex2.data.model.SkanItem
import com.example.magazyniex2.ui.skan.SkanActivity
import kotlinx.android.synthetic.main.item_grid.view.*

class OrderAdapter(
    private  val orders: MutableList<Order>
    ):RecyclerView.Adapter<OrderAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

       return  ItemViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_grid,
               parent,
               false

           )
       )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curItem = orders[position]
        if (curItem.IsRealized) {

        }else {
            holder.itemView.apply {
                tvTitle.text = curItem.Number
                tvPriority.text = curItem.Priority.toString()

            }
        }

        var doubleTap = false
        holder.itemView.setOnClickListener {
            if (doubleTap)
            {
                val skanActivity = Intent(holder.itemView.context,SkanActivity::class.java)
                skanActivity.putExtra("Title",holder.itemView.tvTitle.text)
                holder.itemView.context.startActivity(skanActivity)

            }
            doubleTap = true
            Handler(Looper.getMainLooper()).postDelayed({
                doubleTap = false
            }, 1500)
        }
    }
    fun addItem(order: Order)
    {
        orders.add(order)
        notifyItemInserted(orders.size -1)
    }
    fun clear()
    {
        orders.clear()
    }

    fun remove(orderNumber :String)
    {
        val order = orders.find { it.Number == orderNumber }
        if (order != null){
        orders.remove(order);
        notifyItemRemoved(orders.indexOf(order))}
    }
    override fun getItemCount(): Int {
        return orders.size
    }
}