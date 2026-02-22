import React from "react";
import TaskForm from "../components/TaskForm";
import { createTask } from "../api/taskApi";
import { useNavigate } from "react-router-dom";
import { Task } from "../types/Task";

const CreateTaskPage = () => {
  const navigate = useNavigate();

  const handleSubmit = async (task: Task) => {
    await createTask(task);
    navigate("/");
  };

  return (
    <div>
      <h2>Create Task</h2>
      <TaskForm onSubmit={handleSubmit} />
    </div>
  );
};

export default CreateTaskPage;