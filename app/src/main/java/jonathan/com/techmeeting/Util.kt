package jonathan.com.techmeeting

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonParser

class Util{

    val pokemons : MutableList<Pokemon> = arrayListOf()

    fun readJsonAndPersist(ctx: Context){
        val pokemonDeserializer : JsonDeserializer<Pokemon> = JsonDeserializer<Pokemon>{
            json, typeOfT, context ->

            val pokemonArray = json.asJsonObject

            val id = pokemonArray.get("national_id").asInt
            val name = pokemonArray.get("name").asString
            val description = pokemonArray.get("description").asString
            val image = pokemonArray.get("image_url").asString
            val arrayTypes = pokemonArray.get("types").asJsonArray
            val types : MutableList<String> = arrayListOf()
            arrayTypes.forEach { types.add(it.asString) }

            Pokemon(id,image, name, description, types)
        }

        val inputStream = ctx.resources.openRawResource(R.raw.pokemon)
        val jsonParser = JsonParser()



        val gsonBuilder = GsonBuilder().serializeNulls()
        gsonBuilder.registerTypeAdapter(Pokemon::class.java, pokemonDeserializer)
        val gson = gsonBuilder.create()
        val jElement = jsonParser.parse(inputStream.reader())

        for (i in 0..jElement.asJsonArray.size()-1) {
            pokemons.add( gson.fromJson(jElement.asJsonArray[i],Pokemon::class.java))
        }

    }


}