import axios from "axios";
import {getToken} from "./authService";

const apiUrl = process.env.REACT_APP_API_URL;

// get request for all endpoints
export const fetchProtectedData = async (endpoint) => {
    const token = getToken();
    try {
        console.log(endpoint);
        const response = await axios.get(`${apiUrl}/${endpoint}`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        return response.data;

    } catch (error) {
        console.log(error);
    }
};