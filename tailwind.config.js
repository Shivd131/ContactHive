/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./src/main/resources/**/*.{html,js}",
    "./node_modules/flowbite/**/*.js",
  ],
  theme: {
    extend: {},
  },
  plugins: [require("tailgrids/plugin"), require("flowbite/plugin")],
  darkMode: "class",
};
