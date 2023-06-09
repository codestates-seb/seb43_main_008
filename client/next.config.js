/** @type {import('next').NextConfig} */
const withPWA = require("@ducanh2912/next-pwa").default({
  dest: "public",
});

module.exports = withPWA({
  reactStrictMode: false,
  // images: {
  //   remotePatterns: [
  //     {
  //       protocol: 'https',
  //       hostname: 'ssts-img.s3.ap-northeast-2.amazonaws.com',
  //       pathname: '/member/**'
  //     },
  //   ],
  // },

  images: {
    domains: ["ssts-img.s3.ap-northeast-2.amazonaws.com"],
  },

  experimental: {
    appDir: true,
  },
  webpack: (config) => {
    config.module.rules.push({
      test: /\.svg$/i,
      issuer: /\.[jt]sx?$/,
      use: ["@svgr/webpack"],
    });
    return config;
  },
});
