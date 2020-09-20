package com.example.todo.repository

import com.example.todo.exception.DuplicateKeyException
import com.example.todo.model.entity.Todo
import com.example.todo.db.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepository {

    @Autowired
    lateinit var todoDataBase: TodoDataBase

    @Throws(DuplicateKeyException::class)
    fun save(todo: Todo): Todo {
        if(todo.index == null && isDuplicate(todo)){
            throw DuplicateKeyException()
        }

        val result = insertOrUpDate(todo)
        println("[DEBUG] [save] : ${result}]")
        return result
    }

    @Throws(DuplicateKeyException::class)
    fun saveAll(todo: MutableList<Todo>): MutableList<Todo> {
        return todo.map {
            save(it)
        }.toMutableList()
    }

    fun findOne(index: Int?) : Todo? {
        println("[DEBUG] [findOne] Index : ${index}]")
        val todo = todoDataBase.todoLists?.find { it.index?.equals(index)?:false }
        println("[DEBUG] [findOne] Todo : ${todo}]")
        return todo
    }

    fun findAll(): MutableList<Todo> {
        return (todoDataBase.todoLists?: mutableListOf()).apply {
            println("[DEBUG] [findAll] SIZE : ${this.size}]")
        }
    }

    fun insertOrUpDate(newTodo: Todo): Todo {
        val old = findOne(newTodo.index)

        val insertOrUpdateTodo =  old?.let {oldTodo ->
            oldTodo.apply {
                this.name = newTodo.name
                this.description = newTodo.description
                this.updatedAt = LocalDateTime.now()
            }

            oldTodo
        }?: kotlin.run {
            todoInit(newTodo)
            todoDataBase.todoLists?.add(newTodo)

            newTodo
        }

        println("[DEBUG] [insertOrUpDate] : ${insertOrUpdateTodo}]")
        return insertOrUpdateTodo
    }

    fun delete(index: Int?){
        val deleteTodo = index?.let { todoDataBase.todoLists?.removeAt(it) }
        println("[DEBUG] [delete] : ${deleteTodo}]")
    }

    private fun isDuplicate(todo: Todo) : Boolean{
        val duplicateCount = todoDataBase.todoLists?.filter {
            it.schedule?.isEqual(todo.schedule)?:false
        }?.count()?:0

        println("[DEBUG] [isDuplicate Count] : ${duplicateCount}]")
        return duplicateCount > 0
    }

    private fun todoInit(todo: Todo){
        val index = todoDataBase.todoLists?.size?:0

        todo.apply {
            this.index = index
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }

        println("[DEBUG] [todoInit] : ${todo}]")
    }
}