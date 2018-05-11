package test.kotlin.app.ui.adapter

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import test.kotlin.app.R
import test.kotlin.app.core.model.posts.CommentModel

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class CommentsAdapter(data: OrderedRealmCollection<CommentModel>?, autoUpdate: Boolean) :
        RealmRecyclerViewAdapter<CommentModel, CommentsAdapter.ViewHolder>(data, autoUpdate) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = getItem(position)?.name
        holder.email?.text = getItem(position)?.email
        holder.content?.text = getItem(position)?.body
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comments_adapter_item, parent, false))

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title = itemView?.findViewById<AppCompatTextView>(R.id.title)
        val content = itemView?.findViewById<AppCompatTextView>(R.id.content)
        val email = itemView?.findViewById<AppCompatTextView>(R.id.email)
    }
}