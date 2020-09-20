package com.example.todo.db

import com.example.todo.model.entity.Todo

data class TodoDataBase(
        var todoLists: MutableList<Todo>?=null
){

    fun init(){
        this.todoLists = mutableListOf()
        println("[DEBUG] init")
    }
}