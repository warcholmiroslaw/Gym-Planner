import { extendTheme } from '@chakra-ui/react';

const theme = extendTheme({
    styles: {
        global: {
            body: {
                bg: "#001449", // default background color
                color: "#858585", // default text color
            },
        },
    },
});

export default theme;
