"use client";
import { useParams } from 'next/navigation';
import { useState } from 'react';
import { useEffect } from 'react';
import { BsBookmark, BsBookmarkFill } from 'react-icons/bs';

import { DeleteBookmark, GetSeriesBookmark, PostBookmark } from '../api/bookmarkApi';


export const BookmarkButton = () => {
  const [isMarked, setIsMarked] = useState(false)
  const params = useParams();

  useEffect(() => {
    GetSeriesBookmark(params.id).then((data) => {
      if (data) {
        setIsMarked(data.isBookmarked)
      }
    })
  }, [isMarked])

  const markHandle = (seriesId: string) => {
    setIsMarked(true)
    PostBookmark(seriesId)
  }
  const unMarkHandle = (seriesId: string) => {
    setIsMarked(false)
    DeleteBookmark(seriesId)
  }

  return (
    <>
      {isMarked ? <BsBookmarkFill onClick={() => unMarkHandle(params.id)} /> : <BsBookmark onClick={() => markHandle(params.id)} />}
    </>
  )
}