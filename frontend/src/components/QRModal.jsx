import { useEffect, useRef } from "react";
import QRCode from "qrcode";

export default function QRModal({ fileUrl, fileName, onClose }) {
  const canvasRef = useRef();

  useEffect(() => {
    QRCode.toCanvas(canvasRef.current, fileUrl, {
      width: 220,
      color: { dark: "#0f0f0f", light: "#fafafa" },
    });
  }, [fileUrl]);

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-box" onClick={(e) => e.stopPropagation()}>
        <p className="modal-label">Scan to download</p>
        <canvas ref={canvasRef} />
        <code className="modal-url">{fileName}</code>
        <button className="btn-ghost" onClick={onClose}>Close</button>
      </div>
    </div>
  );
}