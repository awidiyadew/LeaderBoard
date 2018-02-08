package id.balistream.leaderboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_player.view.*

class PlayerAdapter() : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private var players: MutableList<Player> = mutableListOf()

    override fun getItemCount(): Int = players.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    fun addPlayers(players: List<Player>) {
        this.players.apply {
            clear()
            addAll(players)
        }
        notifyDataSetChanged()
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(player: Player) {
            itemView.tv_name.text = player.name
            itemView.tv_score.text = player.score.toString()
        }
    }
}
