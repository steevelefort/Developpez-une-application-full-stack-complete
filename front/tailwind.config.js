/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts,css}"
  ],
  theme: {
    extend: {
      colors: {
        purple: {
          DEFAULT: '#7763C5',
        },
        grey: {
          DEFAULT: '#939393',
          '100': '#F5F5F5',
          '200': '#EEEEEE',
        }
      },
      borderRadius: {
        "xs": "7px",
      },
    },
  },
  plugins: [],
};
