import { LogOut, Mail, ShieldCheck, UserCircle } from "lucide-react";
import Navbar from "../components/Navbar";
import { useEffect, useState } from "react";
import { getCurrentUser } from "../services/userService";
import { logout } from "../services/authService";

export default function Dashboard() {

    const [user, setUser] = useState(null);

    const email = user?.email;
    const role = user?.role;
    const name = user?.name;

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await getCurrentUser();
        console.log("User Response:", response.data);
        setUser(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchUser();
  }, []);

  const handleLogout = async () => {
    try {
        const refreshToken = localStorage.getItem("refreshToken");

        if (refreshToken) {
        await logout(refreshToken);
        }
    } catch (error) {
        console.error("Logout failed:", error);
    } finally {
        localStorage.clear();
        window.location.href = "/login";
    }
    };

  return (
    <>

      <Navbar />
    <div className="min-h-screen bg-slate-100 flex items-center justify-center px-4">
      <div className="w-full max-w-lg bg-white rounded-2xl shadow-xl overflow-hidden">

        {/* Header */}
        <div className="bg-gradient-to-r from-blue-600 to-indigo-600 text-white p-8 text-center">

          <UserCircle size={70} className="mx-auto mb-3" />

          <h1 className="text-3xl font-bold">
            Welcome {name} 👋
          </h1>

          <p className="text-blue-100 mt-2">
            SecureAuth Dashboard
          </p>
        </div>

        {/* Body */}
        <div className="p-8">
        <div className="grid gap-5">

            <div className="bg-slate-100 rounded-xl p-5 flex items-center gap-4">
            <Mail className="text-blue-600" size={28} />

            <div>
                <p className="text-gray-500 text-sm">
                Email
                </p>

                <p className="font-semibold">
                {email}
                </p>
            </div>
            </div>

            <div className="bg-slate-100 rounded-xl p-5 flex items-center gap-4">
            <ShieldCheck className="text-green-600" size={28} />

            <div>
                <p className="text-gray-500 text-sm">
                Role
                </p>

                <p className="font-semibold">
                {role}
                </p>
            </div>
            </div>

        <div className="bg-slate-100 rounded-xl p-5">
        <p className="text-gray-500 text-sm">
            Account Status
        </p>

        <p className="font-semibold text-green-600">
            Active
        </p>
        </div>

        </div>

        <button
            onClick={handleLogout}
            className="mt-8 w-full bg-red-500 hover:bg-red-600 text-white py-3 rounded-xl flex justify-center items-center gap-2 transition"
        >
            <LogOut size={18} />
            Logout
        </button>

        </div>
      </div>
    </div>
    </>
  );
}