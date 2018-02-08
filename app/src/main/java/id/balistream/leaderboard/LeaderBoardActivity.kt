package id.balistream.leaderboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class LeaderBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learder_board)
        val db = FirebaseFirestore.getInstance()
        db.collection("players")
                .addSnapshotListener({ values, error ->
                    if (error != null) {
                        Log.d("TAG", error.message)
                        return@addSnapshotListener
                    }

                    values.forEach {
                        val player = it.toObject(Player::class.java)
                        Log.d("TAG", player.toString())
                    }

                })
    }
}