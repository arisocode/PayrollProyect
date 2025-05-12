import { useState, useEffect } from "react";

export default function Captcha({ onValidate }) {
  const [captchaText, setCaptchaText] = useState("");

  useEffect(() => {
    setCaptchaText(generateCaptcha());
  }, []);

  function generateCaptcha() {
    return Math.random().toString(36).substring(2, 8).toUpperCase();
  }

  return (
    <div className="captcha-container">
      <div className="captcha-box">{captchaText}</div>
      <input
        type="text"
        placeholder="Ingresa el CAPTCHA"
        onChange={(e) => onValidate(e.target.value === captchaText)}
      />
    </div>
  );
}