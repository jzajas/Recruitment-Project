import { useState } from "react";
import { API_URL } from "../config/api";
import axios from "axios";

export function GetUser() {
  const [userId, setUserId] = useState("");
  const [user, setUser] = useState(null);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [editing, setEditing] = useState(false);
  const [editForm, setEditForm] = useState({
    firstname: "",
    surname: "",
    balance: "",
  });

  const getUser = async () => {
    setError("");
    setSuccess("");
    setEditing(false);
    setUser(null);

    try {
      const response = await axios.get(
        `${API_URL}/users/${userId}`
      );
      setUser(response.data);
    } catch (err) {
      setError(err.response?.data?.message || "User not found");
    }
  };

  const deleteUser = async () => {
    try {
      await axios.delete(`${API_URL}/users/${userId}`);
      setUser(null);
      setSuccess("User deleted");
    } catch {
      setError("Error deleting user");
    }
  };

  const startEditing = () => {
    setEditForm({
      firstname: user.firstname,
      surname: user.surname,
      balance: user.balance,
    });
    setEditing(true);
    setError("");
    setSuccess("");
  };

  const handleEditChange = (e) => {
    setEditForm({
      ...editForm,
      [e.target.name]: e.target.value,
    });
  };

  const saveEdit = async () => {
    try {
      const response = await axios.put(
        `${API_URL}/users/${userId}`,
        editForm
      );
      setUser(response.data);
      setEditing(false);
      setSuccess("User updated successfully");
    } catch (err) {
      setError(err.response?.data?.message || "Error updating user");
    }
  };

  const cancelEdit = () => {
    setEditing(false);
    setError("");
    setSuccess("");
  };

  return (
    <div>
      <h2>Get User</h2>

      <input
        placeholder="User ID"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
      />
      <button type="button" onClick={getUser}>
        Get
      </button>

      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}

      {user && (
        <div style={{ marginTop: "20px" }}>
          <h3>User Details</h3>

          <p>
            <b>ID:</b> {user.id}
          </p>
          <p>
            <b>Email:</b> {user.email}
          </p>

          {editing ? (
            <>
              <div>
                <label>Firstname:</label>
                <br />
                <input
                  name="firstname"
                  value={editForm.firstname}
                  onChange={handleEditChange}
                />
              </div>
              <div>
                <label>Surname:</label>
                <br />
                <input
                  name="surname"
                  value={editForm.surname}
                  onChange={handleEditChange}
                />
              </div>
              <div>
                <label>Balance:</label>
                <br />
                <input
                  name="balance"
                  value={editForm.balance}
                  onChange={handleEditChange}
                />
              </div>

              <button type="button" onClick={saveEdit}>
                Save
              </button>
              <button type="button" onClick={cancelEdit}>
                Cancel
              </button>
            </>
          ) : (
            <>
              <p>
                <b>Firstname:</b> {user.firstname}
              </p>
              <p>
                <b>Surname:</b> {user.surname}
              </p>
              <p>
                <b>Balance:</b> {user.balance}
              </p>

              <button type="button" onClick={startEditing}>
                Update
              </button>
              <button type="button" onClick={deleteUser}>
                Delete
              </button>
            </>
          )}
        </div>
      )}
    </div>
  );
}
