"use-client"
import styled from 'styled-components'

import { StyledPost } from "./StyledPost"
import { PostData } from "./type"


export const ActivePost = ({ ...data }: PostData) => {
  return (
    <StyledActive>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }} />
      </div>
      <div className='text'> active {data.id} </div>

    </StyledActive>
  )
}

const StyledActive = styled(StyledPost)`

  .box {
    border-radius: 8px;
    border: solid 2px #3f910c;
    box-shadow: 6px 6px #e4fabf, -6px 6px #e4fabf, 6px -6px #e4fabf, -6px -6px #e4fabf;
  }
`