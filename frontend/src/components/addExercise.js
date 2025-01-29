import React, {useState} from "react";
import {
    Button,
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalCloseButton,
    ModalBody,
    ModalFooter,
    useDisclosure, FormControl, FormLabel, Input, Select, AlertIcon, Alert,
} from "@chakra-ui/react";
import ButtonWithLabel from "./ButtonWithLabel";
import { MdOutlineAddBox } from "react-icons/md";
import {postData} from "../services/api";

const AddExercise = () => {
    const { isOpen, onOpen, onClose } = useDisclosure()

    const [formData, setFormData] = useState({
        name: "",
        muscleGroupId: "",
        description: "",
    });


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const [created, setCreated] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await postData({endpoint: "exercise/add",data: formData});
            console.log(response);
            if (response === 201) {
                setCreated(true);
            }
            else{
                setCreated(false);
            }
        } catch (error) {
            console.error("Login failed:", error);
        }
    };


    return (
        <>
        <ButtonWithLabel
            name="Add exercise"
            icon={MdOutlineAddBox}
            action={onOpen}
        />
        <Modal closeOnOverlayClick={false} isOpen={isOpen} onClose={onClose}>
            <ModalOverlay/>
            <form onSubmit={handleSubmit}>
                <ModalContent>
                    <ModalHeader>Add exercise</ModalHeader>
                    <ModalCloseButton/>
                    <ModalBody pb={6}>
                        <FormControl>
                            <FormLabel>name</FormLabel>
                            <Input
                                name="name"
                                placeholder='name'
                                onChange={handleChange}
                            />
                        </FormControl>

                        <FormControl>
                            <FormLabel>Muscle group</FormLabel>
                            <Select
                                name="muscleGroupId"
                                placeholder='Select muscle group'
                                onChange={handleChange}
                            >
                                <option value='1'>Chest</option>
                                <option value='2'>Back</option>
                                <option value='3'>Legs</option>
                                <option value='4'>Shoulders</option>
                                <option value='5'>Arms</option>
                                <option value='6'>Core(Abs)</option>
                            </Select>
                        </FormControl>

                        <FormControl mt={4}>
                            <FormLabel>description</FormLabel>
                            <Input
                                name="description"
                                placeholder='description'
                                onChange={handleChange}
                            />
                        </FormControl>

                        {/*if server created exercise display alert*/}
                        {created && (<Alert status='success'
                                variant='solid'
                                borderRadius={4}
                                marginTop={4}
                        >
                            <AlertIcon/>
                            Exercise successfully added !
                        </Alert>)}

                    </ModalBody>

                    <ModalFooter>
                        <Button colorScheme='blue'
                                mr={3}
                                type='submit'
                                // onClick={onClose}
                        >
                            Add exercise
                        </Button>
                        <Button onClick={onClose}>Cancel</Button>
                </ModalFooter>
                </ModalContent>
            </form>
        </Modal>
        </>
    )
}
export default AddExercise;