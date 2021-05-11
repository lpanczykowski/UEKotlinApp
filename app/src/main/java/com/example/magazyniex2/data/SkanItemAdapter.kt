package com.example.magazyniex2.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magazyniex2.R
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
            tvIndex.text = curItem.Indeks
            tvAmount.text = curItem.Amount.toString()
            tvJM.text = curItem.JM
            tvBatchNumber.text = curItem.BatchNumber
        }
    }

    fun addItem(item: SkanItem)
    {
        items.add(item);
        notifyItemInserted(items.size -1)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    }
