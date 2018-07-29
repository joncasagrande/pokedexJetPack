package jonathan.com.techmeeting

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    val mutablePokemon : MutableLiveData<Pokemon> = MutableLiveData()

    fun loadPokemon(id: Int): MutableLiveData<Pokemon>{

        mutablePokemon.postValue(loadPokemonById(id))
        mutablePokemon.value
        return mutablePokemon
    }

    private fun loadPokemonById(id : Int ):Pokemon{
        return Util().pokemons[id]
    }
}