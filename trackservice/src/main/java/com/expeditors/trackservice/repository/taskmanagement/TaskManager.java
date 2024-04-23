package com.expeditors.trackservice.repository.taskmanagement;

public interface TaskManager {

    void addTask(Task task);
//    void clearTaskQueue();
    boolean processTasks();
}


