import { useState } from "react";
import axios from "axios";

export function CreateCampaign() {
  const [form, setForm] = useState({
    ownerId: "",
    name: "",
    keywords: "",
    bidAmount: "",
    fund: "",
    status: false,
    town: "",
    radius: "",
  });

  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");
  const [createdCampaign, setCreatedCampaign] = useState(null);

  const keywordOptions = ["food", "tech", "fashion", "gaming", "education"];
  const townOptions = ["Warsaw", "Krakow", "Gdansk", "Wroclaw", "Poznan"];

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    const val = type === "checkbox" ? checked : value;
    setForm((prev) => ({ ...prev, [name]: val }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    setCreatedCampaign(null);

    if (!form.ownerId) return setError("Owner ID must be specified");
    if (!form.keywords.trim()) return setError("At least one keyword must be specified");
    if (parseFloat(form.bidAmount) < 5) return setError("Bid amount must be at least 5");

    try {
      const payload = {
        ownerId: parseInt(form.ownerId),
        name: form.name,
        keywords: form.keywords
          .split(",")
          .map((k) => k.trim())
          .filter((k) => k !== ""),
        bidAmount: form.bidAmount,
        fund: form.fund,
        status: form.status,
        town: form.town || null,
        radius: parseInt(form.radius),
      };

      const response = await axios.post(
        "http://localhost:8080/campaigns",
        payload
      );

      const campaign = response.data; // should match CampaignReturnDTO
      setCreatedCampaign(campaign);
      setSuccess(`Campaign "${campaign.name}" created successfully!`);

      // Reset form
      setForm({
        ownerId: "",
        name: "",
        keywords: "",
        bidAmount: "",
        fund: "",
        status: false,
        town: "",
        radius: "",
      });
    } catch (err) {
      setError(err.response?.data?.message || "Error creating campaign");
    }
  };

  const filteredKeywords = keywordOptions.filter((kw) =>
    kw.toLowerCase().includes(form.keywords.toLowerCase())
  );

  return (
    <div>
      <h2>Create Campaign</h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Owner ID:</label>
          <br />
          <input
            type="number"
            name="ownerId"
            value={form.ownerId}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Campaign Name:</label>
          <br />
          <input
            type="text"
            name="name"
            value={form.name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Keywords (comma separated):</label>
          <br />
          <input
            type="text"
            name="keywords"
            value={form.keywords}
            onChange={handleChange}
            required
          />
          {form.keywords && filteredKeywords.length > 0 && (
            <div style={{ fontSize: "small", color: "gray" }}>
              Suggestions: {filteredKeywords.join(", ")}
            </div>
          )}
        </div>

        <div>
          <label>Bid Amount:</label>
          <br />
          <input
            type="number"
            name="bidAmount"
            value={form.bidAmount}
            onChange={handleChange}
            min="5"
            required
          />
        </div>

        <div>
          <label>Campaign Fund:</label>
          <br />
          <input
            type="number"
            name="fund"
            value={form.fund}
            onChange={handleChange}
            min="0"
            required
          />
        </div>

        <div>
          <label>Status:</label>
          <input
            type="checkbox"
            name="status"
            checked={form.status}
            onChange={handleChange}
          />
        </div>

        <div>
          <label>Town:</label>
          <br />
          <select name="town" value={form.town} onChange={handleChange}>
            <option value="">--Select Town--</option>
            {townOptions.map((town) => (
              <option key={town} value={town}>
                {town}
              </option>
            ))}
          </select>
        </div>

        <div>
          <label>Radius (km):</label>
          <br />
          <input
            type="number"
            name="radius"
            value={form.radius}
            onChange={handleChange}
            min="1"
            required
          />
        </div>

        <button type="submit">Create Campaign</button>
      </form>

      {success && <p style={{ color: "green" }}>{success}</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {/* Display created campaign info */}
      {createdCampaign && (
        <div style={{ marginTop: "20px", border: "1px solid gray", padding: "10px" }}>
          <h3>Created Campaign</h3>
          <p><b>ID:</b> {createdCampaign.id}</p>
          <p><b>Name:</b> {createdCampaign.name}</p>
          <p><b>Owner:</b> {createdCampaign.ownerName} {createdCampaign.ownerSurname}</p>
          <p><b>Keywords:</b> {createdCampaign.keywords.join(", ")}</p>
          <p><b>Bid Amount:</b> {createdCampaign.bidAmount}</p>
          <p><b>Fund:</b> {createdCampaign.fund}</p>
          <p><b>Status:</b> {createdCampaign.status ? "On" : "Off"}</p>
          <p><b>Town:</b> {createdCampaign.town || "-"}</p>
          <p><b>Radius:</b> {createdCampaign.radius} km</p>
        </div>
      )}
    </div>
  );
}
