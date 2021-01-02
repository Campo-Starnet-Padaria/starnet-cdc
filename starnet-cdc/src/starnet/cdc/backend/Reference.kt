/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.backend

class Reference {
    var dbString:String? = null
    var versionReference:String? = "3.2"
    var dbuser:String? = null
    var dbpass:String? = null
    private val disc = Disco()
    private val references = arrayListOf("jdbc:mysql://25.49.100.193:4096/starnet", "starnetcdc", "zLatPeXfFlY62rH1")

    init {
        if (!disc.existsFile("config", "config.cfg")) {
            disc.createFolder("config")
            disc.writer("config", "config.cfg", references, true)
            val configs:ArrayList<String> = disc.read("config", "config.cfg")
            dbString = configs[0]
            dbuser = configs[1]
            dbpass = configs[2]
        } else {
            disc.writer("config", "config.cfg", references, false)
            val configs:ArrayList<String> = disc.read("config", "config.cfg")
            dbString = configs[0]
            dbuser = configs[1]
            dbpass = configs[2]
        }
    }

    fun editarDBString(Db:String){
        val newReference = arrayListOf<String>(Db, references.toString())
        disc.writer("config", "config.cfg", newReference, true);
    }
}