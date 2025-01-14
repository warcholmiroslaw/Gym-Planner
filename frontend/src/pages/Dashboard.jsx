import React from "react";
import {Box, Flex, HStack, Stack, Text, VStack} from "@chakra-ui/react";
import ButtonWithLabel from "../components/ButtonWithLabel";
import { MdOutlineHistory } from "react-icons/md";
import { CiBoxList } from "react-icons/ci";
import { VscDebugStart } from "react-icons/vsc";
import { LuBicepsFlexed } from "react-icons/lu";
import { FiUser } from "react-icons/fi";
import { CiLogout } from "react-icons/ci";



const Dashboard = () => {


    const navigateButtons = [
        {icon: MdOutlineHistory, name: "History", action: "history"},
        {icon: CiBoxList, name: "Plans", action: "plans"},
        {icon: VscDebugStart, name: "Start", action: "start"},
        {icon: LuBicepsFlexed, name: "Exercises", action: "exercises"},
        {icon: FiUser, name: "Account", action: "account"}
    ];

    return (
        <>
            <HStack marginLeft="15vw"
                    justifyContent="space-between"
                    spacing={1}>
                <Text fontSize='5xl'
                    fontWeight="bold"
                    color="#DADADA"

                >
                Last training sessions
                </Text>
                <ButtonWithLabel icon={CiLogout} name="logout"/>

            </HStack>
            <VStack
                spacing={4}>

                <Stack spacing={2}></Stack>
                <Box bg="#005BC5"
                height="35vh"
                width="70%"
                borderRadius={10}>
                </Box>

                <Box bg="#005BC5"
                     height="35vh"
                     width="70%"
                     borderRadius={10}>
                </Box>

            </VStack>
            <Flex
                as="footer"
                position="fixed"
                bottom="0"
                padding="10px"
                width="100%"
                justifyContent="center"
                alignItems="center"
                color="white"
            >
                {navigateButtons.map((field, index) => (
                    <ButtonWithLabel
                    key={index}
                    fieldKey={index}
                    icon={field.icon}
                    name={field.name}
                    action={field.action}
                    />
                ))}
            </Flex>
        </>
    )
}
export default Dashboard;