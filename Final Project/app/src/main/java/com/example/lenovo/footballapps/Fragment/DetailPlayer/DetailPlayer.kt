package com.example.lenovo.footballapps.Fragment.DetailPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lenovo.footballapps.Model.Player.Player
import com.example.lenovo.footballapps.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*


class DetailPlayer : AppCompatActivity() {

    companion object {
        val POSITION_EXTRA = "position_extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val detail = intent.getParcelableExtra<Player>(POSITION_EXTRA)

        tv_player_detail_name.text = detail.strPlayer
        Picasso.get().load(detail.strThumb).into(iv_player_detail_photo)
        tv_player_detail_nations.text = detail.strNationality
        tv_player_detail_deskripsi.text = detail.strDescriptionEN


    }
}
