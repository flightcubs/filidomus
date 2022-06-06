module.exports = {
  content:
    process.env.NODE_ENV == "production"
      ? ["./build/js/main.js"]
      : ["./resources/public/js/cljs-runtime/*.js"],
  theme: {
    extend: {
      fontFamily: {
        serif: ["EB Garamond"],
        sans: ["Hammersmith One"],
        mono: ["Inconsolata"],
      },
      colors: {
        primary: "#2A3947",
        accent: "#DC8C89",
      },
    },
  },
  plugins: [],
};
