"use-client"
import styled from 'styled-components'
export const DonePost = () => {
  return (<StyledActive>
    done
    <div className='text'> 세바스찬 </div>
  </StyledActive>)
}

const StyledActive = styled.div`
  width: calc(80vw / 3);
  height: calc(80vw / 3);

  border-radius: 8px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;

  .text{
    /* font-size: 21px; */
  }
  @media screen and (min-width: 1024px) {
      width: 270px;
      height: 270px;
  }
`