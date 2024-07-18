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
          100: '#E6EDFF', // Variante clara
          200: '#B3CBFF', // Variante clara
          DEFAULT: '#0047FF', // Color principal
          600: '#0038CC', // Variante oscura
          700: '#002999', // Variante oscura
        },
        purple: {
          100: '#F2F1FF', // Variante clara
          200: '#C7C5FF', // Variante clara
          DEFAULT: '#9590FF', // Color principal
          600: '#7773CC', // Variante oscura
          700: '#5A5799', // Variante oscura
        },
        dark: {
          100: '#5A5872', // Variante clara
          200: '#3B394D', // Variante clara
          DEFAULT: '#1C1A27', // Color principal
          600: '#16151F', // Variante oscura
          700: '#0F0E16', // Variante oscura
        },
        green: {
          100: '#E0F4EF', // Variante clara
          200: '#B5E4D9', // Variante clara
          DEFAULT: '#6ABAA4', // Color principal
          600: '#559583', // Variante oscura
          700: '#407062', // Variante oscura
        },
      },
    },
  },
  plugins: [],
}