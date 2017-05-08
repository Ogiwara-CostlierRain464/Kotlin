package jp.ogiwara.test.kotlin.todo

import android.support.v7.widget.RecyclerView
import trikita.anvil.RenderableRecyclerViewAdapter

class BansaRenderableRecyclerViewAdapter<M,VM>(
        val mapModelToViewModel: (M) -> VM,
        val render: (VM) -> Unit,
        val getId: (List<M>,Int) -> Long = {m,i-> RecyclerView.NO_ID },
        val hasStableIds: Boolean = false
) : RenderableRecyclerViewAdapter(){

    var models: List<M> = listOf()

    init {
        setHasStableIds(hasStableIds)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(pos: Int): Long {
        return getId(models,pos)
    }

    override fun view(holder: RecyclerView.ViewHolder) {
        render(mapModelToViewModel(models[holder.adapterPosition]))
    }

    fun update(newModels: List<M>): BansaRenderableRecyclerViewAdapter<M,VM>{

        if((models == newModels).not()){
            this.models = newModels
            notifyDataSetChanged()
        }

        return this
    }
}