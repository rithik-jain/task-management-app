import React, { useState } from "react";
import { Task } from "../types/Task";

interface Props {
  initialData?: Task;
  onSubmit: (task: Task) => void;
}

const TaskForm: React.FC<Props> = ({ initialData, onSubmit }) => {
  const [title, setTitle] = useState(initialData?.title || "");
  const [description, setDescription] = useState(initialData?.description || "");
  const [dueDate, setDueDate] = useState(initialData?.dueDate || "");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    onSubmit({
      id: initialData?.id,
      title,
      description,
      dueDate,
      isCompleted: initialData?.isCompleted || false,
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Title *</label>
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
          maxLength={100}
        />
      </div>

      <div>
        <label>Description</label>
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </div>

      <div>
        <label>Due Date</label>
        <input
          type="datetime-local"
          value={dueDate}
          onChange={(e) => setDueDate(e.target.value)}
        />
      </div>

      <button type="submit">Save</button>
    </form>
  );
};

export default TaskForm;