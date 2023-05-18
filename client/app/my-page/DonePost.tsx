"use-client"
import styled from 'styled-components'

import { Medal } from './Medal'
import { StyledPost } from "./StyledPost"
import { PostData } from "./type"


export const DonePost = ({ ...data }: PostData) => {
  return (
    <StyledDone>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }}> </div>
      </div>
      <div className='text'> {data.title} </div>
      {data.voteResult ? <Medal /> : null}
    </StyledDone>
  )
}

const StyledDone = styled(StyledPost)`
  .image {    
    filter: grayscale(90%);
  }
  .box{
    border: none;
    box-shadow: none;
  }
`