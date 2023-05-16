"use-client"
import styled from 'styled-components'

import { data } from "./data"
import { Medal } from './Medal'
import { StyledPost } from "./StyledPost"

interface Props {
  level?: number;

}
export const DonePost: React.FC<Props> = ({ level }) => {
  return (
    <StyledDone>
      <div className='box'>
        <div className="image" style={{ backgroundImage: `url(${data.image})` }}> </div>
      </div>
      <div className='text'> 세바스찬 </div>
      {level ? <Medal /> : null}
    </StyledDone>
  )
}

const StyledDone = styled(StyledPost)`
  .image {    
    filter: grayscale(80%);
  }
  .box{
    border: none;
    box-shadow: none;
  }
`