import jwt_decode from "jwt-decode";

export const decodeToken = (token) => {
    try {
        return jwt_decode(token);
    } catch (error) {
        return null;
    }
};

export const isTokenExpired = (token) => {
    const decoded = decodeToken(token);
    if (!decoded) return true;

    const currentTime = Date.now() / 1000; // time in seconds
    return decoded.exp < currentTime;
};
