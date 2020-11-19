package com.app.mytask.ui.detailFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mytask.R
import com.app.mytask.room.CommentDataBase
import kotlinx.coroutines.*

class DetailsFragment : Fragment() {

    //  var result : LiveData<Comments>? = null
    var nodeID: Any? = null
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_detail, container,
            false
        )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val nameView = view?.findViewById<TextView>(R.id.nameText)
        val descName = view?.findViewById<TextView>(R.id.descNameText)

        val userName = arguments?.get("userName")
        val description = arguments?.get("description")
        nodeID = arguments?.get("nodeId")

        nameView!!.text = "Name : $userName"
        descName!!.text = "Description : $description"

        //getting recyclerview from xml
         recyclerView = requireView().findViewById(R.id.comments_RecyclerView) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        Log.d("nodeID :---->", "$nodeID.")

        getComments()

       /// Log.d("Result :---->", "$comments.")

        /* result!!.observe(viewLifecycleOwner , Observer {
             Log.d("Comments Of Result" , "${it.comment}")
         })*/

    }

    private fun getComments()  {
        var comments: List<String>? = null
     val cmt =  GlobalScope.async {
            launch(Dispatchers.Main) {
                val queue = async(Dispatchers.IO) {
                    return@async CommentDataBase.getDatabase(requireContext()).commentDao()
                        .getCommentById((nodeID as String))
                }
                comments = queue.await()
                updateAdapter(comments)
            }
        }
        //return comments
    }

    private fun updateAdapter(comments: List<String>?) {
        if (!comments.isNullOrEmpty()) {
            //creating our adapter
            val adapter = CommentAdapter(comments as ArrayList<String>)
            //now adding the adapter to recyclerview
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

}