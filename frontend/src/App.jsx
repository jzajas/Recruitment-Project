import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Header } from "./components/Header";

import { CreateUser } from "./pages/CreateUser";
import { GetUser } from "./pages/GetUser.jsx";
import { CreateCampaign } from "./pages/CreateCampaign";
import { CampaignList } from "./pages/CampaignList";
import { GetCampaign } from "./pages/GetCampaign.jsx";


function App() {
  return (
    <BrowserRouter>
      <Header />

      <Routes>
        <Route path="/" element={<h2>Home</h2>} />
        <Route path="/users/create" element={<CreateUser />} />
        <Route path="/users/get" element={<GetUser />} />
        <Route path="/campaigns" element={<CampaignList />} />
        <Route path="/campaigns/create" element={<CreateCampaign />} />
        <Route path="/campaigns/get" element={<GetCampaign />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;