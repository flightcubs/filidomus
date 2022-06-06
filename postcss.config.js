module.exports = {
  plugins: {
    "tailwindcss/nesting": {},
    autoprefixer: {},
    tailwindcss: {},
    cssnano: process.env.RELEASE == "true" ? {} : false,
  },
};
