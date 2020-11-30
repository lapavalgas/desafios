import React from 'react';
import githubLogo from '../../assets/githubLogo.svg';
import './style.css';

const HeaderGithubLogo = (props) => {
  return (
    <div className="headerLogo" >
      <img src={githubLogo} alt="" />
    </div>
  )
}

export default HeaderGithubLogo;