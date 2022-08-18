package com.juanp.mvisample

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanp.mvisample.common.inflate
import com.juanp.mvisample.data.Company
import com.juanp.mvisample.databinding.ListItemBinding

class SelectCoursesAdapter(
    private val data: ArrayList<Company>,
    private val callback: (Company) -> Unit
) : RecyclerView.Adapter<SelectCoursesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position]) {
            callback(it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapCourses(courses: List<Company>) {
        if (data.isNotEmpty())
            data.clear()
        data.addAll(courses)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(company: Company, callback: (Company) -> Unit) {
            binding.courseTextView.text =
                " ${itemView.context.getString(R.string.name)}: ${company.name}, ${
                    itemView.context.getString(
                        R.string.address
                    )
                }: ${company.address}, ${itemView.context.getString(R.string.nit)}: ${company.nit}, ${
                    itemView.context.getString(
                        R.string.phone
                    )
                }: ${company.phone}"
            binding.courseTextView.setOnClickListener {
                callback(company)
            }
        }
    }
}