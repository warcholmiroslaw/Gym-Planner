import React from "react";
import {Image, Stack, Text} from "@chakra-ui/react";
import logo from "../images/logo.png";


const LogoWithTitle = () => {
    return (
        <Stack alignItems="center" spacing={2}>
            <Text fontSize='5xl'
                  fontWeight="bold"
                  color="#FFFFFF"
                  textShadow="2px 2px 5px #005bc5">
                Gym Planner
            </Text>
            <Image src={logo} alt="logo"></Image>
        </Stack>
    )
}

export default LogoWithTitle;