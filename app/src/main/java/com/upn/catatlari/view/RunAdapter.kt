package com.upn.catatlari.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upn.catatlari.databinding.ItemRunBinding
import com.upn.catatlari.model.RunEntity

class RunAdapter(private val onEditClick: (RunEntity) -> Unit,
                 private val onDeleteClick: (RunEntity) -> Unit
) : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    private var runList = mutableListOf<RunEntity>()

    fun setData(runItems: List<RunEntity>) {
        runList.clear()
        runList.addAll(runItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder =
        RunViewHolder(ItemRunBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) = holder.bind(runList[position])

    override fun getItemCount(): Int = runList.size

    inner class RunViewHolder(private val binding: ItemRunBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(run: RunEntity) {
            binding.txtRunDate.text = run.runDate
            binding.txtRunDistance.text = "${run.runDistance} M"
            binding.txtRunDuration.text = "${run.runDuration} menit"
            binding.btnEdit.setOnClickListener { onEditClick(run) }
            binding.btnDelete.setOnClickListener { onDeleteClick(run) }
        }
    }
}
