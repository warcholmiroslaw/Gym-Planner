import {Box} from "@chakra-ui/react";
import {Route, Routes} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/SignUpPage";
import Dashboard from "./pages/Dashboard";

function App() {
  return (
    <>
    <Box minH={"100vh"}>
      <Routes>
        <Route exact path="/" element={<LoginPage/>} />
        <Route exact path="/signup" element={<SignUpPage/>} />
        <Route exact path="/dashboard" element={<Dashboard/>} />
      </Routes>
    </Box>
    </>
  );
}

export default App;
