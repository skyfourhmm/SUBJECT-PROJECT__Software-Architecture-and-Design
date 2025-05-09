import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import CustomerLayout from "./Layout/CustomerLayout";
import AdminLayout from "./Layout/AdminLayout";
import Login from "./pages/Login";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login />} />

        <Route path="customer/*" element={<CustomerLayout />} />
        <Route path="admin/*" element={<AdminLayout />} />
      </Routes>
    </Router>
  );
}

export default App;
