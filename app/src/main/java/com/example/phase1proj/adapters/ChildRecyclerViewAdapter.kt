package com.example.phase1proj.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.phase1proj.R
import com.example.phase1proj.adapters.ChildRecyclerViewAdapter.MyViewHolder
import com.example.phase1proj.models.Vegetable
import com.example.phase1proj.views.ItemActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.child_card_view_list.view.*
import java.io.File


class ChildRecyclerViewAdapter(
        private val vegetableList: List<Vegetable>

) :
        RecyclerView.Adapter<MyViewHolder>() {
    private val storage: FirebaseStorage = Firebase.storage
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View

        val layoutInflater = LayoutInflater.from(parent.context)
        view = layoutInflater.inflate(R.layout.child_card_view_list, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var storageRef = storage.reference


        holder.vegetableTitle.text = vegetableList[position].name
        if (vegetableList[position].thumbnail!! == 999) {
            var imagesRef: StorageReference? = storageRef.child("images/" + vegetableList[position].url)
            val localFile = File.createTempFile("images", "jpg")
            if (imagesRef != null) {
                imagesRef.getFile(localFile).addOnSuccessListener {
                    // Local temp file has been created
                }.addOnFailureListener {
                    // Handle any errors
                }
            }
            Glide.with(holder.vegetableThumbnail.context).load(imagesRef).into(holder.vegetableThumbnail)
        } else {
            holder.vegetableThumbnail.setImageResource(vegetableList[position].thumbnail!!)
        }
        holder.rateVegetable.text = "$ " + vegetableList[position].price.toString()
        holder.weightVegetable.text = vegetableList[position].weight

        holder.textLayout.setOnClickListener {
            openActivity(holder, vegetableList[position])
        }

    }

    override fun getItemCount(): Int {
        return vegetableList.size
    }

    class MyViewHolder(itemsView: View) : ViewHolder(itemsView) {
        var vegetableTitle: TextView
        var vegetableThumbnail: ImageView
        var weightVegetable: TextView
        var rateVegetable: TextView


        var textLayout = itemsView.item

        init {
            rateVegetable = itemsView.findViewById(R.id.rate) as TextView
            weightVegetable = itemsView.findViewById(R.id.weight) as TextView
            vegetableTitle =
                    itemsView.findViewById<View>(R.id.vegetableTitle) as TextView
            vegetableThumbnail =
                    itemsView.findViewById<View>(R.id.VegetableThumbnail) as ImageView
        }
    }

    private fun openActivity(holder: ChildRecyclerViewAdapter.MyViewHolder, item: Vegetable) {

        val intent = Intent(holder.textLayout.context, ItemActivity::class.java)
        intent.putExtra("ItemDetails", item)
        ContextCompat.startActivity(holder.textLayout.context, intent, null)

    }

}