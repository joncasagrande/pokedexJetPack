package jonathan.com.techmeeting

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val util = Util().readJsonAndPersist(this)

        val model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        model.loadPokemon(1).observe(this, Observer {
            pokemonName.text = it!!.name
            pokemonDescription.text = it.description
            pokemonType.text = it.types.toString()
            Picasso.get().load(it.image).into(pokemonPic)
        })

    }
}
