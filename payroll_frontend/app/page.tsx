"use client";

import { useState, useEffect } from "react";

export default function Login() {
  const [captchaText, setCaptchaText] = useState("");
  const [captchaInput, setCaptchaInput] = useState("");

  useEffect(() => {
    setCaptchaText(generateCaptcha());
  }, []);

  function generateCaptcha() {
    return Math.random().toString(36).substring(2, 8).toUpperCase();
  }

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    if (captchaInput !== captchaText) {
      alert("CAPTCHA incorrecto");
      return;
    }
    alert("Login exitoso");
  }

  return (
    <div className="login-container">
      <div className="login-box">
        <h2>Payroll</h2>
        <form onSubmit={handleSubmit}>
          <label>Usuario</label>
          <input type="text" placeholder="Ingresa tu usuario" required />

          <label>Contraseña</label>
          <input type="password" placeholder="Ingresa tu contraseña" required />

          <label> </label>
          <div className="captcha-box">
            {captchaText.split("").map((char, index) => (
              <span key={index} className="captcha-char">
                {char}
              </span>
            ))}
          </div>
          <input
            type="text"
            placeholder="Ingresa el CAPTCHA"
            onChange={(e) => setCaptchaInput(e.target.value)}
            required
          />

          <button type="submit">Ingresar</button>
        </form>
      </div>
    </div>
  );
}