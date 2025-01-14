import {Box, Button, Flex, SimpleGrid, Text} from "@chakra-ui/react";
import React from "react";
import ButtonWithLabel from "../components/ButtonWithLabel";
import chest from "../images/chest.png";
import arms from "../images/arms.png";
import back from "../images/back.png";
import abs from "../images/abs.png";
import legs from "../images/legs.png";
import shoulders from "../images/shoulders.png";
import ImageButton from "../components/ImageButton";


const Exercises = () => {

    const exerciseButtons = [
    {name: "chest",image: chest, action: "chest"},
        {name: "arms",image: arms, action: "arms"},
        {name: "back",image: back, action: "back"},
        {name: "abs",image: abs, action: "abs"},
        {name: "legs",image: legs, action: "legs"},
        {name: "shoulders",image: shoulders, action: "shoulder"},
    ]
    return (
    <Flex
        justifyContent="center"
        alignItems="center"
        height="100vh"
    >
        <SimpleGrid columns={3} spacing={12}>
            {exerciseButtons.map((field, index) => (
                <ImageButton
                    key={index}
                    fieldKey={index}
                    image={field.image}
                    name={field.name}
                    action={field.action}
                    />
            ))}
        </SimpleGrid>
    </Flex>
    )
};
export default Exercises;

