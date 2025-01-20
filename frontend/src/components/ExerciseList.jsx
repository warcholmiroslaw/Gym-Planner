import {
    Table,
    Thead,
    Tbody,
    Tfoot,
    Tr,
    Th,
    Td,
    TableCaption,
    TableContainer, Button, Flex,
} from '@chakra-ui/react'
import React, {useState} from "react";


const ExerciseList =({exercises}) => {
    const [hoveredRow, setHoveredRow] = useState(null);

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
                    <Td>
                        <Flex
                            direction={{ base: "column", md: "row", lg: "row"}}
                            justifyContent="center"
                        >
                            <Button
                                margin = "0"
                                flex="1"
                                color="#808aa4"
                                display={hoveredRow === index ? 'inline-block' : 'none'}
                                mr={{base:0, md:2, lg:2}}
                                justifyContent="center"
                                padding={{base:2, md:2, lg:0}}
                                marginBottom={{base:2, md:0, lg:0}}
                                width="auto"
                                whiteSpace="nowrap"
                            >
                                Edit
                            </Button>
                            <Button
                                flex="1"
                                variant="outline"
                                color="001242"
                                borderColor="001242"
                                display={hoveredRow === index ? 'inline-block' : 'none'}
                                mr={{base:0, md:2, lg:2}}
                                justifyContent="center"
                                padding={{base:2, md:2, lg:0}}
                                width="auto"
                                whiteSpace="nowrap"
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