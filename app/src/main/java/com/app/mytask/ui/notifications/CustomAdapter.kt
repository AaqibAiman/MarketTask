package com.app.mytask.ui.notifications

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.app.mytask.R
import com.app.mytask.model.UserResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items.view.*


class CustomAdapter(var userList: ArrayList<UserResponse>, var context: Context ,
                    val listener : onClickItem ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])

        holder.itemView.setOnClickListener {
            listener.clickItem(userList[position])
        }

        holder.itemView.btn_send.setOnClickListener {
            Log.d("TAGGG" , holder.itemView.editTextComment.text.toString())
          listener.comments(userList[position] , holder.itemView.editTextComment.text.toString())
        }
    }

    //This method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: UserResponse) {
            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            val textViewAddress  = itemView.findViewById(R.id.textViewDescription) as TextView
            val imageUser  = itemView.findViewById(R.id.imgUser) as ImageView
            val editTextComment  = itemView.findViewById(R.id.editTextComment) as EditText
            val buttonSend  = itemView.findViewById(R.id.btn_send) as ImageButton

            textViewName.text = "Name : ${user.name}"
            textViewAddress.text = "Description : ${user.description}"

            Glide
                .with(this.itemView)
                .load(user.owner!!.avatarUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageUser)
        }
    }


    interface onClickItem {
        fun clickItem(data : UserResponse)

        fun comments(data : UserResponse , comments : String)
    }
}