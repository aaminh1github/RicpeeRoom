package com.example.notesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.MainActivity
import com.example.notesapp.data.Ricpee
import com.example.notesapp.databinding.NoteRowBinding

class RicpeeAdapter(private val activity: MainActivity): RecyclerView.Adapter<RicpeeAdapter.ItemViewHolder>() {
    private var Ricpees = emptyList<Ricpee>()

    class ItemViewHolder(val binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RicpeeAdapter.ItemViewHolder {
        return ItemViewHolder(
            NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val Ricpee = Ricpees[position]

        holder.binding.apply {
            tvName.text = Ricpee.RText
            tvIns.text=Ricpee.insText
            tvAuth.text="by ${Ricpee.AuthText}"

            ibEditNote.setOnClickListener {
                //activity.raiseDialog(Ricpee.id)
                llTv.isGone=true

                llEd.isVisible=true
                edName.setText( tvName.text)
               edIns.setText(tvIns.text)
                edAuth.setText(tvAuth.text)
                btUpd.setOnClickListener {  activity.mainViewModel.editRicpee(Ricpee.id,edName.text.toString(),edIns.text.toString(),edAuth.text.toString())
                    llEd.isGone=true

                    llTv.isVisible=true
                }
            }
            ibDeleteNote.setOnClickListener {
                activity.mainViewModel.deleteRicpee(Ricpee.id)
            }
        }
    }

    override fun getItemCount() = Ricpees.size

    fun update(ricpees: List<Ricpee>){
        println("UPDATING DATA")
        this.Ricpees = ricpees
        notifyDataSetChanged()
    }
}
