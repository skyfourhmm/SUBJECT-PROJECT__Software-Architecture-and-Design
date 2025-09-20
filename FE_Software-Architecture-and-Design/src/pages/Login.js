import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../api/user";

function Login() {
  const navigate = useNavigate();
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const data = await login(userName, password);

      localStorage.setItem("accessToken", data.token);
      localStorage.setItem("userInfo", JSON.stringify(data.thongTinNguoiDung));

      if (data.vaiTro !== "NHAN_VIEN") {
        navigate("/customer");
      } else {
        navigate("/admin");
      }
    } catch (err) {
      console.error("Login failed:", err);
    }
  };

  return (
    <div className="flex h-screen">
      {/* Left Section - Login Form */}
      <div className="w-1/2 flex flex-col justify-center px-16 bg-gray-100">
        <h2 className="text-3xl font-bold mb-6">Welcome Back to DoubleQT!</h2>
        <p className="text-gray-600 mb-8">Sign in to your account</p>

        <form>
          <label className="block mb-2 text-gray-700">User Name</label>
          <input
            type="text"
            placeholder="plearse enter your username"
            className="w-full px-4 py-2 border rounded-full mb-4"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
          />

          <label className="block mb-2 text-gray-700">Password</label>
          <input
            type="password"
            placeholder="plesae enter your password"
            className="w-full px-4 py-2 border rounded-full mb-4"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <div className="flex justify-between text-sm text-gray-600 mb-4">
            <div>
              <input type="checkbox" className="mr-2" />
              Remember Me
            </div>
            <a href="#" className="text-blue-500">
              Forgot Password?
            </a>
          </div>

          <button
            className="w-full bg-blue-600 text-white py-2 rounded-full hover:bg-blue-700"
            onClick={handleLogin}
          >
            Log In
          </button>
        </form>

        <div className="flex items-center my-4">
          <hr className="flex-1 border-gray-300" />
          <span className="px-4 text-gray-500">Or</span>
          <hr className="flex-1 border-gray-300" />
        </div>

        <div className="flex gap-4">
          <button className="flex items-center justify-center w-1/2 border py-2 rounded-full">
            <img
              src="https://www.svgrepo.com/show/355037/google.svg"
              alt="Google"
              className="w-5 h-5 mr-2"
            />
            Continue with Google
          </button>
          <button className="flex items-center justify-center w-1/2 border py-2 rounded-full">
            <img
              src="https://www.svgrepo.com/show/75117/apple-big-logo.svg"
              alt="Apple"
              className="w-5 h-5 mr-2"
            />
            Continue with Apple
          </button>
        </div>

        <p className="mt-6 text-center text-gray-600">
          Don’t have an account?{" "}
          <a href="/register" className="text-blue-500">
            Sign up
          </a>
        </p>
      </div>

      {/* Right Section - Images and Description */}
      <div className="w-1/2 bg-white flex flex-col justify-center items-center text-center p-12">
        <div className="grid grid-cols-2 gap-4">
          <img
            src="https://plus.unsplash.com/premium_photo-1661964071015-d97428970584?q=80&w=1920&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            className="rounded-lg"
            alt="Hotel 1"
          />
          <img
            src="https://plus.unsplash.com/premium_photo-1687960116497-0dc41e1808a2?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            className="rounded-lg"
            alt="Hotel 2"
          />
          <img
            src="https://plus.unsplash.com/premium_photo-1676823553207-758c7a66e9bb?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            className="rounded-lg"
            alt="Hotel 3"
          />
          <img
            src="https://plus.unsplash.com/premium_photo-1661964304872-7b715cf38cd1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            className="rounded-lg"
            alt="Hotel 4"
          />
        </div>
        <h2 className="text-2xl font-bold mt-6">
          Find Your Perfect Stay and Book with Confidence
        </h2>
        <p className="text-gray-600 mt-4">
          Elevate your travel experience with our seamless booking platform —
          explore curated hotels, compare options, and secure the ideal room for
          your journey.
        </p>
      </div>
    </div>
  );
}

export default Login;
