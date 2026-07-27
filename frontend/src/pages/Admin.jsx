import Navbar from "../components/Navbar";
import {
  Shield,
  Users,
  UserCog,
  Database,
  Activity,
} from "lucide-react";

export default function Admin() {
  const email = localStorage.getItem("email");

  return (
    <>
      <Navbar />

      <div className="min-h-screen bg-slate-100 p-8">

        <div className="max-w-6xl mx-auto">

          {/* Header */}
          <div className="bg-gradient-to-r from-purple-600 to-indigo-600 rounded-2xl text-white p-8 shadow-lg">

            <h1 className="text-4xl font-bold">
              Admin Dashboard
            </h1>

            <p className="mt-2 text-purple-100">
              Welcome back, {email}
            </p>

          </div>

          {/* Cards */}
          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-6 mt-8">

            <div className="bg-white rounded-xl shadow p-6">
              <Users className="text-blue-600 mb-4" size={32} />
              <h2 className="font-bold text-xl">Users</h2>
              <p className="text-gray-500 text-sm">
                Manage registered users
              </p>
            </div>

            <div className="bg-white rounded-xl shadow p-6">
              <UserCog className="text-green-600 mb-4" size={32} />
              <h2 className="font-bold text-xl">Roles</h2>
              <p className="text-gray-500 text-sm">
                Manage user roles
              </p>
            </div>

            <div className="bg-white rounded-xl shadow p-6">
              <Database className="text-orange-600 mb-4" size={32} />
              <h2 className="font-bold text-xl">Database</h2>
              <p className="text-gray-500 text-sm">
                Monitor application data
              </p>
            </div>

            <div className="bg-white rounded-xl shadow p-6">
              <Activity className="text-red-600 mb-4" size={32} />
              <h2 className="font-bold text-xl">Activity</h2>
              <p className="text-gray-500 text-sm">
                View recent system activity
              </p>
            </div>

          </div>

          {/* Security Card */}
          <div className="bg-white rounded-xl shadow p-8 mt-8 flex items-center gap-5">

            <Shield size={45} className="text-purple-600" />

            <div>
              <h2 className="text-2xl font-bold">
                SecureAuth Admin Panel
              </h2>

              <p className="text-gray-500 mt-1">
                This page is accessible only to users with the <strong>ADMIN</strong> role.
              </p>
            </div>

          </div>

        </div>

      </div>
    </>
  );
}