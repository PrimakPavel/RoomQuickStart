package com.primakpavel.roomsample

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.primakpavel.roomsample.db.AppDatabase
import com.primakpavel.roomsample.db.User

class MainActivity() : AppCompatActivity() {
    private val mNameInput: EditText? by lazy {
        findViewById<EditText?>(R.id.name_input)
    }

    private val mAddBtn: Button? by lazy {
        findViewById<Button?>(R.id.add_btn)
    }

    private val mUsersStuff: TextView? by lazy {
        findViewById<TextView?>(R.id.all_users_stuff)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val executor = (application as App?)?.appExecutor?.diskIO()

        mAddBtn?.setOnClickListener {
            val name = mNameInput?.text?.toString()
            name?.let { name ->
                executor?.execute {
                    AppDatabase.getInstance(this)?.userDao?.insert(User(name,20))
                }
            }
        }
        val usersLiveData = AppDatabase.getInstance(this)?.userDao?.getAllUsers()
        usersLiveData?.observe(this, Observer { usersList ->
            val usersStr = StringBuilder("")
            usersList?.forEach {
                usersStr.append(it.toString())
            }
            mUsersStuff?.text = usersStr.toString()
        })
    }
}
