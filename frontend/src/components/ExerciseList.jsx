import {
    Table,
    Thead,
    Tbody,
    Tr,
    Th,
    Td,
    TableContainer, Button, Flex, useDisclosure,
} from '@chakra-ui/react'
import React, {useState} from "react";
import {deleteData} from "../services/api";
import { useToast, Text} from '@chakra-ui/react'

import UpdateExercise from "./editExercise";

const ExerciseList =({exercises}) => {

    const [hoveredRow, setHoveredRow] = useState(null);


    const { isOpen, onOpen, onClose } = useDisclosure()

    const toast = useToast()

    const deleteExercise = async (e, exerciseId, exerciseName) => {
        e.preventDefault();

        const response = await deleteData(`exercise/${exerciseId}`);
        if (response === 204){
            toast({
                title: 'Exercise deleted',
                description: (
                    <>
                        Exercise <Text as="span" fontWeight="bold">{exerciseName}</Text> deleted from database.
                    </>
                ),
                status: 'success',
                duration: 9000,
                isClosable: true,
                onCloseComplete: () => {
                    // reload page after toast disappear or closed
                    window.location.reload();
                },
            })
        }
    }



    return (
    <TableContainer
        maxWidth="100%"
        maxHeight={{base: "80vh", md: "80vh", lg: "78vh"}}
        borderRadius={{base:2 , md:4, lg:8}}
        overflowY="auto"

        // remove scrollbar
        sx={
            {
                '::-webkit-scrollbar':{
                    display:'none'
                }
            }
        }
    >
        <Table variant="unstyled"
               color="#DADADA"
        >
            <Thead
                position="sticky"
                height="10vh"
                top="0"
                zIndex="1"
                boxShadow="xl"
                bg="#000e33"
            >
                <Tr
                  boxShadow="md"
                  mb={2}
                  boxSizing="border-box"
                >
                    <Th width={{base:"30%", md:"20%", lg:"20%"}}>
                        Name
                    </Th>
                    <Th width={{base:"60%", md:"55%", lg:"60%"}}>
                        Description
                    </Th>
                    <Th width={{base:"10%", md:"25%", lg:"20%"}}>

                    </Th>
                </Tr>
            </Thead>
            <Tbody>

                {exercises.map((exercise, index) => (

                <Tr key={index}
                    boxShadow="2px 2px 5px 1px #667292"
                    bg={index % 2 === 1 ? "#001242" : "#33436d"}
                    onMouseEnter={() => setHoveredRow(index)}
                    onMouseLeave={() => setHoveredRow(null)}
                    _hover={{
                        bg: "#808aa4",
                    }}
                    wordWrap="break-word"
                    whiteSpace="normal"
                    maxWidth="100%"
                >
                    <Td
                        wordWrap="break-word"
                    >
                        {exercise.name}
                    </Td>
                    <Td
                        wordWrap="break-word"
                    >
                        {exercise.description}
                    </Td>

                    {/*row with buttons to edit /global exercises can't be changed*/}
                    <Td>
                        <Flex
                            direction={{ base: "column", md: "row", lg: "row"}}
                            justifyContent="center"
                        >
                            <Button
                                margin = "0"
                                flex="1"
                                color="#808aa4"
                                display={exercise.userId !== null && hoveredRow === index ? 'inline-block' : 'none'}                                mr={{base:0, md:2, lg:2}}
                                justifyContent="center"
                                padding={{base:2, md:2, lg:0}}
                                marginBottom={{base:2, md:0, lg:0}}
                                width="auto"
                                whiteSpace="nowrap"
                                // action{}
                                onClick={onOpen}
                            >
                                Edit
                            </Button>

                            {/*when you click Edit modal will display*/}
                            <UpdateExercise
                                exercise={exercise}
                                isOpen={isOpen}
                                onClose={onClose}
                            />

                            <Button
                                flex="1"
                                variant="outline"
                                color="001242"
                                borderColor="001242"
                                display={exercise.userId !== null && hoveredRow === index ? 'inline-block' : 'none'}
                                mr={{base:0, md:2, lg:2}}
                                justifyContent="center"
                                padding={{base:2, md:2, lg:0}}
                                width="auto"
                                whiteSpace="nowrap"
                                onClick={(e) => deleteExercise(e, exercise.id, exercise.name)}
                            >
                                Delete
                            </Button>
                        </Flex>
                    </Td>
                </Tr>
                ))}
            </Tbody>
        </Table>
    </TableContainer>
    )
}
export default ExerciseList;