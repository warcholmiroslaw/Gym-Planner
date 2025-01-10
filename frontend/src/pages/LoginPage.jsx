import {
    Button,
    Container, FormControl, FormLabel,
    Image,
    Input,
    InputGroup,
    InputLeftElement,
    InputRightElement,
    Stack,
    Text
} from "@chakra-ui/react";
import React, {useState} from "react";
import logo from '../images/logo.png';
import { RiLockPasswordLine } from "react-icons/ri";
import { FaRegUser } from "react-icons/fa6";
import { IoEyeOutline } from "react-icons/io5";
import { FiEyeOff } from "react-icons/fi";
import {MdOutlineMailOutline} from "react-icons/md";
import {LuPhone} from "react-icons/lu";
import CustomInput from "../components/CustomInput";
import {useNavigate} from "react-router-dom";
import { login } from "../services/authService";
import LogoWithTitle from "../components/LogoWithTitle";




const LoginPage = () => {

    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    const inputFields = [
        { name: "email", placeholder: "email", icon: MdOutlineMailOutline, isRequired: true },
        { name: "password", placeholder: "password", icon: RiLockPasswordLine, type: "password", isRequired: true },
    ];

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = await login(formData);
            console.log("Logged in! Token:", token);
            navigate("/dashboard");
        } catch (error) {
            console.error("Login failed:", error);
        }
    };


    const handleSignUp = () => {
        navigate("/signup");
    };

    return (
        <Container display="flex"
                   flexDirection="column"
                   justifyContent="center"
                   minHeight="100vh"
        >
           <LogoWithTitle />

            <form onSubmit={handleSubmit}>
                <Stack spacing={4}>

                    {inputFields.map((field, index) => (
                                <CustomInput
                                    key={index}
                                    fieldKey={index}
                                    name={field.name}
                                    placeholder={field.placeholder}
                                    icon={field.icon}
                                    type={field.type}
                                    onChange={handleChange}
                                    value={formData[field.name]}
                                    isRequired={field.isRequired}
                                />
                    ))}

                    <Button type = "submit"
                            bg="#005BC5"
                            color="#FFFFFF"
                            _hover={{bg: "#00b4fc", color: "#FFFFFF", borderColor: "#FFFFFF"}}
                    >
                        Login
                    </Button>

                    <Button onClick={handleSignUp}
                            variant="outline"
                            borderColor="#005BC5"
                            color="#005BC5"
                            _hover={{bg: "#012677", color: "#FFFFFF", borderColor: "#FFFFFF"}}
                    >
                        Sign up
                    </Button>

                </Stack>
            </form>


        </Container>
    );
};
export default LoginPage;