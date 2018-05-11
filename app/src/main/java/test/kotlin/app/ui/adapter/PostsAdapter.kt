package test.kotlin.app.ui.adapter

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import test.kotlin.app.R
import test.kotlin.app.core.extension.OnPostItemClick
import test.kotlin.app.core.model.posts.PostModel

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class PostsAdapter(data: OrderedRealmCollection<PostModel>?, autoUpdate: Boolean)
    : RealmRecyclerViewAdapter<PostModel, PostsAdapter.ViewHolder>(data, autoUpdate) {

    var listener: OnPostItemClick? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)

        holder.title?.text = post?.title
        holder.content?.text = post?.body
        holder.itemView.setOnClickListener({ _ ->
            listener?.onItemClick(post!!)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.posts_adapter_item, parent, false))

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title = itemView?.findViewById<AppCompatTextView>(R.id.title)
        val content = itemView?.findViewById<AppCompatTextView>(R.id.content)
    }
}