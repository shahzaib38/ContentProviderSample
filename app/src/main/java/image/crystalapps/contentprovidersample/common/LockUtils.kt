package image.crystalapps.contentprovidersample.common

import image.crystalapps.contentprovidersample.entities.LockType

object LockUtils {



    fun getLockUtils():ArrayList<LockType>{
        val arrayList = ArrayList<LockType>()
        arrayList.add(LockType(0,"None","No Security",false))
        arrayList.add(LockType(0,"Pattern","Medium Security",false))
        arrayList.add(LockType(0,"PIN","Medium to  High",false))
        return arrayList }


}