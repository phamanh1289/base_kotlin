package com.appcyclone.basekotlin.data.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.appcyclone.basekotlin.others.constant.Constants
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created by sunil on 5/30/2017.
 */
class DbHelper(var context : Context = MainApplication.instance) :
        ManagedSQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.createTable(FriendsTable.TABLE_NAME, true, FriendsTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT, FriendsTable.FRIEND_ID to TEXT  ,
                FriendsTable.NAME to TEXT, FriendsTable.EMAIL to TEXT
                )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}