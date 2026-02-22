import React, { useEffect, useState } from "react";
import TaskForm from "../components/TaskForm";
import { getTaskById, updateTask } from "../api/taskApi";
import { useNavigate, useParams } from "react-router-dom";
import { Task } from "../types/Task";

const EditTaskPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [task, setTask] = useState<Task | null>(null);

  useEffect(() => {
    if (id) {
      getTaskById(Number(id)).then((res) => setTask(res.data));
    }
  }, [id]);

  const handleSubmit = async (updatedTask: Task) => {
    if (id) {
      await updateTask(Number(id), updatedTask);
      navigate("/");
    }
  };

  if (!task) return <div>Loading...</div>;

  return (
    <div>
      <h2>Edit Task</h2>
      <TaskForm initialData={task} onSubmit={handleSubmit} />
    </div>
  );
};

export default EditTaskPage;