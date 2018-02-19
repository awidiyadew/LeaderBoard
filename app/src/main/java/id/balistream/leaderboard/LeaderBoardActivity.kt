package id.balistream.leaderboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_learder_board.*
import kotlinx.android.synthetic.main.item_player.view.*

class LeaderBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learder_board)
        setupRecyclerView()
        loadPlayers()
    }

    private fun setupRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PlayerAdapter()
            setHasFixedSize(true)
        }
    }

    private fun loadPlayers() {
        val db = FirebaseFirestore.getInstance()
        db.collection("players")
                .orderBy("score", Query.Direction.DESCENDING)
                .addSnapshotListener({ snapshots, error ->
                    if (error != null) {
                        Log.d("TAG", error.message)
                        return@addSnapshotListener
                    }

                    val players = snapshots.map{
                        it.toObject(Player::class.java)
                    }
                    val champions = players.take(3)
                    showPlayersPosition(players)
                    showChampions(champions)
                })
    }

    private fun showPlayersPosition(players: List<Player>) {
        val adapter = recycler_view.adapter as PlayerAdapter
        adapter.addPlayers(players)
    }

    private fun showChampions(championPlayers: List<Player>) {
        iv_champion1.loadImg(championPlayers[0].photo)
        iv_champion2.loadImg(championPlayers[1].photo)
        iv_champion3.loadImg(championPlayers[2].photo)
    }
}
