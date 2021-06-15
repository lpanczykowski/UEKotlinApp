package com.example.magazyniex2.data

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magazyniex2.R
import com.example.magazyniex2.data.model.SkanItem
import com.example.magazyniex2.ui.skan.SkanActivity
import kotlinx.android.synthetic.main.item_grid.view.*
import kotlinx.android.synthetic.main.skan_item_grid.view.*

class SkanItemAdapter (
     private  val items: MutableList<SkanItem>
): RecyclerView.Adapter<SkanItemAdapter.ItemViewHolder>()

{
    class ItemViewHolder(skanItemView : View) : RecyclerView.ViewHolder(skanItemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkanItemAdapter.ItemViewHolder {

        return SkanItemAdapter.ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.skan_item_grid,
                parent,
                false

            )
        )
    }

    override fun onBindViewHolder(holder: SkanItemAdapter.ItemViewHolder, position: Int) {
        val curItem = items[position]
        holder.itemView.apply {
            tvIndex.text = curItem.ItemName
            tvAmount.text = "["+curItem.Quantity.toString()+"]"
            tvAmountScanned.text = curItem.Scanned.toString()
            tvJM.text = curItem.Units
            tvBatchNumber.text = curItem.Batch
        }
        var doubleTap = false
        holder.itemView.setOnClickListener {
            if (doubleTap)
            {
                if (items[position].Scanned.toInt() == 0) {
                    remove(items[position])
                } else {
                    items[position].Scanned = 0;
                }
            }
            doubleTap = true
            Handler(Looper.getMainLooper()).postDelayed({
                doubleTap = false
            }, 1500)
        }
    }

    fun addItem(item: SkanItem)
    {
        items.add(item);
        notifyItemInserted(items.size -1)
    }

    fun Exists(item : SkanItem) : Boolean {
        if (items.find {it.ItemName ==item.ItemName && it.Batch == item.Batch }!=null){
            return true }
            return false
    }

    fun update(item: SkanItem)
    {
        if (Exists(item)) {
          val pos =  items.indexOf(item)
            items[pos].Scanned += item.Quantity
          notifyItemChanged(pos)
        }
    }
    fun remove(item:SkanItem)
    {
        items.remove(item);
        notifyItemRemoved(items.indexOf(item))
    }
    override fun getItemCount(): Int {
        return items.size
    }
    }
