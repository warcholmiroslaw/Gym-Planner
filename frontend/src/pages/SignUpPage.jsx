import {
    Button,
    Container,
    Image,
    Stack,
    Text
} from "@chakra-ui/react";
import React from "react";
import logo from '../images/logo.png';
import { RiLockPasswordLine } from "react-icons/ri";
import { FaRegUser } from "react-icons/fa6";
import { IoEyeOutline } from "react-icons/io5";
import { FiEyeOff } from "react-icons/fi";
import {MdOutlineMailOutline} from "react-icons/md";
import {LuPhone} from "react-icons/lu";
import CustomInput from "../components/CustomInput";
import LogoWithTitle from "../components/LogoWithTitle";




const SignUpPage = () => {


    const inputFields = [
        { name: "email", placeholder: "email", icon: MdOutlineMailOutline, isRequired: true },
        { name: "name", placeholder: "name", icon: FaRegUser, type: "text", isRequired: true },
        { name: "surname", placeholder: "surname", icon: FaRegUser, type: "text", isRequired: true },
        { name: "password", placeholder: "password", icon: RiLockPasswordLine, type: "password", isRequired: true },
        { name: "confirm password", placeholder: "Confirm password", icon: RiLockPasswordLine, type: "password" ,isRequired: true},
    ];

    function handleSubmit(e) {
        e.preventDefault();
    }

    return (
            <Container display="flex"
                       flexDirection="column"
                       justifyContent="center"
                       minHeight="100vh"// Centrowanie pionowe
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
                                isRequired={field.isRequired}
                            />
                        ))}


                        <Button type="submit"
                                bg="#005BC5"
                                color="#FFFFFF">
                            Create account
                        </Button>

                    </Stack>
                </form>

            </Container>
    );

};
export default SignUpPage;