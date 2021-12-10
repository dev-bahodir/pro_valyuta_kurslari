package dev.bahodir.exchangeratedatabindingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.bahodir.exchangeratedatabindingapp.databinding.X2RvItemBinding
import dev.bahodir.exchangeratedatabindingapp.users.Users

class RVX2(var context: Context) : ListAdapter<Users, RVX2.VH>(DU()) {

    class DU : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }


    }

    inner class VH(var binding: X2RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(users: Users, position: Int) {
            binding.countryName.text = users.code
            Glide.with(context).load(users.image).into(binding.flag)

            if (users.nbu_buy_price == "") {
                binding.sotibOlish.text = "Mavjud emas"
                binding.sotish.text = "Mavjud emas"
            } else {
                binding.sotibOlish.text = users.nbu_buy_price
                binding.sotish.text = users.nbu_cell_price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(X2RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), position)
    }
}