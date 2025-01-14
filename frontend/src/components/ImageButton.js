import React from 'react';
import {Icon, IconButton, Stack} from "@chakra-ui/react"
import { MdOutlineHistory } from "react-icons/md";
import {Button, Image, Text} from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";




const ImageButton = ({fieldKey, image, name, action}) => {
    const navigate = useNavigate();

    const handleSignUp = (action) => {
        navigate(`/${action}`);
    };

    return (
        <Button onClick={() => handleSignUp(action)}
                bg = "#001449"
                display="flex"
                flexDirection="column"
                justifyContent="center"
                boxSize= "32vh"
                color="#DADADA"
                margin={1}
                _hover={{bg: "#005BC5", color: "#FFFFFF"}}
                borderRadius={10}>


            <Image src={image} boxSize="25vh"></Image>
            <Text
                fontSize="2xl"
                fontWeight="bold"
                padding={3}
            >
                {name}
            </Text>

        </Button>
    )
}

export default ImageButton;