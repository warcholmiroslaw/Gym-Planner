import axios from "axios";
import {getToken} from "./authService";

const apiUrl = process.env.REACT_APP_API_URL;

// get request for all endpoints
export const fetchProtectedData = async (endpoint) => {
    const token = getToken();
    try {
        const response = await axios.get(
            `${apiUrl}/${endpoint}`,
            {
                headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        return response.data;

    } catch (error) {
        console.log(error);
    }
};

export const postData = async ({endpoint, data}) => {

    const token = getToken();
    console.log("endpoint: ", endpoint);
    try{
        const response = await axios.post(
            `${apiUrl}/${endpoint}`,
            data,
            {headers: {Authorization: `Bearer ${token}`}}
        );
        return response.status;

    } catch (error){
        console.log(error);
    }
};