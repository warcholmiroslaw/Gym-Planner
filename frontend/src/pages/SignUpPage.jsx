import {
    Button,
    Container,
    Stack} from "@chakra-ui/react";
import React, {useState} from "react";
import { RiLockPasswordLine } from "react-icons/ri";
import { FaRegUser } from "react-icons/fa6";
import {MdOutlineMailOutline} from "react-icons/md";
import CustomInput from "../components/CustomInput";
import LogoWithTitle from "../components/LogoWithTitle";
import {signup} from "../services/authService";
import {useNavigate} from "react-router-dom";




const SignUpPage = () => {

    const [formData, setFormData] = useState({
        email: "",
        name: "",
        surname: "",
        password: "",
        confirmPassword: "",
    });

    const inputFields = [
        { name: "email", placeholder: "email", icon: MdOutlineMailOutline, isRequired: true },
        { name: "name", placeholder: "name", icon: FaRegUser, type: "text", isRequired: true },
        { name: "surname", placeholder: "surname", icon: FaRegUser, type: "text", isRequired: true },
        { name: "password", placeholder: "password", icon: RiLockPasswordLine, type: "password", isRequired: true },
        { name: "confirmPassword", placeholder: "Confirm password", icon: RiLockPasswordLine, type: "password" ,isRequired: true},
    ];

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const  handleSubmit = async (e) => {
        e.preventDefault();

        try {
            console.log(formData);
            const token = await signup(formData);
            console.log("Sign up Token: ", token);

        }
        catch (error) {
            console.log(error);
        }
    }

    return (
            <Container display="flex"
                       flexDirection="column"
                       justifyContent="center"
                       minHeight="100vh"
            >

                <LogoWithTitle />

                <form onSubmit={handleSubmit}>
                    <Stack spacing={4}
                           alignItems="center"

                    >

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


                        <Button type="submit"
                                bg="#005BC5"
                                color="#FFFFFF"
                                width={{base: "70%", md: "100%", lg: "100%"}}
                        >
                            Create account
                        </Button>

                    </Stack>
                </form>

            </Container>
    );

};
export default SignUpPage;