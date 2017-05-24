package jp.ogiwara.kotlin.todo2.view.adapter

import android.support.v7.widget.RecyclerView
import trikita.anvil.RenderableRecyclerViewAdapter

//RecyclerView用のAdapter
//M : store
//VM : TodoModelView
class BansaRenderableRecyclerViewAdapter<M,VM>(
        val mapModelToViewModel: () -> VM,
        val render: (VM) -> Unit,
        val getId: (List<M>,Int) -> Long = {m,i -> RecyclerView.NO_ID},
        val hasStableIds: Boolean = false
): RenderableRecyclerViewAdapter() {

    var models: List<M> = listOf()

    init {
        setHasStableIds(hasStableIds)
    }

    override fun getItemCount() = models.size

    override fun getItemId(pos: Int) = getId(models,pos)

    override fun view(holder: RecyclerView.ViewHolder) =
            render(mapModelToViewModel())

    fun update(newModels: List<M>): BansaRenderableRecyclerViewAdapter<M,VM>{
        if (models != newModels){
            this.models = newModels
            notifyDataSetChanged()
        }
        return this
    }
}