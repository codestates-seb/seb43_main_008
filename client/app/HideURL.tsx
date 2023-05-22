"use client";

import { useEffect } from "react";

export default function HideURL() {
  useEffect(() => {
    history.replaceState({}, null, location.pathname);
  }, []);
}
