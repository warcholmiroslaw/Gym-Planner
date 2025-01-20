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
    const url = "exercise/category/";

    const exerciseButtons = [
    {name: "chest",image: chest, action: url + "Chest"},
        {name: "arms",image: arms, action: url + "Arms"},
        {name: "back",image: back, action: url + "Back"},
        {name: "abs",image: abs, action: url + "Abs"},
        {name: "legs",image: legs, action: url + "Legs"},
        {name: "shoulders",image: shoulders, action: url + "Shoulder"},
    ]
    return (
    <Flex
        display="flex"
        justifyContent="center"
        alignItems="center"
        height={{base: "100%", md: "100%", lg:"100vh"}}
        width="100%"
        padding={{base:2, md: 4, lg:8}}
        boxSizing="border-box"
    >
        <SimpleGrid
            columns={{base: 1, md: 2, lg: 3}}
            justifyContent="center"
            alignItems="center"
            spacing={{base:4, md: 8, lg: 10}}
            width="100%"
            boxSizing="border-box"
        >
            {exerciseButtons.map((field, index) => (
                <ImageButton
                    width={{base: "70%", md: "45%", lg: "80%"}}
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

