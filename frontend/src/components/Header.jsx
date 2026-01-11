import { Link } from "react-router-dom";

export function Header() {
  return (
    <nav style={{ padding: "10px", borderBottom: "1px solid #ccc" }}>
      <Link to="/">Home</Link> |{" "}
      <Link to="/users/create">Create User</Link> |{" "}
      <Link to="/users/get">Get User</Link> |{" "}
      <Link to="/campaigns">All Campaigns</Link> |{" "}
      <Link to="/campaigns/create">Create Campaign</Link> |{" "}
      <Link to="/campaigns/get">Get Campaign</Link>
    </nav>
  );
}
