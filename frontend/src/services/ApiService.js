import axios from "axios";

export default class ApiService {
    static BASE_URL = "http://localhost:8080";

    static getHeaders(){
        const token = localStorage.getItem("token");
        return {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
        };
    }    

    static async registerUser(registration) {
        const response = await axios.post(`${this.BASE_URL}/auth/signup`, registration)
        return response.data;
    }

    static async loginUser(loginDetails) {
        const response = await axios.post(`${this.BASE_URL}/auth/login`, loginDetails)
        return response.data;
    }

    static async getLoggedInUserInfo(loginDetails) {
        const response = await axios.get(`${this.BASE_URL}/user/my-info`, loginDetails)
        return response.data;
    }


}