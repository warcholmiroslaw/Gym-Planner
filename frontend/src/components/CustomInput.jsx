import React from "react";
import {InputGroup, InputLeftElement, Input, InputRightElement, FormLabel, FormControl} from "@chakra-ui/react";

const CustomInput = ({ fieldKey, name, icon: Icon, placeholder, type = "text", rightIcon, onChange, field, isRequired = false}) => {
    return (
            <FormControl id={name} isRequired={isRequired}>
                <FormLabel>{name}</FormLabel>
            <InputGroup>
                <InputLeftElement>
                    <Icon color="#858585" />
                </InputLeftElement>
                <Input name = {name}
                       bg="#FFFFFF"
                       boxShadow="0 2px 5px 3px #012677"
                       type={type} placeholder={placeholder}
                       onChange = {onChange}
                />
                {rightIcon && <InputRightElement>{rightIcon}</InputRightElement>}
            </InputGroup>
        </FormControl>
    );
};

export default CustomInput;
