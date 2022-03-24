package com.example.permissionrequests

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

class CriticalPermissionCaller (private val activity: Activity,private val context : Context, private val requestCode : Int
,private vararg var IntendedPermission : String)  {

    private val RequestStatueHasMap : HashMap<String,Boolean>

    init {
        RequestStatueHasMap = hashMapOf()
        PermissionStatueCheck()
        RequestCaller()
    }

     fun PermissionStatueCheck(){
        IntendedPermission.forEach {
            RequestStatueHasMap[it] =
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun RequestCaller(){
        val Requestlist : ArrayList<String> = arrayListOf()

        RequestStatueHasMap.forEach {
            if(!it.value){
                Requestlist.add(it.key)
            }
        }
        println(Requestlist)
        requestPermissions(activity,Requestlist.toTypedArray(),requestCode)
    }

}