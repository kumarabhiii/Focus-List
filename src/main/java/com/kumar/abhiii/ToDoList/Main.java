package com.kumar.abhiii.todolist;
public class Main
{
public static void main(String[] args) 
{
ToDoList toDoList = new ToDoList();
Runtime.getRuntime().addShutdownHook(new Thread(() -> {
toDoList.save();
}));
}
}