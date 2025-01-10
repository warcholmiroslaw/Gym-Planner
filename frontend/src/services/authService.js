import axios from "axios";


const API_URL = "http://localhost:8080";


export const login = async (credentials) => {
    const response = await axios.post(`${API_URL}/auth/login`, credentials);
    const token = response.data.token;

    localStorage.setItem("token", token);

    return token;
};


export const getToken = () => {
    return localStorage.getItem("token");
};


export const logout = () => {
    localStorage.removeItem("token");
};

export const fetchProtectedData = async (endpoint) => {
    const token = getToken();
    return await axios.get(`${API_URL}/${endpoint}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};
