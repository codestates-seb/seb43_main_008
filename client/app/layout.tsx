import "./global.css";
import "./font.css";

import { Metadata } from 'next';

import StyledComponentsRegistry from "./registry";

export const metadata: Metadata = {
  title: '쓰쓰또쓰 - 플라스틱 재사용 과정을 공유하는 커뮤니티 서비스',
  description: '플라스틱 재사용 과정을 공유합니다',
  keywords: ["재활용", "플라스틱", "업사이클", "커뮤니티"],
  openGraph: {
    title: '쓰쓰또쓰',
    description: "쓰쓰또쓰(쓰고 쓰고 또 쓰자)는 플라스틱 재사용 과정을 공유하는 커뮤니티 서비스입니다.",
    url: "https://ssdss.vercel.app/",
    siteName: "쓰쓰또쓰(쓰고 쓰고 또 쓰자)",
    images: [
      {
        url: "https://ibb.co/YXGWP5B",
        width: 800,
        height: 800,
      },
    ],
  },
}

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="ko">
      <head>
        <link rel="manifest" href="/manifest.json" />
        <link rel="icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="apple-touch-icon" href="/icons/icon-192x192.png" />
        <link
          rel="apple-touch-startup- image"
          href="/images/splashscreens.png"
        />
        <meta name="msapplication-TileColor" content="#ffffff" />
        <meta name="theme-color" content="#ffffff" />
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1, user-scalable=no"
        />
      </head>
      <body>
        <StyledComponentsRegistry>
          <div className="main-container">{children}</div>
        </StyledComponentsRegistry>
      </body>
    </html>
  );
}
