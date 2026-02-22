import React, { useEffect, useState } from "react";
import { getTasks, deleteTask, toggleTask } from "../api/taskApi";
import { useNavigate } from "react-router-dom";
import { Task } from "../types/Task";

const TaskListPage: React.FC = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const navigate = useNavigate();

  const load = async () => {
    const res = await getTasks();
    setTasks(res.data.content);
  };

  useEffect(() => {
    load();
  }, []);

  return (
    <div>
      <h2>Task List</h2>

      <button onClick={() => navigate("/create")}>
        Create Task
      </button>

      {tasks.map((task) => (
        <div
          key={task.id}
          style={{
            border: "1px solid gray",
            margin: "10px",
            padding: "10px"
          }}
        >
          <h3>{task.title}</h3>
          <p>{task.description}</p>
          <p>Status: {task.isCompleted ? "Completed" : "Pending"}</p>

          <button onClick={() => toggleTask(task.id!).then(load)}>
            Toggle
          </button>

          <button onClick={() => navigate(`/edit/${task.id}`)}>
            Edit
          </button>

          <button onClick={() => deleteTask(task.id!).then(load)}>
            Delete
          </button>
        </div>
      ))}
    </div>
  );
};

export default TaskListPage;