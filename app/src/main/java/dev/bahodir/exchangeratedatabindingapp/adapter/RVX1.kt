package dev.bahodir.exchangeratedatabindingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.bahodir.exchangeratedatabindingapp.databinding.VpLayoutBinding
import dev.bahodir.exchangeratedatabindingapp.users.Users

class RVX1(var context: Context, var list: List<Users>) : RecyclerView.Adapter<RVX1.VH>() {

    inner class VH(var binding: VpLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun onBind(users: Users, position: Int) {
            val split = users.date.split(" ")

            binding.date.text = split[0]
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
        return VH(VpLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}