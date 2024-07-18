/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  content: [
    './Integradora-1/src/main/resources/templates/**/*.html',
    './Integradora-1/src/main/resources/static/**/*.html',
    './Integradora-1/src/main/resources/static/**/*.js',
    './Integradora-1/src/main/resources/static/**/*.jsx',
    './Integradora-1/src/main/resources/static/**/*.ts',
    './Integradora-1/src/main/resources/static/**/*.tsx',
  ],
  theme: {
    extend: {
      colors: {
        blue: {
          100: '#E6EDFF',
          200: '#B3CBFF',
          DEFAULT: '#0047FF',
          600: '#0038CC',
          700: '#002999',
        },
        purple: {
          100: '#F2F1FF',
          200: '#C7C5FF',
          DEFAULT: '#9590FF',
          600: '#7773CC',
          700: '#5A5799',
        },
        dark: {
          100: '#5A5872',
          200: '#3B394D',
          DEFAULT: '#1C1A27',
          600: '#16151F',
          700: '#0F0E16',
        },
        green: {
          100: '#E0F4EF',
          200: '#B5E4D9',
          DEFAULT: '#6ABAA4',
          600: '#559583',
          700: '#407062',
        },
      },
      fontFamily: {
        'sans': ['Poppins', 'ui-sans-serif', 'system-ui', '-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'Helvetica Neue', 'Arial', 'Noto Sans', 'sans-serif', 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji'],
      },
      fontWeight: {
        'thin': 100,
        'extralight': 200,
        'light': 300,
        'normal': 400,
        'medium': 500,
        'semibold': 600,
        'bold': 700,
        'extrabold': 800,
        'black': 900,
      },
    },
  },
  plugins: [
    function({ addBase, theme }) {
      addBase({
        'body': { color: theme('colors.dark.DEFAULT') },
      })
    }
  ],
}