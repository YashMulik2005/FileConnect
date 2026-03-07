import { useState, useRef } from "react";
import { uploadFile, shareByMail } from "../api/fileApi";
import QRModal from "../components/QRModal";

export default function UploadPage() {
  const [file, setFile] = useState(null);
  const [result, setResult] = useState(null);
  const [mail, setMail] = useState("");
  const [shareMsg, setShareMsg] = useState("");
  const [showQR, setShowQR] = useState(false);
  const [loading, setLoading] = useState(false);
  const [drag, setDrag] = useState(false);
  const inputRef = useRef();

  const handleUpload = async () => {
    if (!file) return;
    setLoading(true);
    const res = await uploadFile(file);
    setLoading(false);
    if (res.status) setResult(res.data);
  };

  const handleShare = async () => {
    if (!mail || !result) return;
    const res = await shareByMail(mail, result.url);
    setShareMsg(res.message || "Shared!");
  };

  const onDrop = (e) => {
    e.preventDefault();
    setDrag(false);
    setFile(e.dataTransfer.files[0]);
  };

  const fmt = (bytes) => bytes > 1e6 ? `${(bytes/1e6).toFixed(1)} MB` : `${(bytes/1e3).toFixed(1)} KB`;

  return (
    <div className="page">
      <header className="header">
        <span className="logo">FileConnect</span>
        <nav>
          <a href="#" className="nav-active">Upload</a>
          <a href="#retrieve">Retrieve</a>
        </nav>
      </header>

      <main className="main">
        <div className="hero-text">
          <h1>Drop. Share. <em>Done.</em></h1>
          <p>Upload any file and share it instantly via link, QR code, or email.</p>
        </div>

        <div
          className={`dropzone ${drag ? "drag-over" : ""} ${file ? "has-file" : ""}`}
          onClick={() => inputRef.current.click()}
          onDragOver={(e) => { e.preventDefault(); setDrag(true); }}
          onDragLeave={() => setDrag(false)}
          onDrop={onDrop}
        >
          <input ref={inputRef} type="file" hidden onChange={(e) => setFile(e.target.files[0])} />
          {file ? (
            <div className="file-info">
              <span className="file-icon">📎</span>
              <span className="file-name">{file.name}</span>
              <span className="file-size">{fmt(file.size)}</span>
            </div>
          ) : (
            <>
              <span className="drop-icon">⬆</span>
              <p>Click or drag a file here</p>
            </>
          )}
        </div>

        <button className="btn-primary" onClick={handleUpload} disabled={!file || loading}>
          {loading ? <span className="spinner" /> : "Upload File"}
        </button>

        {result && (
          <div className="result-card">
            <div className="result-row">
              <span className="result-label">Short URL</span>
              <code className="result-url">{result.url}</code>
            </div>
            <div className="result-row">
              <span className="result-label">File</span>
              <span className="result-val">{result.fileName}</span>
            </div>
            <div className="result-row">
              <span className="result-label">Type</span>
              <span className="result-val">{result.contentType}</span>
            </div>
            <div className="result-row">
              <span className="result-label">Size</span>
              <span className="result-val">{fmt(result.size)}</span>
            </div>

            <div className="share-row">
              <input
                className="input-mail"
                placeholder="Recipient email"
                value={mail}
                onChange={(e) => setMail(e.target.value)}
              />
              <button className="btn-secondary" onClick={handleShare}>Send</button>
              <button className="btn-qr" onClick={() => setShowQR(true)}>QR</button>
            </div>
            {shareMsg && <p className="share-msg">{shareMsg}</p>}
          </div>
        )}
      </main>

{showQR && <QRModal fileUrl={result.file} fileName={result.fileName} onClose={() => setShowQR(false)} />}    </div>
  );
}