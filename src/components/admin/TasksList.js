import React, { useState } from "react";

function TasksList({ tasks, onAddTask }) {
  const [showAddTaskModal, setShowAddTaskModal] = useState(false);
  const [newTask, setNewTask] = useState({
    date: "",
    title: "",
    status: "Pending",
  });

  const handleAddTask = () => {
    setShowAddTaskModal(true);
  };

  const handleSaveTask = () => {
    if (newTask.date && newTask.title) {
      onAddTask(newTask);
      setNewTask({ date: "", title: "", status: "Pending" });
      setShowAddTaskModal(false);
    } else {
      alert("Please fill in all fields.");
    }
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow">
      <div className="flex justify-between items-center mb-6">
        <h3 className="text-lg font-semibold">Tasks</h3>
        <button onClick={handleAddTask} className="text-blue-500">
          +
        </button>
      </div>
      <div className="space-y-4">
        {tasks.map((task, index) => (
          <div key={index} className="flex items-center justify-between">
            <div>
              <div className="text-sm text-gray-500">{task.date}</div>
              <div>{task.title}</div>
            </div>
            <div
              className={`px-3 py-1 rounded-full text-sm ${
                task.status === "Completed"
                  ? "bg-purple-100 text-purple-800"
                  : task.status === "In Progress"
                  ? "bg-blue-100 text-blue-800"
                  : "bg-gray-100 text-gray-800"
              }`}
            >
              {task.status}
            </div>
          </div>
        ))}
      </div>

      {/* Modal for Adding Task */}
      {showAddTaskModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h2 className="text-lg font-semibold mb-4">Add New Task</h2>
            <div className="space-y-4">
              <input
                type="date"
                value={newTask.date}
                onChange={(e) =>
                  setNewTask({ ...newTask, date: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <input
                type="text"
                placeholder="Task Title"
                value={newTask.title}
                onChange={(e) =>
                  setNewTask({ ...newTask, title: e.target.value })
                }
                className="w-full p-2 border rounded"
              />
              <select
                value={newTask.status}
                onChange={(e) =>
                  setNewTask({ ...newTask, status: e.target.value })
                }
                className="w-full p-2 border rounded"
              >
                <option value="Pending">Pending</option>
                <option value="In Progress">In Progress</option>
                <option value="Completed">Completed</option>
              </select>
            </div>
            <div className="flex gap-4 mt-4">
              <button
                onClick={handleSaveTask}
                className="px-4 py-2 bg-blue-500 text-white rounded"
              >
                Save
              </button>
              <button
                onClick={() => setShowAddTaskModal(false)}
                className="px-4 py-2 border rounded"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default TasksList;