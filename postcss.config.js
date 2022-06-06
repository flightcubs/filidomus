module.exports = {
  plugins: {
    "tailwindcss/nesting": {},
    autoprefixer: {},
    tailwindcss: {},
    cssnano: process.env.NODE_ENV == "production" ? {} : false,
  },
};
