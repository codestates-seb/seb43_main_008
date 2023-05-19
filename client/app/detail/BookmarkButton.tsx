import { useState } from 'react';
import { BsBookmark, BsBookmarkFill } from 'react-icons/bs';

import { PostBookmark } from '../api/bookmarkApi';

interface BookmarkButtonProps {
  seriesId: string;
}

export const BookmarkButton = ({ seriesId }: BookmarkButtonProps) => {
  const [isMarked, setIsMarked] = useState(false)

  const bookmarkHandle = (seriesId: string) => {
    setIsMarked(!isMarked)
    PostBookmark(seriesId)
  }

  return (
    <div onClick={() => bookmarkHandle(seriesId)}>{isMarked ? <BsBookmarkFill /> : <BsBookmark />}</div>
  )
}