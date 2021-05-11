package com.example.magazyniex2.data

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magazyniex2.R
import com.example.magazyniex2.ui.skan.SkanActivity
import kotlinx.android.synthetic.main.item_grid.view.*

class ItemAdapter(
    private  val items: MutableList<Item>
    ):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

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
        val curItem = items[position]
        holder.itemView.apply {
            tvTitle.text = curItem.title
            tvPriority.text = curItem.priority.toString()

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
    fun addItem(item: Item)
    {
        items.add(item);
    }

    override fun getItemCount(): Int {
        return items.size
    }
}