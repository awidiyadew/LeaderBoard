package id.balistream.leaderboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_learder_board.*

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
                .addSnapshotListener({ snapshots, error ->
                    if (error != null) {
                        Log.d("TAG", error.message)
                        return@addSnapshotListener
                    }

                    val players = snapshots.map{
                        it.toObject(Player::class.java)
                    }
                    showPlayersPosition(players)
                })
    }

    private fun showPlayersPosition(players: List<Player>) {
        val adapter = recycler_view.adapter as PlayerAdapter
        adapter.addPlayers(players)
    }
}
