import {useEffect, useState} from "react";
import {useParams} from 'react-router-dom';
import {Container, Flex, Text} from "@chakra-ui/react";
import ButtonWithLabel from "../components/ButtonWithLabel";
import { MdOutlineAddBox } from "react-icons/md";
import ExerciseList from "../components/ExerciseList";


const ExercisesByCategory = () => {

    const {category} = useParams();
    const [exercises, setExercises] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchExercises = async () => {
            setLoading(true);
            try {
                const response = await fetch(`http://localhost:8080/exercise/category/${category}`);
                const data = await response.json();
                setExercises(data);
            } catch (error) {
                console.error("Błąd podczas pobierania danych:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchExercises();
    }, [category]);

    if (loading) {
        return <p>Loading...</p>;
    }

    if (!exercises.length && !loading) {
        return <p>No exercises found for {category}.</p>;
    }

    return (
    <Container
        display="block"
        maxHeight="100vh"
        FlexDirection="column"
        justifyContent="center"
        maxWidth="100%"
        overflowY="hidden"
        boxSizing="border-box"
    >
            <Flex
                display="flex"
                justifyContent="space-between"
                alignItems="center"
                padding={{base: 4, md: 4, lg: 8}}
                width="100%"
                boxSizing="border-box"
            >
                <Text
                    fontSize="5xl"
                    fontWeight="bold"
                    color="#DADADA"
                >Exercises for {category}
                </Text>

                <ButtonWithLabel
                    action=" "
                    name="Add exercise"
                    icon = {MdOutlineAddBox}
                />

            </Flex>

            <ExerciseList
                display="flex"
                maxWidth="100%"
                exercises={exercises} />
    </Container>
    )
}
export default ExercisesByCategory;