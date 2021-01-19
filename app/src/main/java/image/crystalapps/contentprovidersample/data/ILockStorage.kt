package image.crystalapps.contentprovidersample.data

import image.crystalapps.contentprovidersample.entities.LockType

interface ILockStorage {



    suspend fun insert(user :LockType)


//    suspend fun getUserType(index :Int ):LockType








}