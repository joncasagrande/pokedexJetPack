package jonathan.com.techmeeting

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import jonathan.com.techmeeting.persistence.AppDatabase
import kotlinx.android.synthetic.main.pokemon_card.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "pokedex").allowMainThreadQueries().build()


        val util = Util().readJsonAndPersist(db, this)

        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        model.mutablePokemon.observe(this, Observer {
            pokemonName.text = it!!.name
            pokemonDescription.text = it.description
            //pokemonType.text = it.types.toString()
            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(pokemonPic);
        })

        model.loadPokemonById(db, 1)

        doSearch.setOnClickListener(View.OnClickListener {
            model.loadPokemonById(db, searchText.text.toString().toInt())
        })
    }
}
