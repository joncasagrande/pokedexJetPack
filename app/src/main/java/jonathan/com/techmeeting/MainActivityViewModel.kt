package jonathan.com.techmeeting

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import jonathan.com.techmeeting.persistence.AppDatabase
import jonathan.com.techmeeting.persistence.Pokemon

class MainActivityViewModel() : ViewModel(){
    val  mutablePokemon : MutableLiveData<Pokemon> = MutableLiveData()


    fun loadPokemonById(db: AppDatabase,id : Int ) {
        Thread.sleep(1000)
        mutablePokemon.value = db.pokemonDAO().loadAllByIds(id)
    }
}