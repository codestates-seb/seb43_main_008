"use-client"
import { useRouter } from "next/navigation";
import styled from 'styled-components'

import { Medal } from './Medal'
import { StyledPost } from "./StyledPost"
import { PostData } from "./type"


export const DonePost = ({ ...data }: PostData) => {

  const router = useRouter();
  const HandleMoveToDetail = (id: number, title: string) => {
    router.push(`/detail/${id}`);
    sessionStorage.setItem("header", title);
    sessionStorage.setItem("menu", null);
  }

  return (
    <StyledDone onClick={() => HandleMoveToDetail(data.id, data.title)}>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }}> </div>
      </div>
      <div className='text'> {data.title} </div>
      {data.voteResult ? <Medal /> : null}
    </StyledDone>
  )
}

const StyledDone = styled(StyledPost)`
  cursor: pointer;

  .image {    
    filter: grayscale(90%);
  }
  .box{
    border: none;
    box-shadow: none;
  }
`