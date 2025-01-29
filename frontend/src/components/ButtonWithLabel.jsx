import React from 'react';
import {Icon} from "@chakra-ui/react"
import {Button, Text} from "@chakra-ui/react";
import {useNavigate} from "react-router-dom";




const ButtonWithLabel = ({fieldKey, icon, name, action, onClick}) => {

    const navigate = useNavigate();

    return (
        <Button
            onClick = {onClick ?? (() => navigate(`/${action}`))}
            bg = "#001449"
            display="flex"
            flexDirection="column"
            justifyContent="center"
            boxSize= "100px"
            color="#DADADA"
            margin={1}
            _hover={{bg: "#005BC5", color: "#FFFFFF"}}
        >

            <Icon as={icon} boxSize="40px"/>
            <Text
                textStyle="sm"
            >
                {name}
            </Text>
        </Button>
    )
}

export default ButtonWithLabel;