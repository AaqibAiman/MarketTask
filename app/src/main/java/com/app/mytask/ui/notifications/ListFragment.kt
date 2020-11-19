package com.app.mytask.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mytask.R
import com.app.mytask.model.UserResponse
import com.app.mytask.room.CommentDataBase
import com.app.mytask.room.Comments
import com.app.mytask.service.APIClient
import com.app.mytask.service.APIInterface
import com.app.mytask.ui.IHomeInterface
import com.app.mytask.ui.detailFragment.DetailsFragment
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment(), CustomAdapter.onClickItem {

    private lateinit var adapter: CustomAdapter
    private var apiInterface: APIInterface? = null
    var nextId = 0
    val userResponseList = ArrayList<UserResponse>()
    val userList = ObservableArrayList<UserResponse>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var iHomeInterface: IHomeInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            iHomeInterface = context as IHomeInterface
        } catch (e: ClassCastException) {
            throw ClassCastException("${e.message}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_list, container,
            false
        )
        initRecyclerView(view)
        initAdapter()
        apiInterface = APIClient.client?.create(APIInterface::class.java)
        gitHubRepositoryAPICall(nextId)


        return view
    }

    private fun initRecyclerView(view: View) {
        //getting recyclerview from xml
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        //adding a layout manager
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    //findLastCompletelyVisibleItemPosition() returns position of last fully visible view.
                    ////It checks, fully visible view is the last one.
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == userResponseList.size - 1) {
                        gitHubRepositoryAPICall(nextId)
                        //isLoading = true
                        progress_bar?.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun initAdapter() {
        adapter = CustomAdapter(userList, requireContext(), this@ListFragment)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

    }

    private fun gitHubRepositoryAPICall(id: Int) {
        val call: Call<List<UserResponse>> = apiInterface!!.doGetUserList(id)
        call.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                progress_bar?.visibility = View.GONE
                if (response.isSuccessful) {
                    val userResponse = response.body()

                    if (!userResponse.isNullOrEmpty()) {
                        nextId = userResponse.last().id ?: 0
                        userResponseList.addAll(userResponse)
                        userList.addAll(userResponse)
                        adapter.notifyDataSetChanged()
                    }


                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable?) {
                call.cancel()
            }
        })
    }

    private fun addComment(userResponse: UserResponse, comment: String) {
        var result = 0L
        if (comment.isBlank()) {
            putToast("Please add comment")
        } else {
            GlobalScope.launch {
                result = CommentDataBase.getDatabase(requireContext()).commentDao()
                    .insert(Comments(0, userResponse.nodeId!!, comment))
            }
            if (result > 0) putToast("Comment added for ${userResponse.name}")
        }
    }


    private fun putToast(message: String) {
        kotlin.run {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    override fun clickItem(data: UserResponse) {

        val bundle = bundleOf(
            "userName" to data.name, "description" to data.description, "nodeId" to data.nodeId
        )
        iHomeInterface.startFragment(DetailsFragment(), bundle, "DetailsFragment")
        //Navigation.findNavController(requireView()).navigate(R.id.detail_fragment , bundle);

    }

    override fun comments(data: UserResponse, comments: String) {
        addComment(data, comments)
    }


}