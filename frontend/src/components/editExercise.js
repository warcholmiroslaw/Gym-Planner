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
import {putData} from "../services/api";

const UpdateExercise = ({exercise, isOpen, onClose}) => {


    const [formData, setFormData] = useState({
        id: exercise.id,
        name: exercise.name,
        muscleGroupId: exercise.muscleGroupId,
        description: exercise.description,
    });


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const [updated, setUpdated] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await putData({endpoint: "exercise/update",data: formData});
            console.log("update response : ", response);
            if (response === 200) {
                setUpdated(true);
            }
            else{
                setUpdated(false);
            }
        } catch (error) {
            console.error("Login failed:", error);
        }
    };


    return (
        <>
            <Modal closeOnOverlayClick={false} isOpen={isOpen} onClose={onClose}>
                <ModalOverlay/>
                <form onSubmit={handleSubmit}>
                    <ModalContent
                    >
                        <ModalHeader>Update exercise</ModalHeader>
                        <ModalCloseButton/>
                        <ModalBody pb={6}>
                            <FormControl>
                                <FormLabel>name</FormLabel>
                                <Input
                                    name="name"
                                    value={formData.name}
                                    onChange={handleChange}
                                />
                            </FormControl>

                            <FormControl>
                                <FormLabel>Muscle group</FormLabel>
                                <Input
                                    name="MuscleGroup"
                                    value={exercise.muscleGroup.name}
                                    isReadOnly={true}
                                />
                            </FormControl>

                            <FormControl mt={4}>
                            <FormLabel>description</FormLabel>
                                <Input
                                    name="description"
                                    value={formData.description}
                                    onChange={handleChange}
                                />
                            </FormControl>

                            {/*if server created exercise display alert*/}
                            {updated && (<Alert status='success'
                                                variant='solid'
                                                borderRadius={4}
                                                marginTop={4}
                            >
                                <AlertIcon/>
                                Exercise updated !
                            </Alert>)}

                        </ModalBody>

                        <ModalFooter>
                            <Button colorScheme='blue'
                                    mr={3}
                                    type='submit'
                                // onClick={onClose}
                            >
                                Change Exercise
                            </Button>
                            <Button onClick={onClose}>Cancel</Button>
                        </ModalFooter>
                    </ModalContent>
                </form>
            </Modal>
        </>
    )
}
export default UpdateExercise;