import {Box} from "@chakra-ui/react";
import {Route, Routes} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/SignUpPage";
import Dashboard from "./pages/Dashboard";
import Exercises from "./pages/Exercises";
import ExercisesByCategory from "./pages/ExercisesByCategory";

function App() {
  return (
    <Box
        maxWidth="100vw"
        boxSizing="border-box">
      <Routes>
        <Route exact path="/" element={<LoginPage/>} />
        <Route exact path="/signup" element={<SignUpPage/>} />
        <Route exact path="/dashboard" element={<Dashboard />} />
        <Route exact path="/exercises" element={<Exercises />} />
      <Route exact path="/exercise/category/:category" element={<ExercisesByCategory />} />
      </Routes>
    </Box>
  );
}

export default App;
