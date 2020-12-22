/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.backend

class Reference {
    var dbString:String? = null
    var versionReference:String? = null
    val disc = Disco()
    val references = arrayListOf("jdbc:mysql://25.49.100.193:4096/starnet", "2.8.1")
    constructor(){
        disc.createFolder("config")
        if (!disc.existsFile("config", "config.cfg")) {
            disc.writer("config", "config.cfg", references, true)
            val configs:ArrayList<String> = disc.read("config", "config.cfg")
            dbString = configs[0]
            versionReference = configs[1]
        } else {
            disc.writer("config", "config.cfg", references, false)
            val configs:ArrayList<String> = disc.read("config", "config.cfg")
            dbString = configs[0]
            versionReference = configs[1]
        }

    }

    fun editarDBString(Db:String){
        val newReference = arrayListOf<String>(Db, references[1])
        disc.writer("config", "config.cfg", newReference, true);
    }
}