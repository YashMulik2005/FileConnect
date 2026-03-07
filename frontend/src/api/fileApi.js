const BASE = import.meta.env.VITE_API_BASE + "/api";

export const uploadFile = async (file) => {
  const form = new FormData();
  form.append("file", file);
  const res = await fetch(`${BASE}/upload`, { method: "POST", body: form });
  return res.json();
};

export const getFile = async (url) => {
  const res = await fetch(`${BASE}/file/${url}`);
  return res.json();
};

export const shareByMail = async (mail, url) => {
  const res = await fetch(`${BASE}/file/shareByMail`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ mail, url }),
  });
  return res.json();
};