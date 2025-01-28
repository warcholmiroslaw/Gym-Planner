import axios from "axios";


const apiUrl = process.env.REACT_APP_API_URL;


export const login = async (credentials) => {
    const response = await axios.post(`${apiUrl}/auth/login`, credentials);
    const token = response.data.token;

    localStorage.setItem("token", token);
    console.log(token);
    return token;
};

export const signup = async (credentials) => {
    const response = await axios.post(`${apiUrl}/auth/signup`, credentials);
    console.log(response);
    const token = response.data.token;
    return token;

}

export const getToken = () => {
    const token = localStorage.getItem("token");
    if(!token) {
        throw new Error("Token is missing in localStorage!");
    }
    return token;
};


export const logout = () => {
    localStorage.removeItem("token");
};

