package com.mugdha.notepadApplication.adapterFiles

object DataObject {
    var listdata = mutableListOf<CardInfo>()
    var listdata2 = mutableListOf<UserInfo>()

    fun setData(title: String, priority: String) {
        listdata.add(CardInfo(title, priority))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll() {
        listdata.clear()
    }

    fun getData(pos: Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos: Int) {
        listdata.removeAt(pos)
    }

    fun updateData(pos: Int, title: String, priority: String) {
        listdata[pos].title = title
        listdata[pos].priority = priority
    }

    fun setDataInTextView(name: String, phone: String) {
        listdata2.add(UserInfo(name, phone))
    }

    fun getProfileData(pos: Int): UserInfo {
        return listdata2[pos]
    }

}