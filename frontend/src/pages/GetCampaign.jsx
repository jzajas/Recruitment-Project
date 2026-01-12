import { useState } from "react";
import axios from "axios";
import { API_URL } from "../config/api";


export function GetCampaign() {
  const [campaignId, setCampaignId] = useState("");
  const [campaign, setCampaign] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const fetchCampaign = async () => {
    setError("");
    setSuccess("");
    setEditMode(false);

    try {
      const response = await axios.get(
        `${API_URL}/campaigns/${campaignId}`
      );
      setCampaign(response.data);
    } catch (err) {
      setCampaign(null);
      setError(err.response?.data?.message || "Campaign not found");
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    const val = type === "checkbox" ? checked : value;

    setCampaign((prev) => ({
      ...prev,
      [name]: val,
    }));
  };

  const handleUpdate = async () => {
    setError("");
    setSuccess("");

    try {
      const payload = {
        name: campaign.name,
        keywords: campaign.keywords,
        bidAmount: campaign.bidAmount,
        fund: campaign.fund,
        status: campaign.status,
        town: campaign.town,
        radius: campaign.radius,
      };

      const response = await axios.put(
        `${API_URL}/campaigns/${campaign.id}`,
        payload
      );

      setCampaign(response.data);
      setEditMode(false);
      setSuccess("Campaign updated successfully");
    } catch (err) {
      setError(err.response?.data?.message || "Error updating campaign");
    }
  };

  const handleDelete = async () => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this campaign?"
    );
    if (!confirmDelete) return;

    try {
      await axios.delete(
        `${API_URL}/campaigns/${campaign.id}`
      );
      setCampaign(null);
      setCampaignId("");
      setSuccess("Campaign deleted successfully");
    } catch (err) {
      setError(err.response?.data?.message || "Error deleting campaign");
    }
  };

  return (
    <div>
      <h2>Get Campaign</h2>

      <div>
        <input
          type="number"
          placeholder="Campaign ID"
          value={campaignId}
          onChange={(e) => setCampaignId(e.target.value)}
        />
        <button onClick={fetchCampaign}>Get Campaign</button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}

      {campaign && (
        <div style={{ marginTop: "20px", border: "1px solid gray", padding: "10px" }}>
          <h3>Campaign Details</h3>

          <p><b>ID:</b> {campaign.id}</p>
          <p>
            <b>Name:</b>{" "}
            {editMode ? (
              <input
                name="name"
                value={campaign.name}
                onChange={handleChange}
              />
            ) : (
              campaign.name
            )}
          </p>

          <p>
            <b>Owner:</b> {campaign.ownerName} {campaign.ownerSurname}
          </p>

          <p>
            <b>Keywords:</b>{" "}
            {editMode ? (
              <input
                name="keywords"
                value={campaign.keywords.join(", ")}
                onChange={(e) =>
                  setCampaign({
                    ...campaign,
                    keywords: e.target.value
                      .split(",")
                      .map((k) => k.trim()),
                  })
                }
              />
            ) : (
              campaign.keywords.join(", ")
            )}
          </p>

          <p>
            <b>Bid Amount:</b>{" "}
            {editMode ? (
              <input
                type="number"
                name="bidAmount"
                min="5"
                value={campaign.bidAmount}
                onChange={handleChange}
              />
            ) : (
              campaign.bidAmount
            )}
          </p>

          <p>
            <b>Fund:</b>{" "}
            {editMode ? (
              <input
                type="number"
                name="fund"
                min="0"
                value={campaign.fund}
                onChange={handleChange}
              />
            ) : (
              campaign.fund
            )}
          </p>

          <p>
            <b>Status:</b>{" "}
            {editMode ? (
              <input
                type="checkbox"
                name="status"
                checked={campaign.status}
                onChange={handleChange}
              />
            ) : campaign.status ? (
              "On"
            ) : (
              "Off"
            )}
          </p>

          <p>
            <b>Town:</b>{" "}
            {editMode ? (
              <input
                name="town"
                value={campaign.town || ""}
                onChange={handleChange}
              />
            ) : (
              campaign.town || "-"
            )}
          </p>

          <p>
            <b>Radius (km):</b>{" "}
            {editMode ? (
              <input
                type="number"
                name="radius"
                min="1"
                value={campaign.radius}
                onChange={handleChange}
              />
            ) : (
              campaign.radius
            )}
          </p>

          <div style={{ marginTop: "10px" }}>
            {editMode ? (
              <>
                <button onClick={handleUpdate}>Save</button>
                <button onClick={() => setEditMode(false)}>Cancel</button>
              </>
            ) : (
              <>
                <button onClick={() => setEditMode(true)}>Edit</button>
                <button onClick={handleDelete}>Delete</button>
              </>
            )}
          </div>
        </div>
      )}
    </div>
  );
}
