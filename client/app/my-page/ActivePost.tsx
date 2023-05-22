"use-client"
import { useRouter } from "next/navigation";
import styled from 'styled-components'

import { StyledPost } from "./StyledPost"
import { PostData } from "./type"


export const ActivePost = ({ ...data }: PostData) => {
  const router = useRouter();
  const HandleMoveToDetail = (id: number, title: string) => {
    router.push(`/detail/${id}`);
    sessionStorage.setItem("header", title);
  }

  return (
    <StyledActive onClick={() => HandleMoveToDetail(data.id, data.title)}>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }} />
      </div>
      <div className='text'> {data.title} </div>
    </StyledActive>
  )
}

const StyledActive = styled(StyledPost)`
  cursor: pointer;
  .box {
    border-radius: 8px;
    border: solid 2px #3f910c;
    box-shadow: 6px 6px #e4fabf, -6px 6px #e4fabf, 6px -6px #e4fabf, -6px -6px #e4fabf;
  }
`