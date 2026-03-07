import UploadPage from "./pages/UploadPage";
import RetrievePage from "./pages/RetrievePage";
import "./styles.css";

export default function App() {
  const path = window.location.pathname;
  if (path.startsWith("/file/")) return <RetrievePage />;
  return <UploadPage />;
}
