import { useState, useEffect } from "react";
import { getFile } from "../api/fileApi";

export default function RetrievePage() {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  const urlParam = window.location.pathname.split("/file/")[1];

  useEffect(() => {
    if (!urlParam) return;
    getFile(urlParam)
      .then((res) => {
        if (res.status) setData(res.data);
        else setError("File not found.");
      })
      .catch(() => setError("Failed to fetch file."));
  }, [urlParam]);

  const fmt = (bytes) =>
    bytes > 1e6 ? `${(bytes / 1e6).toFixed(1)} MB` : `${(bytes / 1e3).toFixed(1)} KB`;

  return (
    <div className="page">
      <header className="header">
        <a href="/" className="logo">FileConnect</a>
        <nav>
          <a href="/">Upload</a>
          <a href="#" className="nav-active">Retrieve</a>
        </nav>
      </header>

      <main className="main">
        {!urlParam && (
          <div className="hero-text">
            <h1>Retrieve a <em>File.</em></h1>
            <p>Enter a short URL to access your file.</p>
          </div>
        )}

        {error && <p className="share-msg" style={{ color: "#ff5555" }}>{error}</p>}

        {data && (
          <div className="result-card">
            <div className="result-row">
              <span className="result-label">File Name</span>
              <span className="result-val">{data.fileName}</span>
            </div>
            <div className="result-row">
              <span className="result-label">Type</span>
              <span className="result-val">{data.contentType}</span>
            </div>
            <div className="result-row">
              <span className="result-label">Size</span>
              <span className="result-val">{fmt(data.size)}</span>
            </div>
            <a className="btn-primary" href={data.file} target="_blank" rel="noreferrer"
              style={{ textDecoration: "none", textAlign: "center" }}>
              Download File
            </a>
          </div>
        )}

        {urlParam && !data && !error && (
          <div style={{ display: "flex", justifyContent: "center", padding: "2rem" }}>
            <span className="spinner" style={{ borderColor: "#e8ff47", borderTopColor: "transparent" }} />
          </div>
        )}
      </main>
    </div>
  );
}