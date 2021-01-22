package image.crystalapps.contentprovidersample.entities


object SettingUtils {



    fun getSettingList():ArrayList<Setting>{
        val arrayList =ArrayList<Setting>()
         val itemSettings = ArrayList<SettingItems>()
        itemSettings.add(SettingItems(0,"Lock Screen" ,null))
        itemSettings.add(SettingItems(1,"Privacy Policy" ,null))
        itemSettings.add(SettingItems(2,"Share" ,null))
        arrayList.add(Setting(10 ,"Setting" ,itemSettings))
        return arrayList
    }






}