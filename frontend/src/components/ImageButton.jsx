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
                alignItems="center"
                justifyContent="center"
                boxSize="100%"
                color="#DADADA"
                margin={1}
                _hover={{bg: "#005BC5", color: "#FFFFFF"}}
                borderRadius={10}
                padding={{base:2, md: 4, lg:8}}
                boxSizing="border-box"
        >


            <Image src={image}
                   boxSize={{base: "80%", md: "70%", lg: "60%"}}
                   boxSizing="border-box"
            >
            </Image>
            <Text
                fontSize="2xl"
                fontWeight="bold"
                padding={3}
                height="auto"
                width="100%"
            >
                {name}
            </Text>

        </Button>
    )
}

export default ImageButton;