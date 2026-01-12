import { useState, useEffect } from "react";
import axios from "axios";
import { API_URL } from "../config/api";


export function CampaignList() {
  const [campaigns, setCampaigns] = useState([]);
  const [page, setPage] = useState(0);
  const [size] = useState(10);
  const [sort, setSort] = useState("fund");
  const [totalPages, setTotalPages] = useState(0);
  const [error, setError] = useState("");

  const fetchCampaigns = async () => {
    setError("");
    try {
      const response = await axios.get(
        `${API_URL}/campaigns/`,
        {
          params: { page, size, sort },
        }
      );
      const pageData = response.data;
      setCampaigns(pageData.content);
      setTotalPages(pageData.totalPages);
    } catch (err) {
      setError("Error fetching campaigns");
    }
  };

  useEffect(() => {
    fetchCampaigns();
  }, [page, size, sort]);

  const handlePrevious = () => {
    if (page > 0) setPage(page - 1);
  };

  const handleNext = () => {
    if (page < totalPages - 1) setPage(page + 1);
  };

  return (
    <div>
      <h2>All Campaigns</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <table border="1" cellPadding="5" cellSpacing="0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Owner</th>
            <th>Keywords</th>
            <th>Bid Amount</th>
            <th>Fund</th>
            <th>Status</th>
            <th>Town</th>
            <th>Radius (km)</th>
          </tr>
        </thead>
        <tbody>
          {campaigns.map((c) => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>{c.name}</td>
              <td>
                {c.ownerName} {c.ownerSurname}
              </td>
              <td>{c.keywords.join(", ")}</td>
              <td>{c.bidAmount}</td>
              <td>{c.fund}</td>
              <td>{c.status ? "On" : "Off"}</td>
              <td>{c.town || "-"}</td>
              <td>{c.radius}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <div style={{ marginTop: "10px" }}>
        <button type="button" onClick={handlePrevious} disabled={page === 0}>
          Previous
        </button>
        <span style={{ margin: "0 10px" }}>
          Page {page + 1} of {totalPages}
        </span>
        <button
          type="button"
          onClick={handleNext}
          disabled={page >= totalPages - 1}
        >
          Next
        </button>
      </div>

      <div style={{ marginTop: "10px" }}>
        <label>Sort by: </label>
        <select value={sort} onChange={(e) => setSort(e.target.value)}>
          <option value="fund">Fund</option>
          <option value="bidAmount">Bid Amount</option>
          <option value="name">Name</option>
        </select>
      </div>
    </div>
  );
}
