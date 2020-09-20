package com.example.todo.repository

import com.example.todo.db.TodoDataBase
import com.example.todo.exception.DuplicateKeyException
import com.example.todo.model.entity.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    lateinit var todoDataBase: TodoDataBase

    @Autowired
    lateinit var todoRepository: TodoRepository

    @Test
    fun initTest(){
        Assertions.assertNotNull(todoRepository)
        Assertions.assertNotNull(todoDataBase.todoLists)
    }

    @Test
    fun saveOneAndFindOneTest(){
        // given
        todoDataBase.init()

        val todo = Todo().apply {
            this.name = "테스트1"
            this.description = "테스트1"
            this.schedule = LocalDateTime.now()
        }

        // when
        val saveDto = todoRepository.save(todo)
        val findOne = todoRepository.findOne(saveDto.index)

        // then
        Assertions.assertEquals(saveDto, findOne)
    }


    @Test
    fun saveAllAndFindAllTest(){
        // given
        todoDataBase.init()
        val now = LocalDateTime.now()

        val todoList = mutableListOf(
                Todo().apply {
                    this.name = "테스트1"
                    this.description = "테스트1"
                    this.schedule = now
                },
                Todo().apply {
                    this.name = "테스트2"
                    this.description = "테스트2"
                    this.schedule = now.plusSeconds(1)
                },
                Todo().apply {
                    this.name = "테스트2"
                    this.description = "테스트2"
                    this.schedule = now.plusSeconds(2)
                }
        )
        todoRepository.saveAll(todoList)


        //when
        val result = todoRepository.findAll()


        //then
        Assertions.assertEquals(todoList.size, result.size)
        result.forEach {
            Assertions.assertNotNull(it.index)
        }
    }

    @Test
    fun updateTest(){
        // given
        todoDataBase.init()

        val todo = Todo().apply {
            this.name = "테스트1"
            this.description = "테스트1"
            this.schedule = LocalDateTime.now()
        }
        val saveDto = todoRepository.save(todo)
        saveDto.apply {
            this.name = "업데이트 테스트"
            this.description = "업데이트 테스트"
        }

        // when
        val updateDto = todoRepository.save(todo)

        Assertions.assertEquals(saveDto.index, updateDto.index)
        Assertions.assertEquals(saveDto.name, updateDto.name)
        Assertions.assertEquals(saveDto.description, updateDto.description)
    }

    @Test
    fun deleteTest(){
        // given
        todoDataBase.init()

        val todo = Todo().apply {
            this.name = "테스트1"
            this.description = "테스트1"
        }
        val saveDto = todoRepository.save(todo)

        // when
        todoRepository.delete(saveDto.index)
        val result = todoRepository.findAll()


        Assertions.assertEquals(0, result.size)
    }

    @Test
    fun duplicateTest(){
        // given
        todoDataBase.init()

        val todo = Todo().apply {
            this.name = "테스트1"
            this.description = "테스트1"
            this.schedule = LocalDateTime.now()
        }
        val saveDto = todoRepository.save(todo)
        saveDto.apply {
            this.index = null
        }

        // when
        val exception = Assertions.assertThrows(DuplicateKeyException::class.java){
            todoRepository.save(saveDto)
        }

        // then
        Assertions.assertNotNull(exception)
        Assertions.assertEquals(DuplicateKeyException::class.java, exception::class.java)

    }

}