"use-client"
import styled from 'styled-components'
export const ActivePost = () => {
  return (<StyledActive>
    active
  </StyledActive>)
}

const StyledActive = styled.div`
  width: calc(80vw / 3);
  height: calc(80vw / 3);
  /* max-width: 270px;
  height: 270px; */
  
  border-radius: 8px;
  border: solid 1px #3f910c;
  box-shadow: #eff8e0 6px 8px 10px;
  @media screen and (min-width: 1024px) {
      width: 270px;
      height: 270px;
  }
`