"use-client"
import styled from 'styled-components'
export const ActivePost = () => {
  return (<StyledActive>
    active
  </StyledActive>)
}

const StyledActive = styled.div`
  width: 30%;
  height: calc(80vw / 3);
  
  border-radius: 8px;
  border: solid 1px #3f910c;
  box-shadow: #eff8e0 6px 8px 10px;
`