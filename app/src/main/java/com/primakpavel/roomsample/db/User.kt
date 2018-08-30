package com.primakpavel.roomsample.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
class User(val name: String, val age: Int?) {
    @PrimaryKey(autoGenerate = true)
    var id = 0 //@PrimaryKey field (if it’s non primitive, should be annotated with @NonNull)

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\''.toString() +
                ", age='" + age + '\''.toString() +
                '}'.toString() + "\n"
    }
}
