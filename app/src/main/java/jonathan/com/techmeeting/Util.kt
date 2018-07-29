package jonathan.com.techmeeting

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonParser
import jonathan.com.techmeeting.persistence.Pokemon
import jonathan.com.techmeeting.persistence.AppDatabase
import android.arch.persistence.room.Room
import jonathan.com.techmeeting.R.raw.pokemon


class Util{

    fun readJsonAndPersist(db : AppDatabase, ctx: Context){
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

            Pokemon(id, image, name, description)
        }

        if(db.pokemonDAO().getAll().size == 0){
            val inputStream = ctx.resources.openRawResource(R.raw.pokemon)
            val jsonParser = JsonParser()

            val gsonBuilder = GsonBuilder().serializeNulls()
            gsonBuilder.registerTypeAdapter(Pokemon::class.java, pokemonDeserializer)
            val gson = gsonBuilder.create()
            val jElement = jsonParser.parse(inputStream.reader())



            for (i in 0..jElement.asJsonArray.size()-1) {
                db.pokemonDAO().insert(gson.fromJson(jElement.asJsonArray[i], Pokemon::class.java))
            }
        }
    }


}