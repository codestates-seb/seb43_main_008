/** @type {import('next').NextConfig} */
const withPWA = require("@ducanh2912/next-pwa").default({
  dest: "public",
});

module.exports = withPWA({
  reactStrictMode: true,
  experimental: {
    appDir: true,
  },
});
