import axios from "axios";

const API_URL = "http://localhost:8080/api/campaigns";

export const getCampaigns = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};