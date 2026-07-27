import api from "./api";

export const register = (data) => {
  return api.post("/api/auth/register", data);
};

export const login = (data) => {
  return api.post("/api/auth/login", data);
};

export const logout = (refreshToken) => {
  return api.post("/api/auth/logout", {
    refreshToken,
  });
};

export const refreshAccessToken = (refreshToken) => {
  return api.post("/api/auth/refresh-token", {
    refreshToken,
  });
};