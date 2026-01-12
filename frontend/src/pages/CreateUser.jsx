import { useState } from "react";
import axios from "axios";
import { API_URL } from "../config/api";


export function CreateUser() {
  const [form, setForm] = useState({
    firstname: "",
    surname: "",
    email: "",
    balance: "",
  });

  const [createdUser, setCreatedUser] = useState(null);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setCreatedUser(null);

    try {
      console.log("API URL:", API_URL);
      console.log("Full request URL:", `${API_URL}/users`);
      
      const response = await axios.post(
        `${API_URL}/users`,
        {
          firstname: form.firstname,
          surname: form.surname,
          email: form.email,
          balance: form.balance,
        }
      );

      setCreatedUser(response.data);

      setForm({
        firstname: "",
        surname: "",
        email: "",
        balance: "",
      });
    } catch (err) {
      if (err.response && err.response.data) {
        setError(JSON.stringify(err.response.data));
      } else {
        setError("Error creating user");
      }
    }
  };

  return (
    <div>
      <h2>Create User</h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Firstname:</label>
          <br />
          <input
            name="firstname"
            value={form.firstname}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Surname:</label>
          <br />
          <input
            name="surname"
            value={form.surname}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Email:</label>
          <br />
          <input
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Starting Balance:</label>
          <br />
          <input
            name="balance"
            value={form.balance}
            onChange={handleChange}
            placeholder="0.00"
            required
          />
        </div>

        <button type="submit">Create</button>
      </form>

      {createdUser && (
        <div style={{ marginTop: "20px" }}>
          <h3>User created</h3>
          <p><b>ID:</b> {createdUser.id}</p>
          <p><b>Firstname:</b> {createdUser.firstname}</p>
          <p><b>Surname:</b> {createdUser.surname}</p>
          <p><b>Email:</b> {createdUser.email}</p>
          <p><b>Balance:</b> {createdUser.balance}</p>
        </div>
      )}

      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
}
